package account;

import payment.PaymentEntity;

import java.util.HashSet;
import java.util.List;

public class CustomerUserEntity extends UserEntity {

    //private List<AddressEntity> shippingAddresses;
    private List<PaymentEntity> customerPayments;
    private HashSet<AddressEntity> shippingAddresses;

    public CustomerUserEntity (){
        super(-1,"", "", Role.CUSTOMERUSER , "", "", "");
        this.shippingAddresses = null;
        this.customerPayments = null;
    }

    public CustomerUserEntity(int accountID, String email, String password, Role role, String firstName, String lastName, String fiscalCode, HashSet<AddressEntity> shippingAddresses, List<PaymentEntity> customerPayments) {
        super(accountID,email, password, role, firstName, lastName, fiscalCode);
        this.shippingAddresses = shippingAddresses;
        this.customerPayments = customerPayments;
    }

    public CustomerUserEntity(AccountEntity account, HashSet<AddressEntity> shippingAddresses, List<PaymentEntity> customerPayments) {
        super(account);
        this.shippingAddresses = shippingAddresses;
        this.customerPayments = customerPayments;
    }

    public CustomerUserEntity(UserEntity user) {
        super(user.getAccountID(),user.getEmail(), user.getPassword(), Role.CUSTOMERUSER, user.getFirstName(), user.getLastName(), user.getFiscalCode());
        this.shippingAddresses = null;
        this.customerPayments = null;
    }

    public HashSet<AddressEntity> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(HashSet<AddressEntity> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public List<PaymentEntity> getCustomerPayments() {
        return customerPayments;
    }

    public void setCustomerPayments(List<PaymentEntity> customerPayments) {
        this.customerPayments = customerPayments;
    }
}
