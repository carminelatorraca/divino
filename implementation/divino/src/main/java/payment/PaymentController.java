package payment;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import order.OrderEntity;

import java.io.IOException;

@WebServlet(name = "PaymentController", value = "/payment")
public class PaymentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderEntity orderToPay = (OrderEntity) request.getAttribute("order");

        if (orderToPay == null) {
            response.sendError(900, "ordine nullo");
            return;
        }

        PaymentEntity payment = new PaymentEntity();
        payment.setPaidAmount(orderToPay.getOrderTotalAmount());
        payment.setPaymentDescription(orderToPay.getOrderProducts().toString());
        payment.setPaymentMethod("Card Payment");
        payment.setPaymentStatus("Paid");
        payment.setOrderNumber(orderToPay.getOrderNumber());

        request.setAttribute("payment", payment);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
