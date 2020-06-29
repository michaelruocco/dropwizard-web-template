package uk.co.mruoc;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
    public void the_customer_needs_to_be_updated_to(DataTable table) {
        List<Customer> inputCustomers = customerConverter.toCustomers(table);
        this.updateCustomer = inputCustomers.get(0);
    }

    @Given("^customer \"([^\"]*)\" does not exist$")
    public void customer_does_not_exist(String accountNumber) {
        CustomerResponse response = client.getCustomer(accountNumber);
        if (response.getStatusCode() == 200)
            client.deleteCustomer(accountNumber);
    }

    @When("^a get request is made for customer \"([^\"]*)\"$")
    public void a_get_request_is_made_for_customer(String id) {
        customerResponse = client.getCustomer(id);
    }

    @When("^a get request is made for all customers$")
    public void a_get_request_is_made_for_all_customers() {
        customerResponse = client.getCustomers();
    }

    @When("^the customer data is updated")
    public void the_customer_data_is_updated() {
        customerResponse = client.updateCustomer(updateCustomer);
    }

    @When("^the customer \"([^\"]*)\" is deleted$")
    public void the_customer_is_deleted(String accountNumber) {
        customerResponse = client.deleteCustomer(accountNumber);
    }

    @Then("^the following customer is returned$")
    public void the_following_customer_is_returned(DataTable table) {
        List<Customer> expectedCustomers = customerConverter.toCustomers(table);
        Customer expectedCustomer = expectedCustomers.get(0);
        assertThat(customerResponse.getCustomer()).isEqualToComparingOnlyGivenFields(expectedCustomer);
    }

    @Then("^the following customers are returned$")
    public void the_following_customers_are_returned(DataTable table) {
        List<Customer> expectedCustomers = customerConverter.toCustomers(table);
        assertMatchesReturnedCustomers(expectedCustomers);
    }

    @Then("^the service returns a response code (\\d+)$")
    public void the_service_returns_a_response_code(int expectedResponseCode) {
        assertThat(customerResponse.getStatusCode()).isEqualTo(expectedResponseCode);
    }

    @Then("^the response header contains \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void the_response_header_contains_with_value(String headerName, String expectedHeaderValue) {
        String headerValue = customerResponse.getHeader(headerName);
        assertThat(headerValue).isEqualTo(expectedHeaderValue);
    }

    @Then("^the service returns error message \"([^\"]*)\"$")
    public void the_service_returns_error_message(String expectedMessage) {
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

}