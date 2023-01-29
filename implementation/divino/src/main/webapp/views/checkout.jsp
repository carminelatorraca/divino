<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="meta/meta.jsp" %>
    <script src="${pageContext.request.contextPath}/js/custom-script.js" type="text/javascript"></script>

    <title>Checkout</title>
</head>
<body>
<%@include file="meta/header.jsp" %>
<div class="container">

    <form action="${pageContext.request.contextPath}/order" method="post" onsubmit="return validatePayment()">
        <div class="row">
            <div class="col-md-6">
                <h3>Dati Fatturazione</h3>
                <div class="form-check">

                    <input class="form-check-input" type="radio" name="c-choose" id="choose-old-address" checked
                           value="oldaddress">
                    <label class="form-check-label" for="choose-old-address">L'indirizzo di fatturazione coincide con quello di spedizione</label>

                    <div class="card" style="width: 18rem; border-radius: 0">
                        <div class="card-body">
                            <h5 class="card-title">
                            </h5>
                            <h6 class="card-subtitle mb-2 text-muted">Indirizzo</h6>
                            <p class="card-text" style="margin-bottom: 0">
                            </p>
                            <p class="card-text" style="margin-bottom: 0">
                            </p>
                            <p class="card-text" style="margin-bottom: 0">
                            </p>
                        </div>
                    </div>
                    <% } %>
                </div>
                <br>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="c-choose" id="choose-new-address"
                           value="newaddress">
                    <label class="form-check-label" for="choose-new-address">
                        Aggiungi nuovo indirizzo
                    </label>
                </div>

                <br>
                <div id="choose-hidden" style="display: none">
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-firstname" class="form-label wine-label">nome</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-firstname"
                                   placeholder="" name="c-firstname">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-lastname" class="form-label wine-label">cognome</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-lastname"
                                   placeholder="" name="c-lastname">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-state" class="form-label wine-label">stato/regione</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-state"
                                   placeholder="" name="c-state">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-address" class="form-label wine-label">via e numero</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-address"
                                   placeholder="Via/Piazza e Numero Civico" name="c-address">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-address-sec" class="form-label wine-label">indirizzo secondario</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-address-sec"
                                   placeholder="Appartamento, Scala, ecc." name="c-address-sec">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-address-cap" class="form-label wine-label">cap</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-address-cap"
                                   placeholder="" name="c-address-cap">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-address-city" class="form-label wine-label">città</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-address-city"
                                   placeholder="" name="c-address-city">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-address-prov" class="form-label wine-label">provincia</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-address-prov"
                                   placeholder="" name="c-address-prov">
                        </div>
                    </div>
                    <!--<div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-phone" class="form-label wine-label">Telefono</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-phone" required
                                   placeholder="" name="c-phone">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-lg-12">
                            <label for="input-email" class="form-label wine-label">Email</label>
                            <input class="form-control form-control-lg wine-input" type="text" id="input-email" required
                                   placeholder="" name="c-email">
                        </div>
                    </div>-->
                </div>
            </div>
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
                            for
                            (
                            Cart
                            product
                            :
                            productsCart
                            )
                            {
                        %>
                        <tr>
                            <th>
                                <%=product
                                    .
                                    getTitle
                                    (
                                    )%> x<%=product
                                .
                                getQuantity
                                (
                                )%>
                            </th>
                            <td>
                                <%=product
                                    .
                                    getPrice
                                    (
                                    )
                                    *
                                    product
                                    .
                                    getQuantity
                                    (
                                    )%>
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
                        <label for="input-card-cvc" class="form-label wine-label">Codice cvc</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="input-card-cvc" required
                               placeholder="CVC" name="c-card-cvc">
                    </div>
                </div>
                <br>
                <div class="row justify-content-center">
                    <div class="col-lg-12 align-items-center">
                        <% request
                            .
                            setAttribute
                            (
                            "cart"
                            ,
                            cart
                            )
                            ;%>
                        <button type="submit" class="btn btn-success wine-button">CONFERMA ORDINE</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="meta/footer.jsp" %>

<script>
    $(document).ready(
        $("#choose-new-address").on("click", function () {
                $("#choose-hidden").css("display", "")
            }
        )
    )

    $(document).ready(
        $("#choose-old-address").on("click", function () {
                $("#choose-hidden").css("display", "none")
            }
        )
    )
</script>

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
