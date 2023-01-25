<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.sql.DataSource" %>

<%@ page import="com.dewine.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Users user = (Users) request.getSession().getAttribute("user");
    if (user == null || !user.getUserRole().equals("Admin")) {
        request.setAttribute("notAuthorized", Boolean.TRUE);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
%>
<% DataSource dataSource = (DataSource) pageContext.getServletContext().getAttribute("DataSource"); %>
<% ProductsDAO productsDAO = new ProductsDAO(dataSource); %>
<% CategoriesDAO categoriesDAO = new CategoriesDAO(dataSource); %>

<% ArrayList<String> errors = (ArrayList<String>) session.getAttribute("pw-errors"); %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>Vetrina</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>

<div class="container">
    <%
        if (errors != null && errors.size() > 0) {
            for (String error : errors) {
    %>
    <div class="row justify-content-center">
        <div class="col-lg-12">
            <div class="alert alert-warning wine-errors" role="alert">
                <%=error%>
            </div>
        </div>
    </div>
    <%
            }
        }
    %>
    <div class="row align-items-center">
        <div class="col-md-12" style="overflow: scroll">
            <h3>Prodotti</h3>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">ID PRODOTTO</th>
                    <th scope="col">Marca</th>
                    <th scope="col">Modello</th>
                    <th scope="col">Stock</th>
                    <th scope="col">Prezzo</th>
                    <th scope="col">Aliquota</th>
                    <th scope="col">Attivo</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Creato il</th>
                    <th scope="col">Modificato il</th>
                    <th scope="col">AZIONE</th>
                    <th scope="col">AZIONE</th>
                    <th scope="col">AZIONE</th>
                </tr>
                </thead>
                <tbody>
                <%
                    Collection<?> products;
                    try {
                        products = productsDAO.getAll("productId");
                        if (products != null) {
                            Iterator<?> iterator = products.iterator();
                            while (iterator.hasNext()) {
                                Products product = (Products) iterator.next();
                                if (!product.getIsDeleted()) {
                                    Categories category = categoriesDAO.doRetrieveByKey(product.getCategoryId());
                %>
                <tr>
                    <td><%=product.getProductId()%>
                    </td>
                    <td><%=product.getBrand()%>
                    </td>
                    <td><%=product.getModel()%>
                    </td>
                    <td><%=product.getStock()%>
                    </td>
                    <td><%=product.getPrice()%>
                    </td>
                    <td><%=product.getVat()%>
                    </td>
                    <td><%=product.getActivate()%>
                    </td>
                    <td><%=category.getCategoryName() + " ID:" + product.getCategoryId()%>
                    </td>
                    <td><%=product.getCreatedAt()%>
                    </td>
                    <td><%=product.getUpdatedAt()%>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/gestione?toDo=deleteProduct&productId=<%=product.getProductId()%>">
                            <button type="button" class="btn btn-danger">ELIMINA</button>
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/gestione?toDo=deactivateProduct&productId=<%=product.getProductId()%>">
                            <button type="button" class="btn btn-secondary">DISATTIVA</button>
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/gestione?toDo=activeProduct&productId=<%=product.getProductId()%>">
                            <button type="button" class="btn btn-success">ATTIVA</button>
                        </a>
                    </td>
                </tr>
                <% }
                }
                } else { %>
                <tr>
                    <td>nessuno prodotto disponibile</td>
                </tr>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <h3>Aggiorna lo stock</h3>
            <form action="${pageContext.request.contextPath}/updatestock" method="get">
                <div class="gy-2 form-group row align-items-end">
                    <div class="col-md-4">
                        <label for="input-product" class="form-label">Seleziona il prodotto</label>
                        <select class="form-select" aria-label="Seleziona categoria" name="up_product"
                                id="input-product" required>
                            <option selected>Seleziona il prodotto</option>
                            <%
                                Collection<?> productsSel;
                                try {
                                    productsSel = productsDAO.getAll("productId");
                                    if (productsSel != null) {
                                        Iterator<?> iterator = productsSel.iterator();
                                        while (iterator.hasNext()) {
                                            Products productSel = (Products) iterator.next();
                            %>
                            <option value=<%=productSel.getProductId()%>><%=productSel.getTitle()%>
                            </option>
                            <%
                                        }
                                    } else {

                                    }
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            %>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="input-stock" class="form-label">Quantità</label>
                        <input class="form-control" type="number" id="input-stock" required
                               placeholder="aggiorna stock" name="up_stock" min="0">
                    </div>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-primary">Update</button>
                        <button type="reset" class="btn btn-secondary">Annulla</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/fragments/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
        integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
        crossorigin="anonymous"></script>
</body>
</html>
