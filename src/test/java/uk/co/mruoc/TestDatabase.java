package uk.co.mruoc;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDatabase {

    private final DatabaseConfig config;

    public TestDatabase(DatabaseConfig config) {
        this.config = config;
    }

    public void setUp() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.migrate();
    }

    public void clearCustomerTable() {
        clearTable("customer");
    }

    private void clearTable(String tableName) {
        DataSource dataSource = getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement("truncate table " + tableName)) {
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

}
