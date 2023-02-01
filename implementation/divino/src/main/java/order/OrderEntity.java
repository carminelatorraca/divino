package order;

import account.CustomerUserEntity;
import payment.PaymentEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderEntity {

    private int orderNumber;
    private String orderStatus;
    private double orderTotalAmount;
    private String orderShippingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime isDeleted;

    private int orderCustomer;
    private int orderPayment;
    private ArrayList<OrderItemEntity> orderProducts;

    public OrderEntity(int orderNumber, String orderStatus, double orderTotalAmount, String orderShippingAddress, LocalDateTime createdAt, LocalDateTime isDeleted, int orderCustomer, int orderPayment, ArrayList<OrderItemEntity> orderProducts) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.orderTotalAmount = orderTotalAmount;
        this.orderShippingAddress = orderShippingAddress;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.orderCustomer = orderCustomer;
        this.orderPayment = orderPayment;
        this.orderProducts = orderProducts;
    }

    public OrderEntity() {

    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderTotalAmount(double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderTotalAmount() {
        /*
        for (OrderItemEntity item : orderProducts)
            orderTotalAmount += item.getProductPrice() * item.getProductQuantity();

         */
        return orderTotalAmount;
    }

    public String getOrderShippingAddress() {
        return orderShippingAddress;
    }

    public void setOrderShippingAddress(String orderShippingAddress) {
        this.orderShippingAddress = orderShippingAddress;
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

    public int getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(int orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public int getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(int orderPayment) {
        this.orderPayment = orderPayment;
    }

    public ArrayList<OrderItemEntity> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<OrderItemEntity> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
