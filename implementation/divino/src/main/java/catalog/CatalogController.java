package catalog;

import account.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "CatalogController", value = "/catalog")
public class CatalogController extends HttpServlet {
    private CatalogDAO catalogDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.catalogDAO = (CatalogDAO) super.getServletContext().getAttribute("catalogDAO");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productBrand = request.getParameter("p_brand");
        String productDescription = request.getParameter("p_description");
        String productFormat = request.getParameter("p_format");
        double productPrice = Double.parseDouble(request.getParameter("p_price"));
        int productAvailability = Integer.parseInt(request.getParameter("p_availability"));
        boolean isSales = Boolean.parseBoolean(request.getParameter("p_issales"));
        double salesPrice = Double.parseDouble(request.getParameter("p_price_sales"));
        int productVat = Integer.parseInt(request.getParameter("p_vat"));
        String imagePath = request.getParameter("p_images");

        ProductEntity catalogProduct = new ProductEntity();
        catalogProduct.setProductBrand(productBrand);
        catalogProduct.setProductDescription(productDescription);
        catalogProduct.setProductFormat(productFormat);
        catalogProduct.setProductPrice(productPrice);
        catalogProduct.setProductAvailability(productAvailability);
        catalogProduct.setSales(isSales);
        catalogProduct.setSalesPrice(productPrice);
        catalogProduct.setProductVat(productVat);
        catalogProduct.setImagePath(imagePath);

        try {
            catalogDAO.addProduct(catalogProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}