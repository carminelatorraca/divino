package account;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import order.OrderDAO;
import order.OrderEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashSet;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private AccountDAO accountDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.accountDAO = (AccountDAO) super.getServletContext().getAttribute("accountDAO");
        this.orderDAO = (OrderDAO) super.getServletContext().getAttribute("orderDAO");
    }

    //check delle credenziali inserite per il login e aggiunta oggetto utente alla sessione in caso di credeziali corrette
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountEntity account;

        String email = request.getParameter("l-email");
        //String password = request.getParameter("l-password");

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] securePassword = digest.digest(request.getParameter("l-password").getBytes(StandardCharsets.UTF_8)); //gestione password

        try {
            account = accountDAO.retrieveAccount(email, byteToEx(securePassword));
            UserEntity user = accountDAO.retrieveUser(account);
            if (account.getAccountID() != -1) {

                if (user.getRole() == AccountEntity.Role.CUSTOMERUSER) {
                    CustomerUserEntity customer = new CustomerUserEntity(user);
                    request.getSession().setAttribute("user", customer);
                    HashSet<OrderEntity> orders = orderDAO.getCustomerOrders(account);
                    request.getSession().setAttribute("myOrder", orders);
                    response.sendRedirect("./account/account.jsp");

                } else if (user.getRole().equals(AccountEntity.Role.MANAGERUSER)) {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("./admin/admin.jsp");
                } else if (user.getRole().equals(AccountEntity.Role.WAREHOUSEUSER)) {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("./warehouse/warehouse.jsp");
                }

                request.getSession().setAttribute("logged", true);
                //  response.sendRedirect("./shop.jsp");
            } else {
                request.getSession().setAttribute("logged", false);
                request.getSession().setAttribute("error", "Username e/o password invalidi.");
                response.sendRedirect("./login.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String byteToEx(byte[] hash) {
        StringBuilder StringBuilder = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                StringBuilder.append('0');
            }
            StringBuilder.append(hex);
        }
        return StringBuilder.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

