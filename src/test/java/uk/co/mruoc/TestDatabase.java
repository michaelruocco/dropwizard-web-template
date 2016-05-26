package uk.co.mruoc;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static uk.co.mruoc.TestConfig.CONFIG_PATH;

public class TestDatabase {

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
        DatabaseConfig databaseConfig = loadDatabaseConfig();
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(databaseConfig.getUrl());
        dataSource.setUser(databaseConfig.getUser());
        dataSource.setPassword(databaseConfig.getPassword());
        return dataSource;
    }

    private DatabaseConfig loadDatabaseConfig() {
        try {
            Yaml yaml = new Yaml();
            try (InputStream stream = getClass().getResourceAsStream("/" + CONFIG_PATH)) {
                Map<String, Object> configValues = (Map<String,Object>) yaml.load(stream);
                Map<String, Object> databaseConfigValues = (Map<String,Object>) configValues.get("database");
                return new DatabaseConfig(databaseConfigValues);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
