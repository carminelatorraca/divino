<%@ page import="account.AccountEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    AccountEntity user = (AccountEntity) request.getSession().getAttribute("user");
    if (user == null || !user.getRole().equals(AccountEntity.Role.WAREHOUSEUSER)) {
        String errors = "non sei autorizzato";
        session.setAttribute("ow-errors", errors);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>Dashboard - Warehouse</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>

<div class="container">
    <div class="row justify-content-center">
        <div class="row">
            <h1>Benvenuto, <%=user.getEmail()%>
                <a class="nav-link" style="display: inline" href="${pageContext.request.contextPath}/logout">
                    <span class="material-symbols-outlined">logout</span></a>
            </h1>
        </div>
        <br>
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <nav class="navbar navbar-expand-lg bg-black">
                    <div class="container-fluid" style="display: flow-root">
                        <ul class="navbar-nav justify-content-center">
                            <li class="nav-item">
                                <a class="nav-link active text-white"
                                   href="${pageContext.request.contextPath}/order-manager?mode=showOrders">Gestione ordini</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
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


