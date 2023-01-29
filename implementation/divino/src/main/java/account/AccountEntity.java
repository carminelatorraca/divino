package account;

public class AccountEntity {

    public enum Role {CUSTOMERUSER, WAREHOUSEUSER, MANAGERUSER};

    private String accountID;
    private String email;
    private String password;
    private Role role;

    public AccountEntity(String email, String password, Role role) {
        accountID = "-1";
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AccountEntity() {
        accountID = "-1";
        email = null;
        password = null;
        role = null;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
