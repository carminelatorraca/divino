package catalog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CatalogController", value = "/catalog",
        initParams = {@WebInitParam(name = "upload-dir", value = "images", description = "")})

@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class CatalogController extends HttpServlet {
    private CatalogDAO catalogDAO;
    static String SAVE_DIR = "";

    @Override
    public void init() throws ServletException {
        super.init();
        this.catalogDAO = (CatalogDAO) super.getServletContext().getAttribute("catalogDAO");
        SAVE_DIR = getServletConfig().getInitParameter("upload-dir");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("mode") != null && request.getParameter("mode").equalsIgnoreCase("updateProduct")) {

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
                response.sendRedirect("./admin/products-view.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (request.getParameter("mode").equalsIgnoreCase("uploadProduct")) {
            String productBrand = request.getParameter("p_brand");
            String productDescription = request.getParameter("p_description");
            String productFormat = request.getParameter("p_format");
            String productPrice = request.getParameter("p_price");
            String productAvailability = request.getParameter("p_availability");
            String isSales = request.getParameter("p_issales");
            String salesPrice = request.getParameter("p_price_sales");
            String productVat = request.getParameter("p_vat");
            //String imagePath = request.getParameter("p_images");

            ProductEntity catalogProduct = new ProductEntity();
            catalogProduct.setProductBrand(productBrand);
            catalogProduct.setProductDescription(productDescription);
            catalogProduct.setProductFormat(productFormat);
            catalogProduct.setProductPrice(Double.parseDouble(productPrice));
            catalogProduct.setProductAvailability(Integer.parseInt(productAvailability));
            catalogProduct.setSales(Boolean.parseBoolean(isSales));
            catalogProduct.setSalesPrice(Double.parseDouble(salesPrice));
            catalogProduct.setProductVat(Integer.parseInt(productVat));
            //catalogProduct.setImagePath(imagePath);

            String folderId = catalogProduct.getProductBrand();

            String uploadPath = getServletContext().getRealPath("") + File.separator + SAVE_DIR + File.separator + folderId;
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) uploadDir.mkdir();

            for (Part parts : request.getParts()) {
                String fileName = getFileName(parts);
                if (fileName != null && !fileName.equals("")) {
                    parts.write(uploadPath + File.separator + fileName);
                    catalogProduct.setImagePath(fileName);
                }
            }

            try {
                catalogDAO.addProduct(catalogProduct);
                response.sendRedirect("./admin/products-view.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String str : items) {
            if (str.trim().startsWith("filename"))
                return str.substring(str.indexOf("=") + 2, str.length() - 1);
        }
        return "";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}