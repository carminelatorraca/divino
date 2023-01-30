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

        PaymentEntity payment = new PaymentEntity();
        payment.setPaidAmount(orderToPay.getOrderTotalAmount());
        payment.setPaymentDescription(orderToPay.getOrderNumber());
        payment.setPaymentMethod("generic");
        payment.setPaymentStatus("paid");

        request.setAttribute("payment", payment);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
