package account;

import java.sql.*;

public class AccountDAO {

    private static final String TABLE_NAME = "account";
    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    public AccountDAO() {
        //this.connection = connection;
    }

    public void createAccount(AccountEntity account) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (email, password, role) VALUES (?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(query);
        if (check(account.getEmail())) {
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getRole().toString());
            statement.executeUpdate();
        }
    }

    public void createUser(UserEntity user) throws SQLException {
        String query = "INSERT INTO user (AccountID, firstName, lastName, fiscalCode) VALUES (?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getAccountID());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getFiscalCode());
        statement.executeUpdate();
    }

    private boolean check(String email) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("SELECT email FROM " + TABLE_NAME + "WHERE email = ?");
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        return rs.wasNull();
    }

    public AccountEntity retrieveAccount(String email, String password) throws SQLException {
        AccountEntity account = new AccountEntity();

        PreparedStatement pst = connection.prepareStatement("SELECT accountID, email, password, role FROM " + TABLE_NAME + "WHERE email = ? AND password = ?");
        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();


        while (rs.next()) {
            account.setAccountID(rs.getString("accountID"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("password"));
            account.setRole((AccountEntity.Role.valueOf(rs.getString("role"))));
        }
        return account;
    }

    public UserEntity retrieveUser(AccountEntity account) throws SQLException {
        UserEntity user = new UserEntity(account);

        PreparedStatement pst = connection.prepareStatement("SELECT * FROM user WHERE accountID = ?");
        pst.setString(1, account.getAccountID());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setFiscalCode(rs.getString("fiscalCode"));
        }
        return user;
    }

    public void updateCustomerAccount(UserEntity user) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("UPDATE firstName, lastName, email, password, fiscalCode" + TABLE_NAME + " SET WHERE accountID = ?");
        pst.setString(1, user.getFirstName());
        pst.setString(2, user.getLastName());
        pst.setString(3, user.getEmail());
        pst.setString(4, user.getPassword());
        pst.setString(5, user.getFiscalCode());
        pst.executeUpdate();
    }


    //AGGIUNGERE METODO PER RECUPERARE GLI INDIRIZZI DI UN DETERMINATO CUSTOMER
}