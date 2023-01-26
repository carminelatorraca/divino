<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.SQLException" %>

<% DataSource shopDs = (DataSource) pageContext.getServletContext().getAttribute("DataSource"); %>
<% ProductsDAO productsDAO = new ProductsDAO(shopDs); %>
<% ProductImagesDAO imagesDAO = new ProductImagesDAO(shopDs); %>
<% CategoriesDAO categoriesProdDAO = new CategoriesDAO(shopDs); %>

<% ArrayList<String> errors = (ArrayList<String>) session.getAttribute("shop-errors"); %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="meta/meta.jsp" %>
    <script src="${pageContext.request.contextPath}/js/custom-script.js" type="text/javascript"></script>

    <title>Shop</title>
</head>
<body class="bg-light">
<%@include file="meta/header.jsp" %>
<div class="container">
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
        <%
            Collection<?> products = null;
            try {
                products = productsDAO.getActivate("productId");
                if (products != null) {
                    Iterator<?> iterator = products.iterator();
                    int i = 0;
                    while (iterator.hasNext()) {
                        Products product = (Products) iterator.next();
                        Categories prodCategory = categoriesProdDAO.doRetrieveByKey(product.getCategoryId());
        %>
        <div class="col-md-6 col-lg-4">
            <div class="card h-100 w-100 wine-card bg-white">
                <%
                    try {
                        product.setImageUri(imagesDAO.getImages(product));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    if (!product.getImageUri().isEmpty()) {
                %>
                <div class="overflow-hidden">
                    <img src="${pageContext.request.contextPath}/images/<%=product.getProductId()%>/<%=product.getImageUri().get(0)%>"
                         class="card-img-top img-fluid zoom" alt="<%=product.getTitle()%>" width="200px" height="200px">
                </div>
                <% } else { %>
                <img src="${pageContext.request.contextPath}/images/default/no_image_available.jpg"
                     class="card-img-top img-fluid"
                     alt="<%=product.getTitle()%>" width="200px" height="200px">
                <% } %>
                <div class="card-body bg-black" style="color: white; padding: 40px">
                    <h5 class="card-title"><%=product.getTitle()%>
                    </h5>
                    <h6 class="card-title"><%=prodCategory.getCategoryName()%>
                    </h6>
                    <h6 class="card-price" style="font-size: 20px">&euro; <%=product.getPrice()%>
                    </h6>
                    <div class="mt-4 d-flex justify-content-md-start">
                        <a href="${pageContext.request.contextPath}/store?toCart=<%=product.getProductId()%>"
                           class="btn btn-primary wine-button">
                            AGGIUNGI AL CARRELLO
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        } else { %>
        <h4>Nessun prodotto disponibile</h4>
        <% } %>
        <% } catch (SQLException ex) {
            ex.printStackTrace();
        }
        %>
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