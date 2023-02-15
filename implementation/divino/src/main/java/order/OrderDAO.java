package order;

import account.AccountEntity;
import account.CustomerUserEntity;
import payment.PaymentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class OrderDAO {

    private static final String ORDER_TABLE = "orders";
    private static final String PIVOT_ORDER_TABLE = "order_items";
    private static final String PAYMENT_TABLE = "payments";

    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    //creo un nuovo ordine associandolo al cliente
    public OrderEntity createOrder(CustomerUserEntity customer) throws SQLException {
        OrderEntity order = new OrderEntity();

        PreparedStatement pst = connection.prepareStatement("INSERT INTO " + ORDER_TABLE + " (order_account) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, customer.getAccountID());
        pst.executeUpdate();

        order.setOrderCustomer(customer.getAccountID());
        //trovo id ordine
        order.setOrderNumber(getOrderId(customer.getAccountID()));
        return order;
    }

    //trovo l'id dell'ordine
    public int getOrderId(int customer) throws SQLException {
        int id = 0;
        String createQuery = "SELECT order_id FROM " + ORDER_TABLE + " WHERE order_account = ? ORDER BY order_id DESC";
        PreparedStatement pst = connection.prepareStatement(createQuery);

        pst.setInt(1, customer);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
            id = rs.getInt(1);
        return id;
    }

    public void updateOrder(OrderEntity order) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("UPDATE " + ORDER_TABLE + " SET order_status=?, order_total_amount=?, order_shipping_address=? WHERE order_id = ? AND order_account = ?;");

        pst.setString(1, order.getOrderStatus());
        pst.setDouble(2, order.getOrderTotalAmount());
        pst.setString(3, order.getOrderShippingAddress());
        pst.setInt(4, order.getOrderNumber());
        pst.setInt(5, order.getOrderCustomer());
        pst.executeUpdate();

    }

    //salvo i prodotti associati all'ordine
    public void saveOrderItem(OrderItemEntity item) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("INSERT INTO " + PIVOT_ORDER_TABLE + " (order_id, item_description, item_quantity, item_price, item_vat, product_id) VALUES(?,?,?,?,?,?)");

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
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM " + ORDER_TABLE + " ORDER BY order_id DESC");

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
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM " + ORDER_TABLE + " WHERE order_id = ?");
        pst.setInt(1, orderid);

        ResultSet rs = pst.executeQuery();
        OrderEntity order = new OrderEntity();
        while (rs.next()) {
            order.setOrderNumber(rs.getInt(1));
            order.setOrderStatus(rs.getString(2));
            order.setOrderTotalAmount(rs.getDouble(3));
            order.setOrderShippingAddress(rs.getString(4));
            order.setOrderCustomer(rs.getInt(5));
            order.setOrderPayment(rs.getInt(6));
        }
        return order;
    }

    //trovo tutti gli ordini da spedire
    public HashSet<OrderEntity> retrieveAllOrdersToShip() throws SQLException {
        HashSet<OrderEntity> orders = new HashSet<>();

        String createQuery = "SELECT * FROM " + ORDER_TABLE + " WHERE order_status = ? ORDER BY order_id DESC";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setString(1, "packed");

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            OrderEntity order = new OrderEntity();

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

    //trovo gli ordini da preparare
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

    //trovo tutti gli ordini di un cliente
    public HashSet<OrderEntity> getCustomerOrders(AccountEntity account) throws SQLException {
        HashSet<OrderEntity> customerOrders = new HashSet<>();
        String createQuery = "SELECT * FROM " + ORDER_TABLE + " WHERE order_account = ?";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setInt(1, account.getAccountID());

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            OrderEntity order = new OrderEntity();

            order.setOrderNumber(rs.getInt(1));
            order.setOrderStatus(rs.getString(2));
            order.setOrderTotalAmount(rs.getDouble(3));
            order.setOrderShippingAddress(rs.getString(4));
            order.setOrderCustomer(rs.getInt(5));
            order.setOrderPayment(rs.getInt(6));
            order.setOrderProducts(getOrderItems(rs.getInt(1)));
            customerOrders.add(order);
        }
        return customerOrders;
    }

    //trovo tutti i prodotti associati all'ordine
    public HashSet<OrderItemEntity> getOrderItems(int order) throws SQLException {
        HashSet<OrderItemEntity> orderItems = new HashSet<>();

        String createQuery = "SELECT * FROM " + PIVOT_ORDER_TABLE + " WHERE order_id = ?";
        PreparedStatement pst = connection.prepareStatement(createQuery);
        pst.setInt(1, order);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            OrderItemEntity orderItem = new OrderItemEntity();

            orderItem.setOrder(rs.getInt("order_id"));
            orderItem.setProductPrice(rs.getInt("item_price"));
            orderItem.setProductDescription(rs.getString("item_description"));
            orderItem.setProductVat(rs.getInt("item_vat"));
            orderItem.setProductQuantity(rs.getInt("item_quantity"));
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public void savePayment(PaymentEntity payment) throws SQLException {
        String sql = "INSERT INTO " + PAYMENT_TABLE + " (paid_amount, payment_description, payment_status, payment_method, order_id) VALUES(?,?,?,?,?);";
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setDouble(1, payment.getPaidAmount());
        pst.setString(2, payment.getPaymentDescription());
        pst.setString(3, payment.getPaymentStatus());
        pst.setString(4, payment.getPaymentMethod());
        pst.setInt(5, payment.getOrderNumber());

        pst.executeUpdate();
    }
}
