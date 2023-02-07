package account;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import order.OrderDAO;
import order.OrderEntity;

import java.io.IOException;
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
        String password = request.getParameter("l-password");

        try {
            account = accountDAO.retrieveAccount(email, password);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

