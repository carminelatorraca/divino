package catalog;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

@WebServlet("/catalog")

public class CatalogController extends HttpServlet {

    public CatalogController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            CatalogDAO dbCatalog = new CatalogDAO();
            HashSet<ProductEntity> catalog = new HashSet<>();
        try {
            catalog = dbCatalog.createCatalog();
            request.getSession().setAttribute("catalog", catalog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}