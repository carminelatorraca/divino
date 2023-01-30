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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderFacade orderPlacement = new OrderFacade();

        CustomerUserEntity customer = (CustomerUserEntity) request.getSession().getAttribute("user");
        CartEntity shoppingCart = (CartEntity) request.getAttribute("shoppingCart");

        //gestire eccezione se cliente o carrello sono null
        //if (customer == null || shoppingCart == null) {}

        //logica ordine
        try {
            OrderEntity order = orderPlacement.placeOrder(customer);
            orderPlacement.joinProducts(shoppingCart, order);

            request.setAttribute("order", order);
            RequestDispatcher paymentRequest = request.getRequestDispatcher("/payment");
            paymentRequest.include(request, response);

            //verifica se il pagamento è andato a buon fine
            PaymentEntity payment = (PaymentEntity) request.getAttribute("payment");
            if (payment != null && payment.getPaymentStatus().equals("paid")) {
                orderPlacement.placePayment(payment);
                orderPlacement.placeOrderItems(order);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
