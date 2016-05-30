package uk.co.mruoc;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.api.Customer.CustomerBuilder;
import uk.co.mruoc.client.CustomerClient;
import uk.co.mruoc.client.CustomerResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMaintenance {

    private static final String CONFIG_PATH = "config/local-web-template.yml";

    private final CustomerClient client = new CustomerClient();
    private final CustomerConverter customerConverter = new CustomerConverter();
    private final DatabaseConfigLoader configLoader = new DatabaseConfigLoader();
    private final DatabaseConfig databaseConfig = configLoader.loadFileSystemDatabaseConfig(CONFIG_PATH);
    private final TestDatabase database = new TestDatabase(databaseConfig);

    private CustomerResponse customerResponse;
    private Customer newCustomer;
    private Customer updateCustomer;

    @After
    public void tearDown() {
        database.clearCustomers();
    }

    @Given("^the following customers exist$")
    public void the_following_customers_exist(DataTable table) throws Throwable {
        List<Customer> inputCustomers = customerConverter.toCustomers(table);
        inputCustomers.forEach(client::createCustomer);
    }

    @Given("^we have a new customer to create with the following data$")
    public void we_have_a_new_customer_to_create_with_the_following_data(DataTable table) throws Throwable {
        List<Customer> inputCustomers = customerConverter.toCustomers(table);
        this.newCustomer = inputCustomers.get(0);
    }

    @Given("^the customer data is posted$")
    public void the_customer_data_is_posted() throws Throwable {
        customerResponse = client.createCustomer(newCustomer);
    }

    @Given("^the customer needs to be updated to$")
    public void the_customer_needs_to_be_updated_to(DataTable table) throws Throwable {
        List<Customer> inputCustomers = customerConverter.toCustomers(table);
        this.updateCustomer = inputCustomers.get(0);
    }

    @Given("^customer \"([^\"]*)\" does not exist$")
    public void customer_does_not_exist(String accountNumber) throws Throwable {
        CustomerResponse response = client.getCustomer(accountNumber);
        if (response.getStatusCode() == 200)
            client.deleteCustomer(accountNumber);
    }

    @When("^a get request is made for customer \"([^\"]*)\"$")
    public void a_get_request_is_made_for_customer(String id) throws Throwable {
        customerResponse = client.getCustomer(id);
    }

    @When("^a get request is made for all customers$")
    public void a_get_request_is_made_for_all_customers() throws Throwable {
        customerResponse = client.getCustomers();
    }

    @When("^the customer data is updated")
    public void the_customer_data_is_updated() throws Throwable {
        customerResponse = client.updateCustomer(updateCustomer);
    }

    @When("^the customer \"([^\"]*)\" is deleted$")
    public void the_customer_is_deleted(String accountNumber) throws Throwable {
        customerResponse = client.deleteCustomer(accountNumber);
    }

    @Then("^the following customer is returned$")
    public void the_following_customer_is_returned(DataTable table) throws Throwable {
        List<Customer> expectedCustomers = customerConverter.toCustomers(table);
        Customer expectedCustomer = expectedCustomers.get(0);
        assertThat(customerResponse.getCustomer()).isEqualToComparingOnlyGivenFields(expectedCustomer);
    }

    @Then("^the following customers are returned$")
    public void the_following_customers_are_returned(DataTable table) throws Throwable {
        List<Customer> expectedCustomers = customerConverter.toCustomers(table);
        assertMatchesReturnedCustomers(expectedCustomers);
    }

    @Then("^the service returns a response code (\\d+)$")
    public void the_service_returns_a_response_code(int expectedResponseCode) throws Throwable {
        assertThat(customerResponse.getStatusCode()).isEqualTo(expectedResponseCode);
    }

    @Then("^the response header contains \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void the_response_header_contains_with_value(String headerName, String expectedHeaderValue) throws Throwable {
        String headerValue = customerResponse.getHeader(headerName);
        assertThat(headerValue).isEqualTo(expectedHeaderValue);
    }

    @Then("^the service returns error message \"([^\"]*)\"$")
    public void the_service_returns_error_message(String expectedMessage) throws Throwable {
        assertThat(customerResponse.getErrorMessage()).isEqualTo(expectedMessage);
    }

    private void assertMatchesReturnedCustomers(List<Customer> expectedCustomers) {
        List<Customer> customers = customerResponse.getCustomers();
        assertThat(customers.size()).isEqualTo(expectedCustomers.size());
        for (int i = 0; i < expectedCustomers.size(); i++) {
            Customer customer = customers.get(i);
            Customer expectedCustomer = expectedCustomers.get(i);
            assertThat(customer).isEqualToComparingFieldByField(expectedCustomer);
        }
    }

    private static class CustomerConverter {

        List<Customer> toCustomers(DataTable table) {
            List<Customer> customers = new ArrayList<>();
            List<DataTableRow> rows = table.getGherkinRows();
            for (int r = 1; r < rows.size(); r++) {
                DataTableRow row = rows.get(r);
                customers.add(toCustomer(row));
            }
            return customers;
        }

        private Customer toCustomer(DataTableRow row) {
            List<String> cells = row.getCells();
            return new CustomerBuilder()
                    .setAccountNumber(cells.get(0))
                    .setFirstName(cells.get(1))
                    .setSurname(cells.get(2))
                    .setBalance(new BigDecimal(cells.get(3)))
                    .build();
        }

    }

}