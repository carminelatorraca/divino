package account;
public class AccountEntity {

    public enum Role{CUSTOMERUSER, WAREHOUSEUSER, MANAGERUSER};

    private int accountID;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;

    public AccountEntity(String email, String password, Role role, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public AccountEntity() {
        accountID = -1;
        email = null;
        password = null;
        role = null;
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

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
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
}
