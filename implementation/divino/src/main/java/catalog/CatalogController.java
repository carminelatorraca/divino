package catalog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.lang.*;

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
        if (request.getParameter("mode").equalsIgnoreCase("updateProduct")) {
            Integer productID = Integer.valueOf(request.getParameter("p_productID"));
            System.out.println(productID);
            String productBrand = request.getParameter("p_brand");
            String productDescription = request.getParameter("p_description");
            String productFormat = request.getParameter("p_format");
            String productPrice = request.getParameter("p_price");
            String productAvailability = request.getParameter("p_availability");
            String isSales = request.getParameter("p_issales");
            String salesPrice = request.getParameter("p_price_sales");
            String productVat = request.getParameter("p_vat");
            String imagePath = request.getParameter("p_images");

            ProductEntity catalogProduct = new ProductEntity();
            catalogProduct.setProductId(productID);
            catalogProduct.setProductBrand(productBrand);
            catalogProduct.setProductDescription(productDescription);
            catalogProduct.setProductFormat(productFormat);
            catalogProduct.setProductPrice(Double.parseDouble(productPrice));
            catalogProduct.setProductAvailability(Integer.parseInt(productAvailability));
            catalogProduct.setSales(Boolean.parseBoolean(isSales));
            catalogProduct.setSalesPrice(Double.parseDouble(salesPrice));
            catalogProduct.setProductVat(Integer.parseInt(productVat));
            catalogProduct.setImagePath(imagePath);

            try {
                catalogDAO.updateProduct(catalogProduct);
                response.sendRedirect("./admin/product-view.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            String productBrand = request.getParameter("p_brand");
            String productDescription = request.getParameter("p_description");
            String productFormat = request.getParameter("p_format");
            String productPrice = request.getParameter("p_price");
            String productAvailability = request.getParameter("p_availability");
            String isSales = request.getParameter("p_issales");
            String salesPrice = request.getParameter("p_price_sales");
            String productVat = request.getParameter("p_vat");
            String imagePath = request.getParameter("p_images");

            ProductEntity catalogProduct = new ProductEntity();
            catalogProduct.setProductBrand(productBrand);
            catalogProduct.setProductDescription(productDescription);
            catalogProduct.setProductFormat(productFormat);
            catalogProduct.setProductPrice(Double.parseDouble(productPrice));
            catalogProduct.setProductAvailability(Integer.parseInt(productAvailability));
            catalogProduct.setSales(Boolean.parseBoolean(isSales));
            catalogProduct.setSalesPrice(Double.parseDouble(salesPrice));
            catalogProduct.setProductVat(Integer.parseInt(productVat));
            catalogProduct.setImagePath(imagePath);

            try {
                catalogDAO.addProduct(catalogProduct);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}