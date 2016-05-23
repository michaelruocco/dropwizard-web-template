package uk.co.tpplc;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.co.mruoc.api.Customer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMaintenance {

    private final TestClient client = new TestClient();

    private CustomerResponse customerResponse;
    private Customer newCustomer;

    @Given("^the following customers exist$")
    public void the_following_customers_exist(List<Customer> inputCustomers) throws Throwable {
        inputCustomers.forEach(client::createCustomer);
    }

    @Given("^we have a new customer to create with the following data$")
    public void we_have_a_new_customer_to_create_with_the_following_data(List<Customer> inputCustomers) throws Throwable {
        this.newCustomer = inputCustomers.get(0);
    }

    @Given("^the customer data is posted$")
    public void the_customer_data_is_posted() throws Throwable {
        customerResponse = client.createCustomer(newCustomer);
    }

    @When("^a get request is made for customer \"([^\"]*)\"$")
    public void a_get_request_is_made_for_customer(String id) throws Throwable {
        customerResponse = client.getCustomer(id);
    }

    @Then("^the following customer is returned$")
    public void the_following_customer_is_returned(List<Customer> expectedCustomers) throws Throwable {
        Customer expectedCustomer = expectedCustomers.get(0);
        assertThat(customerResponse.getCustomer()).isEqualToComparingOnlyGivenFields(expectedCustomer);
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

}