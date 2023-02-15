package account;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
        String firstName = request.getParameter("r-firstname");
        String lastName = request.getParameter("r-lastname");
        Boolean created;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] securePassword = digest.digest(request.getParameter("r-password").getBytes(StandardCharsets.UTF_8)); //gestione password

        AccountEntity account = new AccountEntity(email, byteToEx(securePassword), AccountEntity.Role.CUSTOMERUSER);
        try {
            created = dbAccount.createAccount(account);
            if(created)
                account = dbAccount.retrieveAccount(email, byteToEx(securePassword));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(created) {
            UserEntity user = new UserEntity(account);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setFiscalCode("null");
            try {
                dbAccount.createUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(!created)
            request.getSession().setAttribute("error2", "Email già utilizzata.");
        else
            request.getSession().setAttribute("error2", null);
        RequestDispatcher view = request.getRequestDispatcher("./login.jsp");
        view.forward(request, response);

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
}
