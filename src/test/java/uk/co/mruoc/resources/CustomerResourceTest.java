package uk.co.mruoc.resources;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import uk.co.mruoc.Application;
import uk.co.mruoc.Config;
import uk.co.mruoc.TestDatabase;
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

    private final TestDatabase database = new TestDatabase();
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
                .setBalance(BigDecimal.valueOf(999))
                .build();

        CustomerResponse response = client.createCustomer(customer);

        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.getHeader("Location")).isEqualTo("http://localhost:8090/web-template/ws/v1/customers/123456");
        assertThat(response.getCustomer()).isEqualToComparingFieldByField(customer);
    }

}
