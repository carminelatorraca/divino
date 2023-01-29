package order;

import account.CustomerUserEntity;
import cart.CartEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

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

        //gestire eccezione
        try {
            OrderEntity order = orderPlacement.placeOrder(customer);
            if (orderPlacement.joinProducts(shoppingCart, order))
                orderPlacement.placeOrderItems(order);
        } catch (SQLException exception) {
            exception.getErrorCode();
        }


    }
}
