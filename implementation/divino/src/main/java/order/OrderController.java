package order;

import account.CustomerUserEntity;
import cart.CartEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import payment.PaymentEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "OrderController", value = "/buy")
public class OrderController extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderDAO = (OrderDAO) super.getServletContext().getAttribute("orderDAO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("mode").equalsIgnoreCase("showOrders")) {
            try {
                ArrayList<OrderEntity> orders = orderDAO.retrieveAllOrders();
                request.getSession().setAttribute("orders", orders);
                response.sendRedirect("./admin/order-view.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("mode"));
        System.out.println(request.getParameter("orderID"));
        if (request.getParameter("mode").equalsIgnoreCase("updateStatus")) {
            Integer orderID = Integer.valueOf(request.getParameter("orderID"));
            String newStatus = request.getParameter("p_status");
            try {
                OrderEntity order = orderDAO.retrieveOrder(orderID);
                System.out.println(order.getOrderTotalAmount());
                System.out.println(order.getOrderStatus());
                order.setOrderStatus(newStatus);
                System.out.println(order.getOrderStatus());
                orderDAO.updateOrder(order);
                request.getSession().setAttribute("orders", orderDAO.retrieveAllOrders());
                response.sendRedirect("./admin/order-view.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            OrderFacade orderPlacement = new OrderFacade(orderDAO);

            CustomerUserEntity customer = (CustomerUserEntity) request.getSession().getAttribute("user");
            CartEntity shoppingCart = (CartEntity) request.getSession().getAttribute("shoppingCart");

            //gestire eccezione se cliente o carrello sono null
            //if (customer == null || shoppingCart == null) {}

            //logica ordine
            try {
                OrderEntity order = orderPlacement.placeOrder(customer);
                order.setOrderShippingAddress((String) request.getSession().getAttribute("shippingAddress"));
                order.setOrderTotalAmount(shoppingCart.getTotalAmount());
                orderDAO.updateOrder(order);
                orderPlacement.joinProducts(shoppingCart, order);
                request.getSession().removeAttribute("shoppingCart");

                request.setAttribute("order", order);
                RequestDispatcher paymentRequest = request.getRequestDispatcher("/payment");
                paymentRequest.include(request, response);

                //verifica se il pagamento è andato a buon fine
                PaymentEntity payment = (PaymentEntity) request.getAttribute("payment");
                if (payment != null && payment.getPaymentStatus().equals("paid")) {
                    orderPlacement.placePayment(payment);
                    orderPlacement.placeOrderItems(order);
                    response.sendRedirect("./order-confirm.jsp");
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
