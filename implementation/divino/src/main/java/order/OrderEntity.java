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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
