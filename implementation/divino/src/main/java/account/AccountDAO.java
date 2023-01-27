package account;

import java.sql.*;

public class AccountDAO {

    private static final String TABLE_NAME = "user";
    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAccount(CustomerUserEntity customer) throws SQLException {

        String query = "INSERT INTO " + TABLE_NAME + " (email, password, firstName, lastName, role) VALUES (?,?,?,?,?);";

        PreparedStatement statement = connection.prepareStatement(query);

        if (check(customer.getEmail())) {
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getFirstName());
            statement.setString(4, customer.getLastName());
            statement.setString(5, customer.getRole().toString());

            statement.executeUpdate();
        }
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
            account.setAccountID(rs.getInt("accountID"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("password"));
            account.setRole((AccountEntity.Role.valueOf(rs.getString("role"))));
        }
        return account;
    }

    public void updateCustomerAccount(CustomerUserEntity account) throws SQLException {
        PreparedStatement pst = connection.prepareStatement("UPDATE firstName, lastName, email, password, fiscalCode" + TABLE_NAME + " SET WHERE accountID = ?");
        pst.setString(1, account.getFirstName());
        pst.setString(2, account.getLastName());
        pst.setString(3, account.getEmail());
        pst.setString(4, account.getPassword());
        pst.setString(4, account.getFiscalCode());
        pst.setInt(5, account.getAccountID());

        pst.executeUpdate();
    }
}