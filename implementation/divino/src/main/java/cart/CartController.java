package cart;

import catalog.ProductEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashSet;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("shoppingCart") != null) {
            CartEntity cart = null;
            cart = (CartEntity) request.getSession().getAttribute("shoppingCart");
            if (request.getParameter("mode").equalsIgnoreCase("add")) {
                String productID = (String) request.getParameter("productID");
                HashSet<ProductEntity> catalog = (HashSet<ProductEntity>) request.getSession().getAttribute("catalog");
                CartItemEntity cartItem = null;
                for (ProductEntity product : catalog) {
                    if (product.getProductId() == productID)
                        cartItem = new CartItemEntity(product, 1);
                }
                cart.addItem(cartItem);
            } else if (request.getParameter("mode").equalsIgnoreCase("remove")) {
                String productID = (String) request.getParameter("productID");
                cart.getShoppingCart().remove(productID);
            } else if (request.getParameter("mode").equalsIgnoreCase("plus")) {
                String productID = (String) request.getParameter("productID");
                Integer quantity = cart.getShoppingCart().get(productID).getProductQuantity();
                cart.getShoppingCart().get(productID).setProductQuantity(quantity + 1);
            } else if (request.getParameter("mode").equalsIgnoreCase("min")) {
                String productID = (String) request.getParameter("productID");
                Integer quantity = cart.getShoppingCart().get(productID).getProductQuantity();
                if (quantity - 1 == 0)
                    cart.getShoppingCart().remove(productID);
                else
                    cart.getShoppingCart().get(productID).setProductQuantity(quantity - 1);
            }
            request.getSession().setAttribute("shoppingCart", cart);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
