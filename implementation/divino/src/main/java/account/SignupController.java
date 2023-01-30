package account;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SignupController", value = "/signup")
public class SignupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO dbAccount = new AccountDAO();

        if (request.getParameter("mode").equalsIgnoreCase("signup")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String passwordCheck = request.getParameter("passwordCheck");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            AccountEntity account = new AccountEntity(email, password, AccountEntity.Role.CUSTOMERUSER);
            try {
                dbAccount.createAccount(account);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            UserEntity user = new UserEntity(account);
            user.setFirstName(firstName);
            user.setFirstName(lastName);
            user.setFiscalCode(null);
            try {
                dbAccount.createUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            RequestDispatcher view = request.getRequestDispatcher("./login.jsp");
            view.forward(request, response);

        }
    }
}
