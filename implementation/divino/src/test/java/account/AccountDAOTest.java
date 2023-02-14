package account;

import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

public class AccountDAOTest extends DataSourceBasedDBTestCase {
    private AccountDAO accountDAO;

    @Override
    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:db/init/database_divino.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream("db/init/init.xml"));
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        getDataSet();
        accountDAO = new AccountDAO(getDataSource().getConnection());
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    void createAccount() throws Exception {
        ITable expectedTable = new FlatXmlDataSetBuilder()
                .build(AccountDAOTest.class.getClassLoader().getResourceAsStream("db/expected/AccountDAOTest/saveAccount.xml")).getTable("accounts");

        AccountEntity account = new AccountEntity();
        account.setEmail("mario.giovanni@gmail.com");
        account.setPassword("marioGiovanni1");
        account.setRole(AccountEntity.Role.CUSTOMERUSER);
        accountDAO.createAccount(account);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("accounts");

        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }

    @Test
    void createUser() {

    }

    @Test
    void retrieveAccount() throws Exception {
        AccountEntity retrieveAccount = accountDAO.retrieveAccount("divino@test.it", "testing");
        assertNotNull(retrieveAccount);
    }

    @Test
    void updateCustomerAccount() throws Exception {
        ITable expectedTable = new FlatXmlDataSetBuilder().build(AccountDAOTest.class.getClassLoader().getResourceAsStream("db/expected/AccountDAOTest/updateAccount.xml")).getTable("users");

        UserEntity user = new UserEntity();
        user.setAccountID(1);
        user.setFirstName("Carmine");
        user.setLastName("La Torraca");
        user.setFiscalCode("LTRCMSDSDSDCSD12");

        accountDAO.updateCustomerAccount(user);

        IDataSet databaseDataSet = getConnection().createDataSet();
        //ITable actualTable = databaseDataSet.getTable("users");

        //Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }
}