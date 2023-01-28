package order;

public class OrderItemEntity {

    private String order;
    private int productVat;
    private double productPrice;
    private int productQuantity;
    private String productDescription;

    public OrderItemEntity() {
        this.order = null;
        this.productVat = -1;
        this.productPrice = -1;
        this.productQuantity = -1;
        this.productDescription = null;
    }

    public String getOrderNumber() {
        return order;
    }

    public void setOrderNumber(String order) {
        this.order = order;
    }

    public int getProductVat() {
        return productVat;
    }

    public void setProductVat(int productVat) {
        this.productVat = productVat;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
