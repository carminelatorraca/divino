package order;

import account.CustomerUserEntity;

import java.sql.*;

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
        PreparedStatement pst = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, customer.getAccountID());
        pst.executeUpdate();

        //return id nuovo ordine creato
        order.setOrderNumber(getOrderId(customer.getAccountID()));
        return order;
    }

    public int getOrderId(int customer) throws SQLException {
        int id = 0;
        String createQuery = "SELECT order_id FROM " + ORDER_TABLE + " WHERE order_account = ?";
        PreparedStatement pst = connection.prepareStatement(createQuery);

        pst.setInt(1, customer);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
            id = rs.getInt(1);
        System.out.println("id"+id);
        return id;
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
