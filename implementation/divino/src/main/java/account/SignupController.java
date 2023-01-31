package account;
import catalog.CatalogDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SignupController", value = "/signup")
public class SignupController extends HttpServlet {
    private AccountDAO dbAccount;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dbAccount = (AccountDAO) super.getServletContext().getAttribute("accountDAO");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("r-email");
        String password = request.getParameter("r-password");
        String firstName = request.getParameter("r-firstname");
        String lastName = request.getParameter("r-lastname");

        AccountEntity account = new AccountEntity(email, password, AccountEntity.Role.CUSTOMERUSER);
        try {
            dbAccount.createAccount(account);
            account = dbAccount.retrieveAccount(email,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UserEntity user = new UserEntity(account);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setFiscalCode("null");
        try {
            dbAccount.createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher view = request.getRequestDispatcher("./login.jsp");
        view.forward(request, response);

    }
}
