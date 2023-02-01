package order;

import account.CustomerUserEntity;
import cart.CartEntity;
import cart.CartItemEntity;
import payment.PaymentEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class OrderFacade {

    private OrderDAO orderDAO;

    public OrderFacade(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     * @param customer that place order
     * @return order object with orderID
     */
    public OrderEntity placeOrder(CustomerUserEntity customer) throws SQLException {
        return orderDAO.createOrder(customer);
    }

    public void placePayment(PaymentEntity payment) {
        //gestire pagamento
    }

    /**
     * @param cart  contains a list of cart item
     * @param order order just create
     */
    public void joinProducts(CartEntity cart, OrderEntity order) {
        //if (cart == null || order == null) return;

        HashSet<OrderItemEntity> orderItems = new HashSet<>();

        for (CartItemEntity cartItem : cart.getShoppingCart().values()) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setProductID(cartItem.getProduct().getProductId());
            orderItem.setOrder(order.getOrderNumber());
            orderItem.setProductPrice(cartItem.getProduct().getProductPrice());
            orderItem.setProductDescription(cartItem.getProduct().getProductBrand());
            orderItem.setProductQuantity(cartItem.getProductQuantity());
            orderItem.setProductPrice(cartItem.getProduct().getProductPrice());
            orderItem.setProductVat(cartItem.getProduct().getProductVat());

            //aggiungo order item ad array degli item
            orderItems.add(orderItem);
        }
        order.setOrderProducts(orderItems);
    }

    /**
     * @param order order contains list of order items to save into db
     * @throws SQLException error in db insert
     */
    public void placeOrderItems(OrderEntity order) throws SQLException {
        for (OrderItemEntity item : order.getOrderProducts()) {
            orderDAO.saveOrderItem(item);
        }
    }
}
