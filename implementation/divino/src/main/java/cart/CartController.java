package cart;

import catalog.ProductEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    CartEntity cart = new CartEntity();
    Collection<ProductEntity> catalog;


    @Override
    public void init() throws ServletException {
        super.init();
        this.catalog = (Collection<ProductEntity>) super.getServletContext().getAttribute("catalog");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("shoppingCart") == null) {
            request.getSession().setAttribute("shoppingCart", cart);
            System.out.println("FATTO");
        }
        cart = (CartEntity) request.getSession().getAttribute("shoppingCart");
        HashMap<String, CartItemEntity> list = new HashMap<>();


        //aggiunta al carrello
        if (request.getParameter("mode").equalsIgnoreCase("add")) {
            String productID = request.getParameter("productid");
            System.out.println(productID);
            CartItemEntity cartItem = new CartItemEntity();
            for (ProductEntity product : catalog) {
                if (product.getProductId().equals(productID))
                    cartItem = new CartItemEntity(product, 1);
            }
            cart.addItem(cartItem);
        }

        //rimozione prodotto
        if (request.getParameter("mode").equalsIgnoreCase("remove")) {
            String productid = request.getParameter("productid");
            cart.removeItem(productid);
        }

        //incremento quantità
        else if (request.getParameter("mode").equalsIgnoreCase("plus")) {
            String productID = request.getParameter("productid");
            int quantity = cart.getShoppingCart().get(productID).getProductQuantity();
            cart.getShoppingCart().get(productID).setProductQuantity(quantity + 1);

        }

        //decremento quantità
        else if (request.getParameter("mode").equalsIgnoreCase("min")) {
            String productID = request.getParameter("productid");
            int quantity = cart.getShoppingCart().get(productID).getProductQuantity();
            if (quantity - 1 == 0)
                cart.getShoppingCart().remove(productID);
            else
                cart.getShoppingCart().get(productID).setProductQuantity(quantity - 1);
        }

        request.getSession().setAttribute("shoppingCart", cart);
        response.sendRedirect("./shop.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
