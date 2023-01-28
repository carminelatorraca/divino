package account;

import payment.PaymentEntity;

import java.util.List;

public class CustomerUserEntity extends UserEntity {

    private String shippingAddress;
    private List<PaymentEntity> customerPayments;

    public CustomerUserEntity (){
        super("", "", Role.CUSTOMERUSER , "", "", "");
        this.shippingAddress = null;
        this.customerPayments = null;
    }

    public CustomerUserEntity(String email, String password, String firstName, String lastName, String fiscalCode, String shippingAddress, List<PaymentEntity> customerPayments) {
        super(email, password, Role.CUSTOMERUSER, firstName, lastName, fiscalCode);
        this.shippingAddress = shippingAddress;
        this.customerPayments = customerPayments;
    }

    public CustomerUserEntity(UserEntity user) {
        super(user.getEmail(), user.getPassword(), Role.CUSTOMERUSER, user.getFirstName(), user.getLastName(), user.getFiscalCode());
        this.shippingAddress = null;
        this.customerPayments = null;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<PaymentEntity> getCustomerPayments() {
        return customerPayments;
    }

    public void setCustomerPayments(List<PaymentEntity> customerPayments) {
        this.customerPayments = customerPayments;
    }
}
