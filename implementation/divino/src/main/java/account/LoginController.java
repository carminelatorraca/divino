package account;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    private AccountDAO accountDao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.accountDao = (AccountDAO) super.getServletContext().getAttribute("accountDAO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //Check delle credenziali inserite per il login e aggiunta oggetto utente alla sessione in caso di credeziali corrette
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountEntity account;

        String email = request.getParameter("l-email");
        String password = request.getParameter("l-password");

        try {
            account = accountDao.retrieveAccount(email, password);
            UserEntity user = accountDao.retrieveUser(account);

            if (account.getAccountID() != -1) {
                if (user.getRole() == AccountEntity.Role.CUSTOMERUSER) {
                    CustomerUserEntity customer = new CustomerUserEntity(user);
                    customer.setShippingAddresses(accountDao.retrieveAddresses(customer));
                    request.getSession().setAttribute("user", customer);
                } else
                    request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("logged", true);
                response.sendRedirect("./shop.jsp");
            } else {
                request.getSession().setAttribute("logged", false);
                request.getSession().setAttribute("error", "Username e/o password invalidi.");
                response.sendRedirect("./login.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

