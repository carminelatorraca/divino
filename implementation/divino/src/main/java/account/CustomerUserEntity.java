package account;

import payment.PaymentEntity;

import java.util.List;

public class CustomerUserEntity extends AccountEntity {

    private String fiscalCode;
    private String shippingAddress;
    private List<PaymentEntity> customerPayments;

    public CustomerUserEntity(String email, String password, String firstName, String lastName) {
        super(email, password, Role.CUSTOMERUSER, firstName, lastName);
        this.fiscalCode = null;
        this.shippingAddress = null;
    }

    public CustomerUserEntity() {
        super();
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public List<PaymentEntity> getCustomerPayments() {
        return customerPayments;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCustomerPayments(List<PaymentEntity> customerPayments) {
        this.customerPayments = customerPayments;
    }
}
