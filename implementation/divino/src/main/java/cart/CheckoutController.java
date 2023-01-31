package cart;

import account.AddressEntity;
import account.CustomerUserEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CheckoutController", value = "/checkout")
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ricevo i dati di checkout quali indirizzo, carrello e pagamento

        CartEntity checkoutCart = (CartEntity) request.getSession().getAttribute("shoppingCart");
        CustomerUserEntity customer = (CustomerUserEntity) request.getSession().getAttribute("user");

        if (checkoutCart != null && customer != null) {
            String firstname = request.getParameter("c-firstname");
            String lastname = request.getParameter("c-lastname");
            String country = request.getParameter("c-state");
            String address = request.getParameter("c-address");
            String postalCode = request.getParameter("c-address-cap");
            String city = request.getParameter("c-address-city");
            String phone = request.getParameter("c-phone");

            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setCity(city);
            addressEntity.setCountry(country);
            addressEntity.setPostalCode(postalCode);
            addressEntity.setNumber(phone);
            addressEntity.setFavourite(1);
        }
    }
}
