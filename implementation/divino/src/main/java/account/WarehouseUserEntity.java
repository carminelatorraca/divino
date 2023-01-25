package account;

public class WarehouseUserEntity extends AccountEntity {

    private String firstName;
    private String lastName;

    public WarehouseUserEntity(String email, String password, String firstName, String lastName) {
        super(email, password, Role.WAREHOUSEUSER);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
