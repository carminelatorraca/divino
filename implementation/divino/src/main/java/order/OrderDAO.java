package order;

import account.CustomerUserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {

    private static final String ORDER_TABLE = "orders";
    private static final String PIVOT_ORDER_TABLE = "order_items";
    private static final String PAYMENT_TABLE = "payments";

    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public OrderEntity createOrder(CustomerUserEntity customer) throws SQLException {
        OrderEntity order = new OrderEntity();

        //associo cliente a nuovo ordine
        String createQuery = "INSERT INTO " + ORDER_TABLE + " (order_account) VALUES (?)";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        System.out.println(customer.getAccountID());
        pst.setInt(1, customer.getAccountID());
        pst.executeUpdate();

        //return id nuovo ordine creato
        ResultSet rs = pst.getGeneratedKeys();
        if (!rs.wasNull()) order.setOrderNumber(rs.getInt(1));

        return order;
    }

    public void saveOrderItem(OrderItemEntity item) throws SQLException {
        String query = "INSERT INTO " + PIVOT_ORDER_TABLE + " (order_id, item_description, item_quantity, item_price, item_vat) VALUES(?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(query);

        //inserimento dei prodotti contenuti nell'ordine
        pst.setInt(1, item.getOrder());
        //aggiungi riferimento order id
        pst.setString(2, item.getProductDescription());
        pst.setInt(3, item.getProductQuantity());
        pst.setDouble(4, item.getProductPrice());
        pst.setInt(5, item.getProductVat());
        pst.executeUpdate();
    }
}
