<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.ArrayList" %>

<% DataSource ordersDS = (DataSource) pageContext.getServletContext().getAttribute("DataSource"); %>
<% OrdersDAO ordersDAO = new OrdersDAO(ordersDS); %>
<% OrderProductDAO orderProductDAO = new OrderProductDAO(ordersDS); %>

<%
    Users user = (Users) request.getSession().getAttribute("user");
    //Orders order = (Orders) request.getSession().getAttribute("order");
    ArrayList<Orders> orderUser = new ArrayList<>();

    try {
        orderUser = ordersDAO.doRetrieveByUser(user);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@include file="/views/meta/meta.jsp" %>

    <title>I miei ordini</title>
</head>
<body>
<%@include file="/views/meta/header.jsp" %>

<div class="container">
    <h3>I miei ordini</h3>
    <%
        for (Orders o : orderUser) {
    %>
    <div class="row row-cols-1 row-cols-md-3 g-4 mb-3">
        <div class="col-md-4">
            <div class="card mb-3 wine-card-cart bg-white">
                <div class="card-body">
                    <h5 class="card-title">
                        <strong>Numero dell'ordine: <%=o.getOrderId()%>
                        </strong>
                    </h5>
                    <p class="card-text" style="font-size: 17px">
                        Data ordine: <%=o.getCreatedAt()%>
                    </p>
                    <p class="card-text" style="font-size: 17px">
                        Totale: <%=o.getTotal()%>
                    </p>
                    <br>
                    <p class="card-text">
                        Stato ordine: <%=o.getOrderStatus()%>
                    </p>
                    <a type="button" class="btn btn-light" href="${pageContext.request.contextPath}/pdfgenerate?orderId=<%=o.getOrderId()%>">Fattura</a>
                </div>
            </div>
        </div>
        <%
            try {
                Collection<OrderProduct> orderProducts = orderProductDAO.getOrderProducts(o.getOrderId());
                for (OrderProduct op : orderProducts) {
                    ProductImagesDAO imagesDAO = new ProductImagesDAO(ordersDS);
                    ArrayList<ProductImages> images = imagesDAO.getProdImagesForId(op.getProductId());
                    for (ProductImages pimages : images) {
        %>
        <div class="col-md-6 col-lg-2">
            <div class="card h-100 w-100 bg-white mb-3" style="border-radius: 0;">
                <img src="${pageContext.request.contextPath}/images/<%=pimages.getProductId()%>/<%=pimages.getPathName()%>"
                     class="card-img-top img-fluid"
                     alt="no_image" width="200px" height="200px">
                <div class="card-body">
                    <p class="card-text"><%=op.getProductTitle()%>
                    </p>
                    <p class="card-text">Quantità: <%=op.getQuantity()%>
                    </p>
                </div>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
    <%
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    %>
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