package cart;

import java.util.HashMap;

public class CartEntity {

    private HashMap<String, CartItem> shoppingCart;
    private double totalAmount;

    public void addItem(String productId, CartItem cartItem) {
        if (!shoppingCart.containsKey(productId)) {
            cartItem.setProductQuantity(1);
            shoppingCart.put(productId, cartItem);
        } else {
            cartItem.setProductQuantity(cartItem.getProductQuantity() + 1);
        }
    }

    public double getTotalAmount() {
        totalAmount = 0;
        for (CartItem item : shoppingCart.values()) {
            totalAmount += item.getProduct().getProductPrice() * item.getProductQuantity();
        }
        return totalAmount;
    }

    public void clearCart() {
        shoppingCart.clear();
    }
}
