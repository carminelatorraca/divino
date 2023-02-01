package catalog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "CatalogController", value = "/catalog")
public class CatalogController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productBrand = request.getParameter("p_brand");
        String productDescription = request.getParameter("p_description");
        String productFormat = request.getParameter("p_format");
        double productPrice = Double.parseDouble(request.getParameter("p_price"));
        int productAvailability = Integer.parseInt(request.getParameter("p_availability"));
        int isSales = Integer.parseInt(request.getParameter("p_issales"));
        double salesPrice = Double.parseDouble(request.getParameter("p_price_sales"));
        int productVat = Integer.parseInt(request.getParameter("p_vat"));

    }
}