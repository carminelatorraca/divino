package account;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import order.OrderDAO;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private Connection open() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3308/divino_db", "root", "Divino!");
    }

    @Test
    public void doPostTest() throws Exception {
        Connection connection = open();
        AccountDAO accountDAO = new AccountDAO(connection);
        OrderDAO orderDAO = new OrderDAO(connection);

        //REQUEST & RESPONSE
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("l-email")).thenReturn("carmine@carmine.com");
        when(request.getParameter("l-password")).thenReturn("ciao");

        //SESSION
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(true)).thenReturn(session);

        //DISPATCHER
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/shop.jsp")).thenReturn(dispatcher);

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        //SERVLET
        LoginController login = new LoginController();
        login.orderDAO = orderDAO;
        login.accountDAO = accountDAO;

        login.doPost(request, response);

        writer.flush();
        System.out.println(stringWriter.toString());
    }
}
