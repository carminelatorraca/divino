<%@ page import="java.util.Collection" %>
<%@ page import="cart.CartEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cart.CartItemEntity" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% ServletContext context = request.getServletContext(); %>
<% CartEntity shoppingCart = new CartEntity(); %>
<% if (session.getAttribute("shoppingCart") == null)
    session.setAttribute("shoppingCart", shoppingCart);
else
    shoppingCart = (CartEntity) session.getAttribute("shoppingCart"); %>
<% session.setAttribute("total", ""); %>

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

    <div class="row justify-content-evenly">
        <div class="col-lg-8 wine-margin">
            <h3>Carrello</h3>

            <%
                if (shoppingCart.getShoppingCart() == null) { %>
            <br>
            <h4>Hey, riempi il calice!</h4>
            <% } else {
                for (CartItemEntity product : shoppingCart.getShoppingCart().values()) {
            %>
            <div class="card mb-3 wine-card-cart bg-white">
                <div class="row g-0 align-items-center">

                    <div class="col-md-2" style="text-align: center">
                        <a href="${pageContext.request.contextPath}/cart?mode=remove&productid=<%=product.getProduct().getProductId()%>"
                           class="material-symbols-outlined" id="wine-cart-remove" style="color: #dcbe84">close</a>
                    </div>

                    <div class="col-md-2">
                        <img src="${pageContext.request.contextPath}/images/<%=product.getProduct().getImagePath()%>"
                             class="card-img-top img-fluid"
                             alt="no_image" width="200px" height="200px">
                    </div>

                    <!-- CARD PRODOTTO -->
                    <div class="col-md-8">
                        <div class="card-body">

                            <!-- price -->
                            <h5 class="card-title"><%=product.getProduct().getProductBrand()%>
                            </h5>
                            <!-- quantity -->
                            <p class="card-text" style="font-size: 17px">
                                &euro; <%=product.getProduct().getProductPrice() * product.getProductQuantity()%>
                            </p>

                            <!-- INCREMENTA QUANTITA PRODOTTO -->
                            <a class="btn btn-light"
                               href="${pageContext.request.contextPath}/cart?mode=plus&productID=<%=product.getProduct().getProductId()%>">+</a>

                            <input type="hidden" name="productId" value="<%=product.getProduct().getProductId()%>">
                            <input type="text" name="quantity" value="<%=product.getProductQuantity()%>" readonly>

                            <!-- DECREMANTA QUANTITA PRODOTTO -->
                            <a class="btn btn-light"
                               href="${pageContext.request.contextPath}/cart?mode=min&productid=<%=product.getProduct().getProductId()%>">-</a>
                        </div>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>

        <!-- DETTAGLIO COSTI -->
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

            <a type="button" class="btn btn-success wine-button" href="${pageContext.request.contextPath}/checkout">PROCEDI
                CON L'ORDINE</a>
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