<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% ServletContext context = request.getServletContext(); %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="account.AccountEntity" %>
<%@ page import="account.CustomerUserEntity" %>

<%
    CustomerUserEntity account = (CustomerUserEntity) session.getAttribute("user");
    if (account == null || !account.getRole().equals(AccountEntity.Role.CUSTOMERUSER)) {
        String errors = "non sei autorizzato";
        session.setAttribute("ow-errors", errors);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }%>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>I miei dati</title>
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
    <h3>I miei dati</h3>
    <div class="row align-items-center">
        <%
            if (account != null) {
        %>
        <div class="col-md-6">
            <div class="card" style="width: 18rem; border-radius: 0">
                <div class="card-body">
                    <h5 class="card-title"><%=account.getFirstName() + " " + account.getLastName()%>
                    </h5>
                    <h6 class="card-subtitle mb-2 text-muted">Dati personali</h6>
                    <p class="card-text" style="margin-bottom: 0">Email: <%=account.getEmail()%>
                    </p>
                    <p class="card-text" style="margin-bottom: 0">Nome: <%=account.getFirstName()%>
                    </p>
                    <p class="card-text" style="margin-bottom: 0">Cognome: <%=account.getLastName()%>
                    </p>
                    <br>
                </div>
            </div>
        </div>
        <div class="col-md-6" id="update-form">
            <form action="${pageContext.request.contextPath}/account" method="post">
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputAddress" class="form-label wine-label">Nome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputAddress"
                               placeholder="" name="a-firstname" value="<% out.println(account.getFirstName());%>">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputAddress2" class="form-label wine-label">Cognome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputAddress2"
                               placeholder="" name="a-lastname" value="<% out.println(account.getLastName());%>">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputCap" class="form-label wine-label">Email</label>
                        <input class="form-control form-control-lg wine-input" type="email" id="inputCap"
                               placeholder="" name="a-email" value="<% out.print(account.getEmail());%>">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="oldPass" class="form-label wine-label">Vecchia Password</label>
                        <input class="form-control form-control-lg wine-input" type="password" id="oldPass"
                               placeholder="Vecchia Password" name="a-oldPass" >
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="newPass" class="form-label wine-label">Nuova Password</label>
                        <input class="form-control form-control-lg wine-input" type="password" id="newPass"
                               placeholder="Nuova Password" name="a-newPass">
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
        crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
        crossorigin="anonymous">
</script>

</body>
</html>