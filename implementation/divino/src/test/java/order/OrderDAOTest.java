package order;

import account.AccountDAOTest;
import account.AccountEntity;
import account.CustomerUserEntity;
import catalog.CatalogDAO;
import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import payment.PaymentEntity;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDAOTest extends DataSourceBasedDBTestCase {
    OrderDAO orderDAO;

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
        orderDAO = new OrderDAO(getDataSource().getConnection());
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    void createOrder() throws Exception {
        ITable expectedTable = new FlatXmlDataSetBuilder()
                .build(AccountDAOTest.class.getClassLoader().getResourceAsStream("db/expected/OrderDAOTest/saveOrder.xml")).getTable("orders");

        CustomerUserEntity customer = new CustomerUserEntity();
        customer.setAccountID(3);
        orderDAO.createOrder(customer);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("orders");

        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }

    @Test
    void updateOrder() {
    }

    @Test
    void saveOrderItem() throws Exception {
        ITable expectedTable = new FlatXmlDataSetBuilder()
                .build(AccountDAOTest.class.getClassLoader().getResourceAsStream("db/expected/OrderDAOTest/saveOrderItem.xml")).getTable("orders");

        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setProductID(1);
        orderItem.setOrder(1);
        orderItem.setProductVat(22);
        orderItem.setProductDescription("buon vino");
        orderItem.setProductPrice(120);

        orderDAO.saveOrderItem(orderItem);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("orders");

        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }

    @Test
    void retrieveAllOrders() {
    }

    @Test
    void retrieveOrder() {
    }

    @Test
    void retrieveAllOrdersToShip() {
    }

    @Test
    void retrieveAllOrdersToPack() {
    }

    @Test
    void getCustomerOrders() {
    }

    @Test
    void getOrderItems() {
    }

    @Test
    void savePayment() throws Exception {
        ITable expectedTable = new FlatXmlDataSetBuilder()
                .build(AccountDAOTest.class.getClassLoader().getResourceAsStream("db/expected/OrderDAOTest/savePayment.xml")).getTable("payments");

        PaymentEntity payment = new PaymentEntity();
        payment.setOrderNumber(2);
        payment.setPaymentStatus("paid");
        payment.setPaymentDescription("pagamento ordine n. 2");
        payment.setPaidAmount(150.00);
        payment.setPaymentMethod("generic payment");
        payment.setPaymentNumber(4);

        orderDAO.savePayment(payment);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("payments");

        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }
}