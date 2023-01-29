package order;

import account.CustomerUserEntity;
import cart.CartEntity;
import cart.CartItemEntity;

import java.sql.SQLException;

public class OrderFacade {

    private OrderDAO orderDAO;

    /**
     *
     * @param customer
     * @return order object with orderID
     */
    public OrderEntity placeOrder(CustomerUserEntity customer) throws SQLException {
        return orderDAO.createOrder(customer);
    }

    //associo i prodotti del carrello all'ordine
    public boolean joinProducts(CartEntity cart, OrderEntity order) {
        if (cart != null && order != null) {
            for (CartItemEntity cartItem : cart.getShoppingCart().values()) {
                OrderItemEntity orderItem = new OrderItemEntity();

                orderItem.setOrderNumber(order.getOrderNumber());
                orderItem.setProductPrice(cartItem.getProduct().getProductPrice());
                orderItem.setProductDescription(cartItem.getProduct().getProductBrand());
                orderItem.setProductQuantity(cartItem.getProductQuantity());
                orderItem.setProductPrice(cartItem.getProduct().getProductPrice());
                orderItem.setProductVat(cartItem.getProduct().getProductVat());
            }
        } else return false;
        return true;
    }

    public boolean placeOrderItems(OrderEntity order) throws SQLException {
        for (OrderItemEntity item : order.getOrderProducts()) {
            orderDAO.saveOrderItem(item);
        }
        return true;
    }
}
