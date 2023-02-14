package catalog;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogDAOTest extends DataSourceBasedDBTestCase {
    CatalogDAO catalogDAO;

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
        catalogDAO = new CatalogDAO(getDataSource().getConnection());
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }


    @Test
    void removeProduct() {
        ITable expectedTable = new FlatXmlDataSetBuilder()
                .build(CatalogDAOTest.class.getClassLoader().getResourceAsStream("db/expected/CatalogDAOTest/deleteProduct.xml"))
                .getTable(ClientiDAO.TABLE_NAME);


        clientiDAO.doDelete("peppeRoma");

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable(ClientiDAO.TABLE_NAME);

        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }

    @Test
    void addProduct() {

    }

    @Test
    void updateProduct() {

    }

    @Test
    void updateStock() {

    }
}