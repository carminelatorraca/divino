<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% DataSource cartDS = (DataSource) pageContext.getServletContext().getAttribute("DataSource"); %>
<% ProductsDAO prodCartDAO = new ProductsDAO(cartDS); %>
<% ProductImagesDAO productImagesDAO = new ProductImagesDAO(cartDS); %>
<%
    ArrayList<Cart> cart = (ArrayList<Cart>) session.getAttribute("cart");
    List<Cart> productsCart = null;
    if (cart != null) {
        try {
            productsCart = prodCartDAO.getProductsCart(cart);
            double total = prodCartDAO.getCartTotal(cart);
            request.setAttribute("cart", cart);
            request.setAttribute("total", total);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
%>
<% ArrayList<String> errors = (ArrayList<String>) session.getAttribute("cart-errors"); %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>Carrello</title>
</head>
<body class="bg-light">

<%@include file="/fragments/header.jsp" %>

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
    <div class="row justify-content-evenly">
        <div class="col-lg-8 wine-margin">
            <h3>Carrello</h3>
            <%
                if (cart == null || cart.size() == 0) { %>
            <br>
            <h4>Hey, riempi il calice!</h4>
            <% } else {
                for (Cart product : productsCart) {
            %>
            <div class="card mb-3 wine-card-cart bg-white">
                <div class="row g-0 align-items-center">
                    <div class="col-md-2" style="text-align: center">
                        <a href="${pageContext.request.contextPath}/cartremove?remove=<%=product.getProductId()%>"
                           class="material-symbols-outlined" id="wine-cart-remove" style="color: #dcbe84">close</a>
                    </div>
                    <div class="col-md-2">
                        <%
                            try {
                                ArrayList<ProductImages> images = new ArrayList<>();
                                images = productImagesDAO.getProductImages(product);
                                for (ProductImages image : images) {
                                    if (image.getProductId() == product.getProductId()) {
                        %>
                        <img src="${pageContext.request.contextPath}/images/<%=image.getProductId()%>/<%=image.getPathName()%>"
                             class="card-img-top img-fluid"
                             alt="no_image" width="200px" height="200px">
                        <%
                                }
                            }
                        %>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title"><%=product.getTitle()%>
                            </h5>
                            <p class="card-text" style="font-size: 17px">
                                &euro; <%=product.getPrice() * product.getQuantity()%>
                            </p>
                            <br>
                            <a class="btn btn-light"
                               href="${pageContext.request.contextPath}/cartquantity?action=inc&productId=<%=product.getProductId()%>">+</a>
                            <input type="hidden" name="productId" value="<%=product.getProductId()%>">
                            <input type="text" name="quantity" value="<%=product.getQuantity()%>" readonly>
                            <a class="btn btn-light"
                               href="${pageContext.request.contextPath}/cartquantity?action=dec&productId=<%=product.getProductId()%>">-</a>
                            <br>
                            <!-- Button for remove
                            <a href="${pageContext.request.contextPath}/cartremove?remove=<%=product.getProductId()%>"
                               class="btn">
                                Rimuovi
                            </a>
                            -->
                            <p class="card-text">
                                <small class="text-muted">Ultimo aggiornamento il <%=product.getUpdatedAt()%>
                                </small>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <% } catch (SQLException ex) {
                ex.printStackTrace();
            }
            }
            } %>
        </div>
        <div class="col-lg-4 h-100 bg-black wine-margin" id="cart-total">
            <h3 style="color: white">Totale</h3>
            <table class="table" style="color: white">
                <tbody class="wine-label">
                <tr>
                    <th>Subtotale</th>
                    <td class="wine-cart-total"><h6>&euro; ${(total>0)?total:0}</h6></td>
                </tr>
                <tr>
                    <th>Spedizione</th>
                    <td class="wine-cart-total"><h6>Gratuita</h6></td>
                </tr>
                <tr>
                    <th>Totale</th>
                    <td class="wine-cart-total"><h6>&euro; ${(total>0)?total:0}</h6></td>
                </tr>
                </tbody>
            </table>
            <% request.setAttribute("cart", cart);%>
            <a type="button" class="btn btn-success wine-button" href="${pageContext.request.contextPath}/checkout">PROCEDI
                CON L'ORDINE</a>
        </div>
    </div>
</div>

<%@include file="meta/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
        integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
        crossorigin="anonymous"></script>

</body>
</html>





