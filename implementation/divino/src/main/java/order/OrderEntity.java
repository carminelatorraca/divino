package order;

import account.CustomerUserEntity;
import payment.PaymentEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderEntity {

    private String orderNumber;
    private String orderStatus;
    private double orderTotalAmount;
    private String orderShippingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime isDeleted;

    private CustomerUserEntity orderCustomer;
    private PaymentEntity orderPayment;
    private ArrayList<OrderItemEntity> orderProducts;

    public OrderEntity(String orderNumber, String orderStatus, double orderTotalAmount, String orderShippingAddress, LocalDateTime createdAt, LocalDateTime isDeleted, CustomerUserEntity orderCustomer, PaymentEntity orderPayment, ArrayList<OrderItemEntity> orderProducts) {
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderTotalAmount() {
        for (OrderItemEntity item : orderProducts)
            orderTotalAmount += item.getProductPrice() * item.getProductQuantity();
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

    public CustomerUserEntity getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(CustomerUserEntity orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public PaymentEntity getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(PaymentEntity orderPayment) {
        this.orderPayment = orderPayment;
    }

    public ArrayList<OrderItemEntity> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<OrderItemEntity> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
