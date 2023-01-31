package cart;

import java.util.HashMap;

public class CartEntity {

    private HashMap<String, CartItemEntity> shoppingCart = new HashMap<>();
    private double totalAmount;

    public CartEntity() {
        totalAmount = 0;
    }

    public void addItem(CartItemEntity cartItem) {
        if(shoppingCart!=null) {
            if (!shoppingCart.containsKey(cartItem.getProduct().getProductId())) {
                cartItem.setProductQuantity(1);
                shoppingCart.put(cartItem.getProduct().getProductId(), cartItem);
            } else {
                cartItem.setProductQuantity(cartItem.getProductQuantity() + 1);
                System.out.println("ue be");
            }
        }
    }

    public HashMap<String, CartItemEntity> getShoppingCart() {
        return shoppingCart;
    }

    public CartItemEntity getCartItem(String productID){
        return shoppingCart.get(productID);
    }

    public void setShoppingCart(HashMap<String, CartItemEntity> shoppingCart) {
        this.shoppingCart = shoppingCart;
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
