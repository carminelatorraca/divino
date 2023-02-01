package cart;

import java.util.HashMap;

public class CartEntity {

    private HashMap<Integer, CartItemEntity> shoppingCart = new HashMap<Integer, CartItemEntity>();
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
                System.out.println("Fatto +1 :" + cartItem.getProductQuantity());
            }
        }
    }

    public boolean checkItem(Integer productID){
        if (shoppingCart.containsKey(productID))
            return true;
        return false;
    }

    public HashMap<Integer, CartItemEntity> getShoppingCart() {
        return shoppingCart;
    }

    public CartItemEntity getCartItem(Integer productID){
        return shoppingCart.get(productID);
    }

    public void setShoppingCart(HashMap<Integer, CartItemEntity> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void removeItem(int productId) {
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
