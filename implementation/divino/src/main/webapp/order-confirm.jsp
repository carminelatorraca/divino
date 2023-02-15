<%@ page import="cart.CartItemEntity" %>
<%@ page import="cart.CartEntity" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/fragments/meta.jsp" %>
    <title>Ordine Confermato</title>
</head>
<body class="bg-light">

<%@include file="/fragments/header.jsp" %>

<div class="container">
    <div class="row justify-content-evenly">
        <h4>Ordine confermato!</h4>
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