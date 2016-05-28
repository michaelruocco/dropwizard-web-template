package uk.co.mruoc;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import uk.co.mruoc.api.Customer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TestDatabase {

    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final DatabaseConfig config;

    public TestDatabase(DatabaseConfig config) {
        this.config = config;
    }

    public void setUp() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.migrate();
    }

    public void clearCustomers() {
        clearTable("customer");
    }

    public void setUpCustomers() {
        addCustomers(customerBuilder.buildCustomerList());
    }

    private void addCustomers(List<Customer> customers) {
        for (Customer customer : customers)
            addCustomer(customer);
    }

    private void addCustomer(Customer customer) {
        DataSource dataSource = getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(customerInsertQuery())) {
                stmt.setString(1, customer.getAccountNumber());
                stmt.setString(2, customer.getFirstName());
                stmt.setString(3, customer.getSurname());
                stmt.setBigDecimal(4, customer.getBalance());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new TestDatabaseException(e);
        }
    }

    private String customerInsertQuery() {
        String query = "insert into customer ";
        query += "(accountNumber, firstName, surname, balance) ";
        return query + "values (?,?,?,?)";
    }

    private void clearTable(String tableName) {
        DataSource dataSource = getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("truncate table " + tableName)) {
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new TestDatabaseException(e);
        }
    }

    private DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(config.getDriverClass());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUser());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

    private static class TestDatabaseException extends RuntimeException {

        TestDatabaseException(Throwable cause) {
            super(cause);
        }

    }

}
