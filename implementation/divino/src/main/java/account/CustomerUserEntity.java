package account;

import payment.PaymentEntity;

import java.util.List;

public class CustomerUserEntity extends UserEntity {

    private List<AddressEntity> shippingAddresses;
    private List<PaymentEntity> customerPayments;

    public CustomerUserEntity (){
        super("", "", Role.CUSTOMERUSER , "", "", "");
        this.shippingAddresses = null;
        this.customerPayments = null;
    }

    public CustomerUserEntity(String email, String password, Role role, String firstName, String lastName, String fiscalCode, List<AddressEntity> shippingAddresses, List<PaymentEntity> customerPayments) {
        super(email, password, role, firstName, lastName, fiscalCode);
        this.shippingAddresses = shippingAddresses;
        this.customerPayments = customerPayments;
    }

    public CustomerUserEntity(AccountEntity account, List<AddressEntity> shippingAddresses, List<PaymentEntity> customerPayments) {
        super(account);
        this.shippingAddresses = shippingAddresses;
        this.customerPayments = customerPayments;
    }

    public CustomerUserEntity(UserEntity user) {
        super(user.getEmail(), user.getPassword(), Role.CUSTOMERUSER, user.getFirstName(), user.getLastName(), user.getFiscalCode());
        this.shippingAddresses = null;
        this.customerPayments = null;
    }

    public List<AddressEntity> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(List<AddressEntity> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public List<PaymentEntity> getCustomerPayments() {
        return customerPayments;
    }

    public void setCustomerPayments(List<PaymentEntity> customerPayments) {
        this.customerPayments = customerPayments;
    }
}
