package catalog;

import java.time.LocalDateTime;

public class ProductEntity {

    private String productId;
    private String productBrand;
    private String productDescription;
    private String productFormat;
    private double productPrice;
    private int productAvailability;
    private boolean isSales;
    private double salesPrice;
    private int productVat;
    private boolean isVisible;
    private String imagePath;

    private LocalDateTime createdAt;
    private LocalDateTime isDeleted;

    public ProductEntity() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductFormat() {
        return productFormat;
    }

    public void setProductFormat(String productFormat) {
        this.productFormat = productFormat;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(int productAvailability) {
        this.productAvailability = productAvailability;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(LocalDateTime isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isSales() {
        return isSales;
    }

    public void setSales(boolean sales) {
        isSales = sales;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getProductVat() {
        return productVat;
    }

    public void setProductVat(int productVat) {
        this.productVat = productVat;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
