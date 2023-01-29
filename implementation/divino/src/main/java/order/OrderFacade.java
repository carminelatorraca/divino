package order;

import account.CustomerUserEntity;
import cart.CartEntity;
import cart.CartItemEntity;

import java.sql.SQLException;

public class OrderFacade {

    private OrderDAO orderDAO;

    /**
     * @param customer that place order
     * @return order object with orderID
     */
    public OrderEntity placeOrder(CustomerUserEntity customer) throws SQLException {
        return orderDAO.createOrder(customer);
    }

    /**
     * @param cart  contains a list of cart item
     * @param order order just create
     * @return true if all cart item are in order item
     */
    public boolean joinProducts(CartEntity cart, OrderEntity order) {
        if (cart == null || order == null) return false;

        for (CartItemEntity cartItem : cart.getShoppingCart().values()) {
            OrderItemEntity orderItem = new OrderItemEntity();

            orderItem.setOrderNumber(order.getOrderNumber());
            orderItem.setProductPrice(cartItem.getProduct().getProductPrice());
            orderItem.setProductDescription(cartItem.getProduct().getProductBrand());
            orderItem.setProductQuantity(cartItem.getProductQuantity());
            orderItem.setProductPrice(cartItem.getProduct().getProductPrice());
            orderItem.setProductVat(cartItem.getProduct().getProductVat());
        }
        return true;
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
