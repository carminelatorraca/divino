package cart;

import java.util.HashMap;

public class CartEntity {

    private HashMap<String, CartItemEntity> shoppingCart;
    private double totalAmount;

    public CartEntity() {
        totalAmount = 0;
    }

    public void addItem(String productId, CartItemEntity cartItem) {
        if (!shoppingCart.containsKey(productId)) {
            cartItem.setProductQuantity(1);
            shoppingCart.put(productId, cartItem);
        } else {
            cartItem.setProductQuantity(cartItem.getProductQuantity() + 1);
        }
    }

    public void removeItem(String productId) {
        shoppingCart.remove(productId);
    }

    public double getTotalAmount() {
        totalAmount = 0;
        for (CartItemEntity item : shoppingCart.values()) {
            totalAmount += item.getProduct().getProductPrice() * item.getProductQuantity();
        }
        return totalAmount;
    }

    public void clearCart() {
        shoppingCart.clear();
    }


}
