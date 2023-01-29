package account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignupServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        AccountDAO dbAccount = new AccountDAO();

        if(request.getParameter("mode").equalsIgnoreCase("signup")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String passwordCheck = request.getParameter("passwordCheck");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            AccountEntity account = new AccountEntity(email,password, AccountEntity.Role.CUSTOMERUSER);
            dbAccount.createAccount(account);

            UserEntity user = new UserEntity(account);
            user.setFirstName(firstName);
            user.setFirstName(lastName);
            user.setFiscalCode(null);
            dbAccount.createUser(user);

            RequestDispatcher view = request.getRequestDispatcher("./login.jsp");
            view.forward(request, response);

        }
    }
}
