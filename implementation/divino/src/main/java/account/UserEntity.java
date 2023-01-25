package account;

public class UserEntity extends AccountEntity {
    private String firstName;
    private String lastName;

    public UserEntity(String email, String password) {
        super(email, password, Role.CUSTOMERUSER);
    }
}
