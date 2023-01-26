<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Users admin = (Users) session.getAttribute("user"); %>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/views/meta/meta.jsp" %>

    <title>Dashboard - BackOffice</title>
</head>
<body>
<%@include file="/views/meta/header.jsp" %>

<div class="container">
    <div class="row justify-content-center">
        <div class="row">
            <h1>Benvenuto, <%=admin.getFirstName()%>
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
                                   href="${pageContext.request.contextPath}/views/admin/product-upload.jsp">Upload
                                    prodotti</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active text-white"
                                   href="${pageContext.request.contextPath}/views/admin/order-view.jsp">Gestione ordini</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active text-white"
                                   href="${pageContext.request.contextPath}/views/admin/product-view.jsp">Lista prodotti</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active text-white"
                                   href="${pageContext.request.contextPath}/views/admin/categories-settings.jsp">Gestione
                                    categorie</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active text-white"
                                   href="${pageContext.request.contextPath}/views/admin/customer-view.jsp">Gestione utenti</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</div>

<%@include file="/views/meta/footer.jsp" %>

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

