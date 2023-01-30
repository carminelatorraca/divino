<%@ page import="java.util.ArrayList" %>
<%@ page import="catalog.ProductEntity" %>
<%@ page import="java.util.HashSet" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="meta/meta.jsp" %>
    <script src="${pageContext.request.contextPath}/js/custom-script.js" type="text/javascript"></script>

    <title>Shop</title>
</head>

<body class="bg-light">

<%@include file="meta/header.jsp" %>

<div class="container">
    <% HashSet<ProductEntity> catalog = (HashSet<ProductEntity>) session.getAttribute("catalog");
    <% ArrayList<String> errors = (ArrayList<String>) session.getAttribute("errors");
    <%
        if (errors != null) {
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
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <!--<h4>Nessun prodotto disponibile</h4>-->
        <% for (ProductEntity product : catalog) {
        %>

        <!-- CARD PRODUCT -->
        <div class="col-md-6 col-lg-4">
            <div class="card h-100 w-100 wine-card bg-white">

                <!-- IMAGE PRODUCT -->
                <div class="overflow-hidden">
                    <img src="${pageContext.request.contextPath}/images/"
                         class="card-img-top img-fluid zoom" alt="#" width="200px" height="200px">
                </div>

                <!-- CARD BODY -->
                <div class="card-body bg-black" style="color: white; padding: 40px">
                    <h5 class="card-title"><%=product.getProductBrand()%>
                    </h5>
                    <h6 class="card-title"></h6>
                    <h6 class="card-price" style="font-size: 20px">&euro;
                        <%=product.getProductPrice()%>
                    </h6>
                    <div class="mt-4 d-flex justify-content-md-start">
                        <a href="${pageContext.request.contextPath}/cart?toCart=<%=product.getProductId()%>"
                           class="btn btn-primary wine-button">
                            AGGIUNGI AL CARRELLO
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <% }%>
    </div>
</div>

<%@include file="meta/footer.jsp" %>

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