package account;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class LoginControllerTest {
    LoginController loginController;

    @Mock
    ServletContext context;

    @BeforeEach
    void setUp() {
    }


    @Test
    void doPostValid() throws ServletException, IOException, SQLException {

        // Set up a mock request and response
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        final ServletContext servletContext = Mockito.mock(ServletContext.class);

        //controller login
        loginController = new LoginController() {
            private static final long serialVersionUID = 1L;

            public ServletContext getServletContext() {
                return servletContext;
            }
        };

        PreparedStatement preparedStmt1 = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        Connection connection = mock(Connection.class);
        when(servletContext.getAttribute("connection")).thenReturn(connection);

        AccountDAO accountDAO = mock(AccountDAO.class);
        when(servletContext.getAttribute("accountDAO")).thenReturn(accountDAO);


        when(request.getParameter("email")).thenReturn("carmine@carmine.com");
        when(request.getParameter("password")).thenReturn("ciao");

        AccountEntity account = mock(AccountEntity.class);
        when(accountDAO.retrieveAccount("carmine@carmine.com", "ciao")).thenReturn(account);

        // Call the servlet's doGet method with the mock request and response
        loginController.doPost(request, response);

        verify(request).getParameter("nome");
        verify(request).getParameter("cognome");

        Assertions.assertEquals("carmine@test.it", request.getParameter("email"));
        Assertions.assertEquals("testing", request.getParameter("password"));
    }
}