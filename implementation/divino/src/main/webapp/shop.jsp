<%@ page import="catalog.ProductEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="catalog.CatalogEntity" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% ServletContext context = request.getServletContext(); %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/fragments/meta.jsp" %>
    <script src="js/my-scripts.js" type="text/javascript"></script>

    <title>Shop</title>
</head>
<body class="bg-light">
<%@include file="/fragments/header.jsp" %>
<div class="container">
    <%
        ArrayList<String> exceptions = (ArrayList<String>) context.getAttribute("exceptions");

        if (exceptions != null) {
            for (String exception : exceptions) {
    %>
    <div class="row justify-content-center">
        <div class="col-lg-12">
            <div class="alert alert-warning wine-errors" role="alert">
                <%=exception%>
            </div>
        </div>
    </div>
    <%
            }
        }
    %>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <%
            CatalogEntity catalog = (CatalogEntity) context.getAttribute("catalog");
            if (catalog.getCatalogProducts().isEmpty()) {
        %>
        <h4>Nessun prodotto disponibile</h4>
        <%
        } else {
            for (ProductEntity product : catalog.getCatalogProducts()) {
        %>
        <div class="col-md-6 col-lg-4">
            <div class="card h-100 w-100 wine-card bg-white">
                <div class="overflow-hidden">
                    <img src="${pageContext.request.contextPath}/images/<%=product.getImagePath()%>/"
                         class="card-img-top img-fluid zoom" alt="<%=product.getProductBrand()%>" width="200px"
                         height="200px">
                </div>
                <div class="card-body bg-black" style="color: white; padding: 40px">
                    <h5 class="card-title"><%=product.getProductBrand()%>
                    </h5>
                    <h6 class="card-price" style="font-size: 20px">&euro; <%=product.getProductPrice()%>
                    </h6>
                    <div class="mt-4 d-flex justify-content-md-start">
                        <a href="${pageContext.request.contextPath}/cart?mode=add&productid=<%=product.getProductId()%>"
                           class="btn btn-primary wine-button">
                            AGGIUNGI AL CARRELLO
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <%
                }
            }
        %>
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