package catalog;

import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
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
    void removeProduct() throws Exception {
        ITable expectedTable = new FlatXmlDataSetBuilder().build(CatalogDAOTest.class.getClassLoader().getResourceAsStream("db/expected/CatalogDAOTest/deleteProduct.xml")).getTable("products");

        ProductEntity product = new ProductEntity();
        product.setProductId(1);

        catalogDAO.removeProduct(product);

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("products");

        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }

    @Test
    void addProduct() throws Exception {
        ITable expectedTable = new FlatXmlDataSetBuilder()
                .build(CatalogDAOTest.class.getClassLoader().getResourceAsStream("db/expected/ProductsDAOTest/testDoSaveProducts.xml"))
                .getTable("products");

        catalogDAO.addProduct(new ProductEntity(1, "Rosematte", "Bel vino", "1", 120.99, 21, true, 0.0, 22, true, "3.jpg"));

        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("products");

        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }

    @Test
    void updateProduct() {

    }

    @Test
    void updateStock() {

    }
}