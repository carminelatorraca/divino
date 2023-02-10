package main;

import account.AccountDAO;
import catalog.CatalogDAO;
import catalog.CatalogEntity;
import catalog.ProductEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import order.OrderDAO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@WebListener
public class MainListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private Connection connection;

    public MainListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        ServletContext servletContext = sce.getServletContext();

        try {
            //set db connection
            javax.naming.Context contextInit = new InitialContext();
            javax.naming.Context context = (javax.naming.Context) contextInit.lookup("java:comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/divino_db");
            connection = dataSource.getConnection();
            servletContext.setAttribute("connection", connection);

            //init dao
            AccountDAO accountDAO = new AccountDAO(connection);
            servletContext.setAttribute("accountDAO", accountDAO);

            CatalogDAO catalogDAO = new CatalogDAO(connection);
            servletContext.setAttribute("catalogDAO", catalogDAO);

            OrderDAO orderDAO = new OrderDAO(connection);
            servletContext.setAttribute("orderDAO", orderDAO);

            //set catalog
            CatalogEntity catalog = new CatalogEntity();
            catalog.setCatalogProducts(catalogDAO.createCatalog());
            servletContext.setAttribute("catalog", catalog);

            //set errors
            //Collection<DivinoExceptions> exceptions = new ArrayList<>();
            ArrayList<String> exceptions = new ArrayList<>();
            servletContext.setAttribute("exceptions", exceptions);

        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
