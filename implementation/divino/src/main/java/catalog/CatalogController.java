package catalog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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