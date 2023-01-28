package account;

import payment.PaymentEntity;

import java.util.List;

public class CustomerUserEntity extends AccountEntity {

    private String firstName;
    private String lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
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
