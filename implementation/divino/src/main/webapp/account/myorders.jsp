<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="account.AccountEntity" %>
<%@ page import="java.util.Collection" %>
<%@ page import="order.OrderEntity" %>
<%@ page import="order.OrderItemEntity" %>
<%@ page import="java.util.HashSet" %>

<%
    AccountEntity account = (AccountEntity) session.getAttribute("user");
    if (account == null || !account.getRole().equals(AccountEntity.Role.CUSTOMERUSER)) {
        String errors = "non sei autorizzato";
        session.setAttribute("ow-errors", errors);
        request.getRequestDispatcher("./login.jsp").forward(request, response);
    }
    //gestire ordini
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/fragments/meta.jsp" %>

    <title>I miei ordini</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>

<div class="container">
    <h3>I miei ordini</h3>
    <%
        HashSet<OrderEntity> orders = (HashSet<OrderEntity>) session.getAttribute("myOrder");
        System.out.println(orders.size());
        if (!orders.isEmpty()) {
            for (OrderEntity order : orders) {
    %>
    <div class="row row-cols-1 row-cols-md-3 g-4 mb-3">
        <div class="col-md-4">
            <div class="card mb-3 wine-card-cart bg-white">
                <div class="card-body">
                    <h5 class="card-title">
                        <strong>Numero dell'ordine: <%=order.getOrderNumber()%>
                        </strong>
                    </h5>
                    <p class="card-text" style="font-size: 17px">
                        ID Pagamento: <%=order.getOrderPayment()%>
                    </p>
                    <p class="card-text" style="font-size: 17px">
                        Totale Ordine: <%=order.getOrderTotalAmount()%>
                    </p>
                    <br>
                    <p class="card-text">
                        Stato Ordine: <%=order.getOrderStatus()%>
                    </p>
                </div>
            </div>
        </div>
        <%
            for (OrderItemEntity orderItem : order.getOrderProducts()) {
        %>
        <div class="col-md-6 col-lg-2">
            <div class="card h-100 w-100 bg-white mb-3" style="border-radius: 0;">
                <img src="${pageContext.request.contextPath}/images/<%//=%>"
                     class="card-img-top img-fluid"
                     alt="no_image" width="200px" height="200px">
                <div class="card-body">
                    <p class="card-text"><%//%>
                    </p>
                    <p class="card-text">Quantità: <%=orderItem.getProductQuantity()%>
                    </p>
                </div>
            </div>
        </div>
        <% }%>
    </div>
    <%
            }
        }
    %>
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