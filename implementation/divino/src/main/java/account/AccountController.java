package account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AccountController", value = "/account")
public class AccountController extends HttpServlet {

    private AccountDAO accountDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.accountDAO = (AccountDAO) super.getServletContext().getAttribute("accountDAO");
    }

    //Check delle credenziali inserite per il login e aggiunta oggetto utente alla sessione in caso di credeziali corrette
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("a-email");
        String password = request.getParameter("a-newPass");
        String firstName = request.getParameter("a-firstname");
        String lastName = request.getParameter("a-lastname");

        CustomerUserEntity user = (CustomerUserEntity) request.getSession().getAttribute("user");
        user.setEmail(email);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        if (password != null)
            user.setPassword(password);

        try {
            accountDAO.updateCustomerAccount(user);
            response.sendRedirect("./account/myaccount.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}