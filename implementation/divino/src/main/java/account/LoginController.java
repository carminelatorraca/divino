package account;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

    //Check delle credenziali inserite per il login e aggiunta oggetto utente alla sessione in caso di credeziali corrette
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO dbAccount = new AccountDAO();
        AccountEntity account = new AccountEntity();

        if (request.getParameter("mode").equalsIgnoreCase("login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            try {
                account = dbAccount.retrieveAccount(email, password);
                UserEntity user = dbAccount.retrieveUser(account);
                if (user != null) {
                    if (user.getRole() == AccountEntity.Role.CUSTOMERUSER) {
                        CustomerUserEntity customer = new CustomerUserEntity(user);
                        request.getSession().setAttribute("user", customer);
                    } else
                        request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("logged", (Boolean) true);
                    response.sendRedirect("#");
                } else {
                    request.getSession().setAttribute("logged", (Boolean) false);
                    request.getSession().setAttribute("error", "Username e/o password invalidi.");
                    response.sendRedirect("./login.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
