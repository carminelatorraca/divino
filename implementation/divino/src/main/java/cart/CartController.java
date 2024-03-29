package cart;

import account.UserEntity;
import catalog.CatalogEntity;
import catalog.ProductEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {
    CartEntity cart = new CartEntity();
    CatalogEntity catalog;


    @Override
    public void init() throws ServletException {
        super.init();
        this.catalog = (CatalogEntity) super.getServletContext().getAttribute("catalog");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("shoppingCart") == null) {
            request.getSession().setAttribute("shoppingCart", cart);
        }

        cart = (CartEntity) request.getSession().getAttribute("shoppingCart");
        HashMap<String, CartItemEntity> list = new HashMap<>();

        //aggiunta al carrello
        if (request.getParameter("mode").equalsIgnoreCase("add")) {
            Integer productID = Integer.valueOf(request.getParameter("productid"));

            for (ProductEntity product : catalog.getCatalogProducts()) {
                if (product.getProductId().equals(productID)) {
                    if (!cart.checkItem(productID)) {
                        cart.addItem(new CartItemEntity(product, 1));
                    }
                    else {
                        CartItemEntity item = cart.getCartItem(productID);
                        item.setProductQuantity(item.getProductQuantity()+1);
                    }
                }
            }
        }

        //plus al carrello
        if (request.getParameter("mode").equalsIgnoreCase("plus")) {
            Integer productID = Integer.valueOf(request.getParameter("productid"));

            for (ProductEntity product : catalog.getCatalogProducts()) {
                if (product.getProductId().equals(productID)) {
                    if (!cart.checkItem(productID)) {
                        cart.addItem(new CartItemEntity(product, 1));
                    }
                    else {
                        CartItemEntity item = cart.getCartItem(productID);
                        item.setProductQuantity(item.getProductQuantity()+1);
                    }
                    request.getSession().setAttribute("shippingCart", cart);
                }
            }
        }

        //rimozione prodotto
        if (request.getParameter("mode").equalsIgnoreCase("remove")) {
            int productid = Integer.parseInt(request.getParameter("productid"));
            cart.removeItem(productid);
        }

        //decremento quantità
        else if (request.getParameter("mode").equalsIgnoreCase("min")) {
            int productID = Integer.parseInt(request.getParameter("productid"));
            int quantity = cart.getShoppingCart().get(productID).getProductQuantity();
            if ((quantity - 1) == 0) {
                cart.removeItem(productID);
                if (cart.getShoppingCart().isEmpty()) {
                    cart = null;
                }
            }
            else
                cart.getShoppingCart().get(productID).setProductQuantity(quantity - 1);
        }

        UserEntity user = (UserEntity) request.getSession().getAttribute("user");

        if (request.getParameter("mode").equalsIgnoreCase("add")) {
            request.getSession().setAttribute("shoppingCart", cart);
            response.sendRedirect(getServletContext().getContextPath() + "/shop.jsp");
        } else if (request.getParameter("mode").equalsIgnoreCase("checkout") && cart!=null) {
            response.sendRedirect(getServletContext().getContextPath() + "/checkout.jsp");
        } else if (request.getParameter("mode").equalsIgnoreCase("checkout") && user==null) {
            response.sendRedirect(getServletContext().getContextPath() + "/cart.jsp");
        } else{
            request.getSession().setAttribute("shoppingCart", cart);
            response.sendRedirect(getServletContext().getContextPath() + "/cart.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
