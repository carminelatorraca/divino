package account;

import payment.PaymentEntity;

import java.util.List;

public class CustomerUserEntity extends UserEntity {

    private String fiscalCode;
    private String shippingAddress;
    private List<PaymentEntity> customerPayments;

}
