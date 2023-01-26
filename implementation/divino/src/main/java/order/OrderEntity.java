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
    private ArrayList<OrderDetail> products;


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
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
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

    public ArrayList<OrderDetail> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderDetail> products) {
        this.products = products;
    }
}
