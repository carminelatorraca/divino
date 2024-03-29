<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="account.CustomerUserEntity" %>
<%@ page import="java.util.ArrayList" %>

<% ServletContext context = request.getServletContext(); %>

<% CustomerUserEntity user = (CustomerUserEntity) session.getAttribute("user"); %>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>I miei indirizzi</title>
</head>
<body>
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
    <h3>I miei indirizzi</h3>
    <div class="row align-items-center">
        <%
            if (user != null) {
        %>
        <div class="col-md-6">
            <div class="card" style="width: 18rem; border-radius: 0">
                <div class="card-body">
                    <h5 class="card-title"><%=user.getFirstName() + " " + user.getLastName()%>
                    </h5>
                    <h6 class="card-subtitle mb-2 text-muted">Indirizzo di spedizione</h6>
                    <p class="card-text" style="margin-bottom: 0"><%=user.getShippingAddresses()%>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-md-6" id="update-form">
            <form action="${pageContext.request.contextPath}/account" method="post">
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputAddress" class="form-label wine-label">Indirizzo</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputAddress"
                               placeholder="" name="a-address">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputAddress2" class="form-label wine-label">Indirizzo aggiuntivo</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputAddress2"
                               placeholder="Scala, piano ecc." name="a-address-sec">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputCap" class="form-label wine-label">Cap</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputCap"
                               placeholder="" name="a-cap-code">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputCity" class="form-label wine-label">Citta</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputCity"
                               placeholder="" name="a-city">
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary wine-button">Aggiorna</button>
                <button type="reset" class="btn btn-primary wine-button">Annulla</button>
            </form>
        </div>
        <% } %>
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