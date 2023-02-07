<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="account.AccountEntity" %>
<%@ page import="java.util.Collection" %>
<%@ page import="order.OrderEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="catalog.ProductEntity" %>
<%@ page import="catalog.CatalogEntity" %>
<%@ page import="java.util.HashSet" %>

<% ServletContext context = request.getServletContext(); %>
<%
    AccountEntity user = (AccountEntity) request.getSession().getAttribute("user");
    if (user == null || !user.getRole().equals(AccountEntity.Role.MANAGERUSER)) {
        String errors = "non sei autorizzato";
        session.setAttribute("ow-errors", errors);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    CatalogEntity c = (CatalogEntity) context.getAttribute("catalog");
    HashSet<ProductEntity> catalog = (HashSet<ProductEntity>) c.getCatalogProducts();
%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>Ordini clienti</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-12" style="overflow: scroll">
            <h3>Prodotti</h3>
            <div id="orders-table">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">PRODOTTO</th>
                        <th scope="col">NOME</th>
                        <th scope="col">PREZZO</th>
                        <th scope="col">QUANTITA DISPONIBILE</th>
                        <th scope="col">VISIBILE</th>
                        <th scope="col">MODIFICA INFO</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if (catalog != null) {
                            for ( ProductEntity product : catalog) {
                    %>
                    <tr>
                        <td><%=product.getProductId()%>
                        </td>
                        <td><%=product.getProductDescription()%>
                        </td>
                        <td><%=product.getProductPrice()%>
                        </td>
                        <td><%=product.getProductAvailability()%>
                        </td>
                        <td><%=product.isVisible()%>
                        </td>
                        <td>
                        <form id="upload-form" action="${pageContext.request.contextPath}/admin/product-update.jsp" method="post">
                            <input name="up_product" hidden="hidden" value="<%=product.getProductId()%>">
                            <button type="submit" class="btn btn-primary">Modifica</button>
                        </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td>nessun prodotto</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="/fragments/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
        integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
        crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
        crossorigin="anonymous">
</script>
</body>
</html>
