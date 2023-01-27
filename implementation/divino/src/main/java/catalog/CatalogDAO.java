package catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class CatalogDAO {

    private static final String TABLE_NAME = "prodotto";
    private Connection connection;

    public CatalogDAO(Connection connection) {
        this.connection = connection;
    }

    public  HashSet<ProductEntity> createCatalog() throws SQLException {
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + ";");
        ResultSet rs = pst.executeQuery();
        HashSet<ProductEntity> catalog = new HashSet<>();
        ProductEntity product = new ProductEntity();
        while(rs.next()){
            product.setProductId(rs.getString("productID"));
            product.setProductBrand(rs.getString("productBrand"));
            product.setProductAvailability(rs.getInt("productAvailability"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setProductFormat(rs.getString("productFormat"));
            product.setProductPrice(rs.getDouble("productPrice"));
            product.setProductVat(rs.getInt("productVAT"));
            product.setSales(rs.getBoolean("isSales"));
            product.setSalesPrice(rs.getDouble("salesPrice"));
            product.setVisible(rs.getBoolean("isVisible"));
            catalog.add(product);
        }
        return catalog;
    }

    public void removeProduct(ProductEntity product) throws SQLException{
        PreparedStatement pst = connection.prepareStatement("DELETE * FROM " + TABLE_NAME + "WHERE productID = ?;");
        pst.setString(1, product.getProductId());
        pst.executeUpdate();
    }


    public void addProduct(ProductEntity product) throws SQLException{
        PreparedStatement pst = connection.prepareStatement("INSERT INTO " + TABLE_NAME +
                "(productID, productPrice, productBrand, productAvailability, ProductDescription, productVAT, productFormat, isSales, salesPrice, isVisible) VALUES (?,?,?,?,?,?,?,?,?,?);");
        pst.setString(1, product.getProductId());
        pst.setDouble(2, product.getProductPrice());
        pst.setString(3, product.getProductBrand());
        pst.setInt(4, product.getProductAvailability());
        pst.setString(5, product.getProductDescription());
        pst.setInt(6, product.getProductVat());
        pst.setString(7, product.getProductFormat());
        pst.setBoolean(8, product.isSales());
        pst.setDouble(9, product.getSalesPrice());
        pst.setBoolean(10, product.isVisible());
        pst.executeUpdate();
    }

    public void updateProduct(ProductEntity product) throws SQLException{
        PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + TABLE_NAME +
                "SET productID=?, productPrice = ?, productBrand = ?, productAvailability = ?, ProductDescription = ?, productVAT = ?, productFormat = ?, isSales = ?, salesPrice = ?, isVisible = ? " +
                "WHERE productID = ?;");
        pst.setString(1, product.getProductId());
        pst.setDouble(2, product.getProductPrice());
        pst.setString(3, product.getProductBrand());
        pst.setInt(4, product.getProductAvailability());
        pst.setString(5, product.getProductDescription());
        pst.setInt(6, product.getProductVat());
        pst.setString(7, product.getProductFormat());
        pst.setBoolean(8, product.isSales());
        pst.setDouble(9, product.getSalesPrice());
        pst.setBoolean(10, product.isVisible());
        pst.setString(11, product.getProductId());
        pst.executeUpdate();
    }

    //Aggiorna la quantità disponibile di un prodotto in seguito ad un ordine andato a buon fine
    public void updateStock(Integer purchasedQuantity, String productID) throws SQLException{

        int updatedQuantity = getAvailableQuantity(productID) - purchasedQuantity;
        PreparedStatement pst = connection.prepareStatement(
                "UPDATE " + TABLE_NAME +
                        "SET productAvailability = ?" +
                        "WHERE productID = ?;");
        pst.setDouble(1, updatedQuantity);
        pst.setString(2, productID);
        pst.executeUpdate();
    }

    private Integer getAvailableQuantity (String productID) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(
                "SELECT productAvailability FROM" + TABLE_NAME + "WHERE productID = ?;");
                pst.setString(1, productID);
                ResultSet rs = pst.executeQuery();
                return rs.getInt("productAvailability") ;
    }

}
