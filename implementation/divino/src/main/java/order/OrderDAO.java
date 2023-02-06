package order;

import account.AccountEntity;
import account.CustomerUserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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
        order.setOrderCustomer(customer.getAccountID());
        order.setOrderNumber(getOrderId(customer.getAccountID()));
        return order;
    }

    public void updateOrder(OrderEntity order) throws SQLException {
        String createQuery = "UPDATE " + ORDER_TABLE + " SET order_status=?, order_total_amount=?, order_shipping_address=? WHERE order_id = ? AND order_account = ?;";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setString(1, order.getOrderStatus());
        pst.setDouble(2, order.getOrderTotalAmount());
        pst.setString(3, order.getOrderShippingAddress());
        pst.setInt(4, order.getOrderNumber());
        pst.setInt(5, order.getOrderCustomer());
        pst.executeUpdate();

    }

    public OrderEntity getOrder(CustomerUserEntity customer) throws SQLException {
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
        String createQuery = "SELECT order_id FROM " + ORDER_TABLE + " WHERE order_account = ? ORDER BY order_id DESC";
        PreparedStatement pst = connection.prepareStatement(createQuery);

        pst.setInt(1, customer);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
            id = rs.getInt(1);
        System.out.println("id" + id);
        return id;
    }

    public void saveOrderItem(OrderItemEntity item) throws SQLException {
        String query = "INSERT INTO " + PIVOT_ORDER_TABLE + " (order_id, item_description, item_quantity, item_price, item_vat, product_id) VALUES(?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(query);

        //inserimento dei prodotti contenuti nell'ordine
        pst.setInt(1, item.getOrder());
        //aggiungi riferimento order id
        pst.setString(2, item.getProductDescription());
        pst.setInt(3, item.getProductQuantity());
        pst.setDouble(4, item.getProductPrice());
        pst.setInt(5, item.getProductVat());
        pst.setInt(6, item.getProductID());
        pst.executeUpdate();
    }

    public ArrayList<OrderEntity> retrieveAllOrders() throws SQLException {
        ArrayList<OrderEntity> orders = new ArrayList<>();
        String createQuery = "SELECT * FROM " + ORDER_TABLE + " ORDER BY order_id DESC";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            OrderEntity order = new OrderEntity();
            order.setOrderNumber(rs.getInt(1));
            order.setOrderStatus(rs.getString(2));
            order.setOrderTotalAmount(rs.getDouble(3));
            order.setOrderShippingAddress(rs.getString(4));
            order.setOrderCustomer(rs.getInt(7));
            order.setOrderPayment(rs.getInt(8));
            orders.add(order);
        }
        return orders;
    }

    public OrderEntity retrieveOrder(Integer orderid) throws SQLException {
        String createQuery = "SELECT * FROM " + ORDER_TABLE + " WHERE order_id = ?";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setInt(1, orderid);
        ResultSet rs = pst.executeQuery();
        OrderEntity order = new OrderEntity();
        while (rs.next()) {

            order.setOrderNumber(rs.getInt(1));
            order.setOrderStatus(rs.getString(2));
            order.setOrderTotalAmount(rs.getDouble(3));
            order.setOrderShippingAddress(rs.getString(4));
            order.setOrderCustomer(rs.getInt(7));
            order.setOrderPayment(rs.getInt(8));
        }
        return order;
    }

    public HashSet<OrderEntity> retrieveAllOrdersToShip() throws SQLException {
        HashSet<OrderEntity> orders = new HashSet<>();
        OrderEntity order = new OrderEntity();
        String createQuery = "SELECT * FROM " + ORDER_TABLE + " WHERE order_status = ? ORDER BY order_id DESC";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setString(1, "packed");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            order.setOrderNumber(rs.getInt(1));
            order.setOrderStatus(rs.getString(2));
            order.setOrderTotalAmount(rs.getDouble(3));
            order.setOrderShippingAddress(rs.getString(4));
            order.setOrderCustomer(rs.getInt(5));
            order.setOrderPayment(rs.getInt(6));
            orders.add(order);
        }
        return orders;
    }

    public HashSet<OrderEntity> retrieveAllOrdersToPack() throws SQLException {
        HashSet<OrderEntity> orders = new HashSet<>();
        OrderEntity order = new OrderEntity();
        String createQuery = "SELECT * FROM " + ORDER_TABLE + " WHERE order_status = ? ORDER BY order_id DESC";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setString(1, "paid");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            order.setOrderNumber(rs.getInt(1));
            order.setOrderStatus(rs.getString(2));
            order.setOrderTotalAmount(rs.getDouble(3));
            order.setOrderShippingAddress(rs.getString(4));
            order.setOrderCustomer(rs.getInt(5));
            order.setOrderPayment(rs.getInt(6));
            orders.add(order);
        }
        return orders;
    }

    public HashSet<OrderEntity> customerOrders(AccountEntity account) throws SQLException {
        HashSet<OrderEntity> customerOrders = new HashSet<>();
        OrderEntity order = new OrderEntity();

        String createQuery = "SELECT * FROM " + ORDER_TABLE + " WHERE order_account = ?";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setInt(1, account.getAccountID());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            order.setOrderNumber(rs.getInt(1));
            order.setOrderStatus(rs.getString(2));
            order.setOrderTotalAmount(rs.getDouble(3));
            order.setOrderShippingAddress(rs.getString(4));
            order.setOrderCustomer(rs.getInt(5));
            order.setOrderPayment(rs.getInt(6));
            order.setOrderProducts(orderItem(rs.getInt(1)));
            customerOrders.add(order);
        }
        return customerOrders;
    }

    public HashSet<OrderItemEntity> orderItem(int order) throws SQLException {
        HashSet<OrderItemEntity> orderItems = new HashSet<>();
        OrderItemEntity orderItem = new OrderItemEntity();

        String createQuery = "SELECT * FROM " + PIVOT_ORDER_TABLE + " WHERE order_id = ?";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setInt(1, order);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            orderItem.setOrder(rs.getInt("order_id"));
            orderItem.setProductPrice(rs.getInt("item_price"));
            orderItem.setProductDescription(rs.getString("item_description"));
            orderItem.setProductVat(rs.getInt("item_vat"));
            orderItem.setProductQuantity(rs.getInt("item_quantity"));
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
