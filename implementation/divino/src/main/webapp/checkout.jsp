<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="cart.CartEntity" %>

<%@ page import="cart.CartItemEntity" %>
<%@ page import="account.AccountEntity" %>
<%@ page import="account.CustomerUserEntity" %>

<%
    CustomerUserEntity user = (CustomerUserEntity) session.getAttribute("user");
    if (user == null || !user.getRole().equals(AccountEntity.Role.CUSTOMERUSER)) {
        String errors = "non sei autorizzato";
        session.setAttribute("ow-errors", errors);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

%>
<%
    CartEntity shoppingCart = (CartEntity) request.getSession().getAttribute("shoppingCart");
    double total = shoppingCart.getTotalAmount();
    request.setAttribute("total", total);
%>

<% ArrayList<String> errors = (ArrayList<String>) session.getAttribute("order-errors"); %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/fragments/meta.jsp" %>
    <script src="js/my-scripts.js" type="text/javascript"></script>
    <title>Checkout</title>
</head>
<body>

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

    <!-- FORM INDIRIZZO -->
    <form action="${pageContext.request.contextPath}/checkout" method="post" onsubmit="return validatePayment()">
        <div class="row">

            <!-- DETTAGLIO INDIRIZZI -->
            <div class="col-md-6">
                <h3>Dati Fatturazione</h3>

                <!-- NUOVO INDIRIZZO -->

                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-firstname" class="form-label wine-label">Nome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-firstname"
                               placeholder="" name="c-firstname" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-lastname" class="form-label wine-label">Cognome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-lastname"
                               placeholder="" name="c-lastname" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-state" class="form-label wine-label">Stato/regione</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-state"
                               placeholder="" name="c-state" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-address" class="form-label wine-label">Via e Numero</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-address"
                               placeholder="Via/Piazza e Numero Civico" name="c-address" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-address-cap" class="form-label wine-label">CAP</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-address-cap"
                               placeholder="" name="c-address-cap" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-address-city" class="form-label wine-label">Città</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-address-city"
                               placeholder="" name="c-address-city" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-address-prov" class="form-label wine-label">Provincia</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-address-prov"
                               placeholder="" name="c-address-prov" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-phone" class="form-label wine-label">Cellulare</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-phone" required
                               placeholder="" name="c-phone">
                    </div>
                </div>

            </div>

            <!-- DETTAGLIO ORDINE IN CORSO  -->
            <div class="col-md-6">
                <h3>Il tuo ordine</h3>
                <div class="row">
                    <table class="table wine-checkout-total">
                        <thead>
                        <tr class="wine-label">
                            <th scope="col">PRODOTTO</th>
                            <th scope="col">SUBTOTALE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (CartItemEntity cartItem : shoppingCart.getShoppingCart().values()) {
                        %>
                        <tr>
                            <th>
                                <%=cartItem.getProduct().getProductDescription()%> x<%=cartItem.getProductQuantity()%>
                            </th>
                            <td>
                                <%=cartItem.getProduct().getProductPrice() * cartItem.getProductQuantity()%>
                            </td>
                        </tr>
                        <% } %>
                        <tr>
                            <th class="wine-label">Subtotale</th>
                            <td>${total}</td>
                        </tr>
                        <tr>
                            <th class="wine-label">Spedizione</th>
                            <td>GRATUITA</td>
                        </tr>
                        <tr>
                            <th class="wine-label" style="font-size: 20px">Totale</th>
                            <td style="font-size: 20px">${total}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="input-credit-card" class="form-label wine-label">Numero carta</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-credit-card"
                               required
                               placeholder="1234 1234 1234 1234" name="c-card-number">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-6">
                        <label for="input-card-expire" class="form-label wine-label">Data di scadenza</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-card-expire"
                               required
                               placeholder="MM/AA" name="c-card-expire">
                    </div>
                    <div class="col-lg-6">
                        <label for="input-card-cvc" class="form-label wine-label">Codice CVC</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-card-cvc" required
                               placeholder="CVC" name="c-card-cvc">
                    </div>
                </div>
                <br>
                <div class="row justify-content-center">
                    <div class="col-lg-12 align-items-center">
                        <% request.setAttribute("cart", shoppingCart);%>
                        <button type="submit" class="btn btn-success wine-button">CONFERMA ORDINE</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
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
