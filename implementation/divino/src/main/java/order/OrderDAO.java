package order;

import cart.CartItemEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    private static final String TABLE_NAME = "order";
    private static final String PIVOT_TABLE_NAME = "order_item";

    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void createOrder(OrderEntity order, CartItemEntity cart) throws SQLException {
        String createQuery = "INSERT INTO " + TABLE_NAME + " (order_id, customer_id, order_status, order_total_amount, created_at) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(createQuery);


    }
}
