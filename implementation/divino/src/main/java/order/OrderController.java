package order;

import account.CustomerUserEntity;
import cart.CartEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import payment.PaymentEntity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderController", value = "/buy")
public class OrderController extends HttpServlet {
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.orderDAO = (OrderDAO) super.getServletContext().getAttribute("orderDAO");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomerUserEntity customer = (CustomerUserEntity) request.getSession().getAttribute("user");
        CartEntity shoppingCart = (CartEntity) request.getSession().getAttribute("shoppingCart");

        //errore se cliente o carrello sono null
        if (customer == null || shoppingCart == null) {
            response.sendRedirect("/error.jsp");
            return;
        }

        //creazione facade ordine
        OrderFacade orderPlacement = new OrderFacade(orderDAO);

        //logica ordine
        try {
            OrderEntity order = orderPlacement.placeOrder(customer);
            order.setOrderShippingAddress((String) request.getSession().getAttribute("shippingAddress"));
            order.setOrderTotalAmount(shoppingCart.getTotalAmount());
            orderDAO.updateOrder(order);

            //associo item carrello all'ordine
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
