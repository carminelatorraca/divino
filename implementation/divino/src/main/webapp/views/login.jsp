<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- //ArrayList<String> errors = (ArrayList<String>) session.getAttribute("reglog-errors"); -->

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="meta/meta.jsp" %>
    <script src="${pageContext.request.contextPath}/js/custom-script.js" type="text/javascript"></script>

    <title>Login</title>
</head>
<body>
<%@include file="meta/header.jsp" %>
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
    <div class="row justify-content-evenly g-0">
        <div class="col-lg-4 my-auto">
            <h3>Login</h3>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="loginUsername" class="form-label wine-label">Email</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="loginUsername" required
                               placeholder="inserisci l'username" name="l-username">
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
            </form>
        </div>
        <br>
        <div class="col-lg-4 my-auto">
            <h3>Registrati qui!</h3>
            <form action="${pageContext.request.contextPath}/register" method="post" novalidate>
                <div class="form-group row">
                    <div class="col-lg-12">
                        <label for="inputFirstname" class="form-label wine-label">Nome</label>
                        <input class="form-control form-control-lg wine-input" type="text" id="inputFirstname" required
                               placeholder="inserisci il nome" name="r-firstname" onchange="validateFName()">
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
                        <label for="inputUsername" class="form-label wine-label">Username</label>
                        <div class="input-group">
                            <span class="input-group-text wine-no-border-radius" id="span">@</span>
                            <input class="form-control form-control-lg wine-input" type="text" id="inputUsername"
                                   required
                                   placeholder="scegli l'username" name="r-username">
                            <!--<button class="btn btn-outline-secondary wine-no-border-radius" type="button"
                                    id="check-username">Check
                            </button>-->
                        </div>
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
                                Deve essere lunga almeno 8-16 caratteri.
                            </span>
                        </div>
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary wine-button">Registrati</button>
                <button type="reset" class="btn btn-secondary wine-button">Cancella</button>
            </form>
        </div>
    </div>
    <br>
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