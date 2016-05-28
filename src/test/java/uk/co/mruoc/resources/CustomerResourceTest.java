package uk.co.mruoc.resources;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.mruoc.*;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.client.CustomerClient;
import uk.co.mruoc.client.CustomerResponse;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static uk.co.mruoc.TestConfig.*;
import static uk.co.mruoc.api.Customer.*;

public class CustomerResourceTest {

    @ClassRule
    public static final DropwizardAppRule<Config> RULE = new DropwizardAppRule<>(Application.class, RESOURCE_PATH);

    private final DatabaseConfigLoader configLoader = new DatabaseConfigLoader();
    private final DatabaseConfig databaseConfig = configLoader.loadClasspathDatabaseConfig(CONFIG_PATH);
    private final TestDatabase database = new TestDatabase(databaseConfig);
    private final CustomerClient client = new CustomerClient();

    @Before
    public void setUp() {
        database.setUp();
    }

    @After
    public void tearDown() throws SQLException {
        database.clearCustomerTable();
    }

    @Test
    public void shouldCreateCustomer() {
        Customer customer = new CustomerBuilder()
                .setAccountNumber("123456")
                .setFirstName("Michael")
                .setSurname("Ruocco")
                .setBalance(BigDecimal.valueOf(999.00))
                .build();

        CustomerResponse response = client.createCustomer(customer);

        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.getHeader("Location")).isEqualTo("http://localhost:8090/web-template/ws/v1/customers/123456");
        assertThat(response.getCustomer()).isEqualToComparingFieldByField(customer);
    }

    @Test
    public void shouldGetCustomer() {
        Customer customer = new CustomerBuilder()
                .setAccountNumber("123456")
                .setFirstName("Michael")
                .setSurname("Ruocco")
                .setBalance(BigDecimal.valueOf(999.00))
                .build();

        client.createCustomer(customer);

        CustomerResponse response = client.getCustomer(customer.getAccountNumber());

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getCustomer()).isEqualToComparingFieldByField(customer);
    }

    @Test
    public void shouldGetCustomers() {
        Customer customer1 = new CustomerBuilder()
                .setAccountNumber("111111")
                .setFirstName("Michael")
                .setSurname("Ruocco")
                .setBalance(BigDecimal.valueOf(999.00))
                .build();

        client.createCustomer(customer1);

        Customer customer2 = new CustomerBuilder()
                .setAccountNumber("222222")
                .setFirstName("Laura")
                .setSurname("Noble")
                .setBalance(BigDecimal.valueOf(123.00))
                .build();

        client.createCustomer(customer2);

        CustomerResponse response = client.getCustomers();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getHeader("X-Total-Count")).isEqualTo("2");
        assertThat(response.getCustomers().size()).isEqualTo(2);
        assertThat(response.getCustomers().get(0)).isEqualToComparingFieldByField(customer1);
        assertThat(response.getCustomers().get(1)).isEqualToComparingFieldByField(customer2);
    }

    @Test
    public void shouldUpdateCustomer() {
        Customer customer = new CustomerBuilder()
                .setAccountNumber("111111")
                .setFirstName("Michael")
                .setSurname("Ruocco")
                .setBalance(BigDecimal.valueOf(999.00))
                .build();

        client.createCustomer(customer);

        Customer updateCustomer = new CustomerBuilder()
                .setAccountNumber("111111")
                .setFirstName("Laura")
                .setSurname("Noble")
                .setBalance(BigDecimal.valueOf(123.00))
                .build();

        CustomerResponse response = client.updateCustomer(updateCustomer);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getCustomer()).isEqualToComparingFieldByField(updateCustomer);
    }

    @Test
    public void shouldDeleteCustomer() {
        Customer customer = new CustomerBuilder()
                .setAccountNumber("111111")
                .setFirstName("Michael")
                .setSurname("Ruocco")
                .setBalance(BigDecimal.valueOf(999.00))
                .build();

        client.createCustomer(customer);

        CustomerResponse response = client.deleteCustomer(customer.getAccountNumber());

        assertThat(response.getStatusCode()).isEqualTo(204);
    }

}
