<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dewine.model.Users" %>
<%@ page import="com.dewine.model.UsersDAO" %>
<%@ page import="java.util.ArrayList" %>

<% DataSource addressDS = (DataSource) pageContext.getServletContext().getAttribute("DataSource"); %>
<% ArrayList<String> errors = (ArrayList<String>) session.getAttribute("account-errors"); %>

<% Users user = (Users) session.getAttribute("user"); %>
<% UsersDAO usersDAO = new UsersDAO(addressDS); %>
<%
    try {
        user = usersDAO.doRetrieveByKey(user.getUserId());
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
%>
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
    <h3>I miei dati</h3>
    <div class="row align-items-center">
        <%
            if (user != null) {
        %>
        <div class="col-md-6">
            <div class="card" style="width: 18rem; border-radius: 0">
                <div class="card-body">
                    <h5 class="card-title"><%=user.getFirstName() + " " + user.getLastName()%>
                    </h5>
                    <h6 class="card-subtitle mb-2 text-muted">Dati personali</h6>
                    <p class="card-text" style="margin-bottom: 0">Username: <%=user.getUsername()%>
                    </p>
                    <p class="card-text" style="margin-bottom: 0">Nome: <%=user.getFirstName()%>
                    </p>
                    <p class="card-text" style="margin-bottom: 0">Cognome: <%=user.getLastName()%>
                    </p>
                    <p class="card-text" style="margin-bottom: 0">Email: <%=user.getEmail()%>
                    </p>
                    <p class="card-text" style="margin-bottom: 0">Cellulare: <%=user.getMobilePhone()%>
                    </p>
                    <br>
                </div>
            </div>
        </div>
        <div class="col-md-6" id="update-form">
            <form action="${pageContext.request.contextPath}/account" method="post">
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputAddress0" class="form-label wine-label">Username</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputAddress0"
                               placeholder="username" name="a-username">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputAddress" class="form-label wine-label">Nome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputAddress"
                               placeholder="nome" name="a-firstname">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputAddress2" class="form-label wine-label">Cognome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputAddress2"
                               placeholder="cognome" name="a-lastname">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputCap" class="form-label wine-label">Email</label>
                        <input class="form-control form-control-lg wine-input" type="email" id="inputCap"
                               placeholder="email" name="a-email">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputCity" class="form-label wine-label">Cellulare</label>
                        <input class="form-control form-control-lg wine-input" type="number" id="inputCity"
                               placeholder="" name="a-telephone">
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