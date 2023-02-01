package account;

import java.sql.*;
import java.util.HashSet;

public class AccountDAO {

    private static final String TABLE_NAME = "accounts";
    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    public AccountDAO() {
        //this.connection = connection;
    }

    public void createAccount(AccountEntity account) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (email, password, role) VALUES (?,?,?);";
        PreparedStatement statement = connection.prepareStatement(query);
        //if (check(account.getEmail())) {
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getRole().toString());
            statement.executeUpdate();
        //}
    }


    public void createUser(UserEntity user) throws SQLException {
        String query = "INSERT INTO users (account_id, firstName, lastName, fiscalCode) VALUES (?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user.getAccountID());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getFiscalCode());
        statement.executeUpdate();
    }

    private boolean check(String email) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("SELECT email FROM " + TABLE_NAME + "WHERE email = ?;");
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        return rs.wasNull();
    }

    public AccountEntity retrieveAccount(String email, String password) throws SQLException {
        AccountEntity account = new AccountEntity();

        PreparedStatement pst = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND password = ?;");
        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();


        while (rs.next()) {
            account.setAccountID(rs.getInt("account_id"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("password"));
            account.setRole((AccountEntity.Role.valueOf(rs.getString("role"))));
        }
        return account;
    }

    public UserEntity retrieveUser(AccountEntity account) throws SQLException {
        UserEntity user = new UserEntity(account);

        PreparedStatement pst = connection.prepareStatement("SELECT * FROM users WHERE account_id = ?;");
        pst.setInt(1, account.getAccountID());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setFiscalCode(rs.getString("fiscalCode"));
        }
        return user;
    }

    public void updateCustomerAccount(UserEntity user) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("UPDATE users SET firstName=?, lastName=?, email=?, password=?, fiscalCode=? WHERE account_id = ?;");
        pst.setString(1, user.getFirstName());
        pst.setString(2, user.getLastName());
        pst.setString(3, user.getEmail());
        pst.setString(4, user.getPassword());
        pst.setString(5, user.getFiscalCode());
        pst.setInt(6, user.getAccountID());
        pst.executeUpdate();
    }


    //AGGIUNGERE METODO PER RECUPERARE GLI INDIRIZZI DI UN DETERMINATO CUSTOMER
    public void addAddress(CustomerUserEntity user, AddressEntity address) throws SQLException {
        String query = "INSERT INTO addresses (account_id, street, number, city, postalCode, country, favourite) VALUES (?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user.getAccountID());
        statement.setString(2, address.getStreet());
        statement.setString(3, address.getNumber());
        statement.setString(4, address.getCity());
        statement.setString(5, address.getPostalCode());
        statement.setString(6, address.getCountry());
        statement.setInt(7, address.getFavourite());
        statement.executeUpdate();
    }

    public HashSet<AddressEntity> retrieveAddresses (CustomerUserEntity user) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM addresses WHERE account_id = ?;");
        pst.setInt(1, user.getAccountID());

        ResultSet rs = pst.executeQuery();

        AddressEntity address = new AddressEntity();
        HashSet<AddressEntity> shippingAddresses = null;
        while (rs.next()) {
            address.setStreet(rs.getString("street"));
            address.setNumber(rs.getString("number"));
            address.setCity(rs.getString("city"));
            address.setPostalCode(rs.getString("postalCode"));
            address.setCountry(rs.getString("country"));
            address.setFavourite(rs.getInt("favourite"));
            shippingAddresses.add(address);
        }
        return shippingAddresses;
    }
}