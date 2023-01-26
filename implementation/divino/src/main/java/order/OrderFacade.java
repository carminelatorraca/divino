package order;

import account.CustomerUserEntity;
import payment.PaymentEntity;

import java.util.ArrayList;

public class OrderFacade {

    private OrderEntity order;
    private ArrayList<OrderItem> products;
    private PaymentEntity payment;
    private CustomerUserEntity customer;

    public OrderFacade() {
        order = new OrderEntity();
        payment = new PaymentEntity();
        products = new ArrayList<OrderItem>();
        customer = new CustomerUserEntity();
    }

    public void createOrder() {

    }

}
