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
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static uk.co.mruoc.TestConfig.*;
import static uk.co.mruoc.api.Customer.*;

public class CustomerResourceTest {

    @ClassRule
    public static final DropwizardAppRule<Config> RULE = new DropwizardAppRule<>(Application.class, RESOURCE_PATH);

    private final DatabaseConfigLoader configLoader = new DatabaseConfigLoader();
    private final DatabaseConfig databaseConfig = configLoader.loadClasspathDatabaseConfig(CONFIG_PATH);
    private final TestDatabase database = new TestDatabase(databaseConfig);
    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerClient client = new CustomerClient();

    @Before
    public void setUp() {
        database.setUp();
        database.setUpCustomers();
    }

    @After
    public void tearDown() throws SQLException {
        database.clearCustomers();
    }

    @Test
    public void shouldCreateCustomer() {
        Customer customer = customerBuilder.buildNewCustomer();

        CustomerResponse response = client.createCustomer(customer);

        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.getHeader("Location")).isEqualTo(buildNewCustomerUrl(customer));
        assertThat(response.getCustomer()).isEqualToComparingFieldByField(customer);
    }

    @Test
    public void shouldGetCustomer() {
        Customer customer = customerBuilder.buildCustomer1();

        CustomerResponse response = client.getCustomer(customer.getAccountNumber());

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getCustomer()).isEqualToComparingFieldByField(customer);
    }

    @Test
    public void shouldGetCustomers() {
        List<Customer> customers = customerBuilder.buildCustomerList();

        CustomerResponse response = client.getCustomers();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getHeader("X-Total-Count")).isEqualTo("2");
        assertThat(response.getCustomers().size()).isEqualTo(2);
        assertThat(response.getCustomers().get(0)).isEqualToComparingFieldByField(customers.get(0));
        assertThat(response.getCustomers().get(1)).isEqualToComparingFieldByField(customers.get(1));
    }

    @Test
    public void shouldUpdateCustomer() {
        Customer updateCustomer = customerBuilder.buildUpdateCustomer1();

        CustomerResponse response = client.updateCustomer(updateCustomer);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getCustomer()).isEqualToComparingFieldByField(updateCustomer);
    }

    @Test
    public void shouldDeleteCustomer() {
        Customer customer = customerBuilder.buildCustomer1();

        CustomerResponse response = client.deleteCustomer(customer.getAccountNumber());

        assertThat(response.getStatusCode()).isEqualTo(204);
    }

    private String buildNewCustomerUrl(Customer customer) {
        return "http://localhost:8090/web-template/ws/v1/customers/" + customer.getAccountNumber();
    }

}
