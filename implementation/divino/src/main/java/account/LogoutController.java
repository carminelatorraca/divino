package account;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/logout")

public class LogoutController {

    public LogoutController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("logged");
        request.getSession().removeAttribute("cart");
        response.sendRedirect("./home.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
