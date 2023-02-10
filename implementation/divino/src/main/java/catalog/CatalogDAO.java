package catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class CatalogDAO {

    private static final String TABLE_NAME = "products";
    private Connection connection;

    public CatalogDAO(Connection connection) {
        this.connection = connection;
    }

    public HashSet<ProductEntity> createCatalog() throws SQLException {
        HashSet<ProductEntity> catalog = new HashSet<>();

        PreparedStatement pst = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + ";");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            ProductEntity product = new ProductEntity();
            product.setProductId(rs.getInt(1));
            product.setProductBrand(rs.getString(2));
            product.setProductAvailability(rs.getInt(6));
            product.setProductDescription(rs.getString(3));
            product.setProductFormat(rs.getString(4));
            product.setProductPrice(rs.getDouble(5));
            product.setProductVat(rs.getInt(9));
            product.setSales(rs.getBoolean(7));
            product.setSalesPrice(rs.getDouble(8));
            product.setVisible(rs.getBoolean(10));
            product.setImagePath(rs.getString(11));
            catalog.add(product);
        }
        return catalog;
    }

    public void removeProduct(ProductEntity product) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("DELETE * FROM " + TABLE_NAME + "WHERE productID = ?;");
        pst.setInt(1, product.getProductId());
        pst.executeUpdate();
    }

    public void addProduct(ProductEntity product) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("INSERT INTO " + TABLE_NAME +
                "(product_price, product_brand, product_availability, product_description, product_vat, product_format, is_sales, sales_price, is_visible, image_path) VALUES (?,?,?,?,?,?,?,?,?,?);");

        pst.setDouble(1, product.getProductPrice());
        pst.setString(2, product.getProductBrand());
        pst.setInt(3, product.getProductAvailability());
        pst.setString(4, product.getProductDescription());
        pst.setInt(5, product.getProductVat());
        pst.setString(6, product.getProductFormat());
        pst.setBoolean(7, product.isSales());
        pst.setDouble(8, product.getSalesPrice());
        pst.setBoolean(9, product.isVisible());
        pst.setString(10, product.getImagePath());
        pst.executeUpdate();
    }

    public void updateProduct(ProductEntity product) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET product_id = ?, product_price = ?, product_brand = ?, product_availability = ?, product_description = ?, product_vat = ?, product_format = ?, is_sales = ?, sales_price = ?, is_visible = ? " +
                "WHERE product_id = ?;");
        pst.setInt(1, product.getProductId());
        pst.setDouble(2, product.getProductPrice());
        pst.setString(3, product.getProductBrand());
        pst.setInt(4, product.getProductAvailability());
        pst.setString(5, product.getProductDescription());
        pst.setInt(6, product.getProductVat());
        pst.setString(7, product.getProductFormat());
        pst.setBoolean(8, product.isSales());
        pst.setDouble(9, product.getSalesPrice());
        pst.setBoolean(10, product.isVisible());
        pst.setInt(11, product.getProductId());
        pst.executeUpdate();
    }

    //Aggiorna la quantità disponibile di un prodotto in seguito ad un ordine andato a buon fine
    public void updateStock(Integer purchasedQuantity, String productID) throws SQLException {

        int updatedQuantity = getAvailableQuantity(productID) - purchasedQuantity;
        PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + TABLE_NAME +
                        "SET product_availability = ?" +
                        "WHERE product_id = ?;");
        pst.setDouble(1, updatedQuantity);
        pst.setString(2, productID);
        pst.executeUpdate();
    }

    private Integer getAvailableQuantity(String productID) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(
                "SELECT product_availability FROM" + TABLE_NAME + "WHERE product_id = ?;");
        pst.setString(1, productID);
        ResultSet rs = pst.executeQuery();
        return rs.getInt("productAvailability");
    }
}
