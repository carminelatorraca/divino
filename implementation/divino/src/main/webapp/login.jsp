<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% ServletContext context = request.getServletContext(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/fragments/meta.jsp" %>
    <script src="js/my-scripts.js" type="text/javascript"></script>

    <title>Login</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<div class="container">
    <%
        String exceptions = (String) session.getAttribute("error2");

        if (exceptions != null) {

    %>
    <div class="row justify-content-center">
        <div class="col-lg-12">
            <div class="alert alert-warning wine-errors" role="alert">
                <%=exceptions%>
            </div>
        </div>
    </div>
    <%
        }
    %>
    <div class="row justify-content-evenly g-0">
        <div class="col-lg-4 my-auto">
            <h3>Login</h3>
            <form action="${pageContext.request.contextPath}/login" method="post" onsubmit="return validateSignIn()"
                  novalidate>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="loginEmail" class="form-label wine-label">Email</label>
                        <input class="form-control form-control-lg wine-input" type="email" id="loginEmail" required
                               placeholder="inserisci l'email" name="l-email" value="">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="loginPassword" class="form-label wine-label">Password</label>
                        <input class="form-control form-control-lg wine-input" type="password" id="loginPassword"
                               required
                               placeholder="inserisci la password" name="l-password">
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary wine-button">Accedi</button>
                <br>
                <%
                    if (session.getAttribute("error") != null)
                        out.println(session.getAttribute("error"));
                %>
            </form>
        </div>
        <br>
        <div class="col-lg-4 my-auto">
            <h3>Registrati qui!</h3>
            <form action="${pageContext.request.contextPath}/signup" method="post" onsubmit="return validateSignup()"
                  novalidate>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputFirstname" class="form-label wine-label">Nome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputFirstname" required
                               placeholder="inserisci il nome" name="r-firstname">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputLastname" class="form-label wine-label">Cognome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputLastname" required
                               placeholder="inserisci il cognome" name="r-lastname">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputEmail" class="form-label wine-label">Email</label>
                        <input class="form-control form-control-lg wine-input" type="email" id="inputEmail" required
                               placeholder="inserisci l'email" name="r-email">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputPassword" class="form-label wine-label">Password</label>
                        <div class="col-auto">
                            <input type="password" id="inputPassword" class="form-control form-control-lg wine-input"
                                   aria-describedby="passwordHelp" required placeholder="inserisci la password"
                                   name="r-password">
                        </div>
                        <div class="col-auto">
                            <span id="passwordHelp" class="form-text">
                                Deve essere lunga dai 8-16 caratteri.
                            </span>
                        </div>
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary wine-button">Registrati</button>
                <button type="reset" class="btn btn-secondary wine-button">Cancella</button>
                <br><br>
                <% if (session.getAttribute("error2") != null)
                    out.println(session.getAttribute("error2")); %>
            </form>
        </div>
    </div>
    <br>
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