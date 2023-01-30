package order;

import account.CustomerUserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {

    private static final String ORDER_TABLE = "order";
    private static final String PIVOT_ORDER_TABLE = "order_items";
    private static final String PAYMENT_TABLE = "payments";

    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public OrderEntity createOrder(CustomerUserEntity customer) throws SQLException {
        OrderEntity order = new OrderEntity();

        //associo cliente a nuovo ordine
        String createQuery = "INSERT INTO " + ORDER_TABLE + " (customer_id) VALUES (?)";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setString(1, customer.getAccountID());
        pst.executeUpdate();

        //return id nuovo ordine creato
        ResultSet rs = pst.getGeneratedKeys();
        if (!rs.wasNull()) order.setOrderNumber(rs.getString(1));

        return order;
    }

    public void saveOrderItem(OrderItemEntity item) throws SQLException {
        String query = "INSERT INTO " + PIVOT_ORDER_TABLE + " (order_item_id, order_id, item_description, item_quantity, item_price, item_vat) VALUES(?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(query);

        //inserimento dei prodotti contenuti nell'ordine
        pst.setString(1, item.getOrderNumber());
        //aggiungi riferimento order id
        pst.setString(2, item.getProductDescription());
        pst.setInt(3, item.getProductQuantity());
        pst.setDouble(4, item.getProductPrice());
        pst.setInt(5, item.getProductVat());
        pst.executeUpdate();
    }
}
