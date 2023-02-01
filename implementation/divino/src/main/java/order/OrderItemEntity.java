package order;

public class OrderItemEntity {

    private int order;
    private int productVat;
    private double productPrice;
    private int productQuantity;
    private String productDescription;
    private Integer productID;

    public OrderItemEntity() {
        this.productID = -1;
        this.order = -1;
        this.productVat = -1;
        this.productPrice = -1;
        this.productQuantity = -1;
        this.productDescription = null;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
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

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
