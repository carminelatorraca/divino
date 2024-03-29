package cart;

import catalog.ProductEntity;

public class CartItemEntity {

    private ProductEntity product;
    private int productQuantity;

    public CartItemEntity() {
        this.product = null;
        this.productQuantity = -1;
    }

    public CartItemEntity(ProductEntity product, int productQuantity) {
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        if (productQuantity > 0 && productQuantity <= product.getProductAvailability())
            this.productQuantity = productQuantity;
    }

}
