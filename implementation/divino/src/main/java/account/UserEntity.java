package account;

import java.util.HashSet;

public class UserEntity extends AccountEntity {

    private String firstName;
    private String lastName;
    private String fiscalCode;


    public UserEntity(String email, String password, Role role, String firstName, String lastName, String fiscalCode) {
        super(email, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
    }

    public UserEntity(AccountEntity account) {
        super(account.getAccountID(), account.getEmail(), account.getPassword(), account.getRole());
        this.firstName = null;
        this.lastName = null;
        this.fiscalCode = null;
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


}
