package uk.co.mruoc.resources.rest;

import org.junit.*;
import uk.co.mruoc.*;
import uk.co.mruoc.Customer;
import uk.co.mruoc.ErrorMessage;
import uk.co.mruoc.facade.FakeCustomerFacade;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CustomerResourceTest {

    private final FakeCustomerFacade facade = new FakeCustomerFacade();
    private final FakeUriInfo uriInfo = new FakeUriInfo();

    private final CustomerResource resource = new CustomerResource(facade);

    @Test
    public void getCustomerShouldReturnNotFoundStatusIfCustomerDoesNotExist() {
        facade.setExists(false);

        Response response = resource.getCustomer("");

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void getCustomerShouldReturnErrorMessageIfCustomerDoesNotExist() {
        facade.setExists(false);

        Response response = resource.getCustomer("123456");

        ErrorMessage error = (ErrorMessage) response.getEntity();
        assertThat(error.getMessage()).isEqualTo("customer 123456 not found");
    }

    @Test
    public void getCustomerShouldReturnOkStatus() {
        Customer customer = new FakeCustomer1();
        facade.setCustomerToRead(customer);
        facade.setExists(true);

        Response response = resource.getCustomer(customer.getAccountNumber());

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void getCustomerShouldReturnCustomer() {
        Customer customer = new FakeCustomer1();
        facade.setCustomerToRead(customer);
        facade.setExists(true);

        Response response = resource.getCustomer(customer.getAccountNumber());

        assertThat(response.getEntity()).isEqualTo(customer);
    }

    @Test
    public void getCustomersShouldReturnCustomers() {
        Customer customer1 = new FakeCustomer1();
        Customer customer2 = new FakeCustomer2();
        facade.setCustomersToRead(customer1, customer2);

        Response response = resource.getCustomers();

        List<Customer> customers = (List<Customer>) response.getEntity();
        assertThat(customers).contains(customer1, customer2);
    }

    @Test
    public void getCustomersShouldReturnTotalCountHeader() {
        Customer customer1 = new FakeCustomer1();
        Customer customer2 = new FakeCustomer2();
        facade.setCustomersToRead(customer1, customer2);

        Response response = resource.getCustomers();

        assertThat(response.getHeaderString("X-Total-Count")).isEqualTo("2");
    }

    @Test
    public void createCustomerShouldReturnConflictStatusIfCustomerExists() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(true);

        Response response = resource.createCustomer(customer, uriInfo);

        assertThat(response.getStatus()).isEqualTo(409);
    }

    @Test
    public void createCustomerShouldReturnErrorMessageIfCustomerExists() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(true);

        Response response = resource.createCustomer(customer, uriInfo);

        ErrorMessage error = (ErrorMessage) response.getEntity();
        assertThat(error.getMessage()).isEqualTo("customer 333333 already exists");
    }

    @Test
    public void createCustomerShouldReturnCreatedStatus() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(false);
        facade.setCustomerToRead(customer);

        Response response = resource.createCustomer(customer, uriInfo);

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void createCustomerShouldCreateCustomer() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(false);
        facade.setCustomerToRead(customer);

        resource.createCustomer(customer, uriInfo);

        assertThat(facade.getLastCreatedCustomer()).isEqualTo(customer);
    }

    @Test
    public void createCustomerShouldReturnCreatedCustomerInResponseBody() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(false);
        facade.setCustomerToRead(customer);

        Response response = resource.createCustomer(customer, uriInfo);

        assertThat(response.getEntity()).isEqualTo(customer);
    }

    @Test
    public void createCustomerShouldReturnCreatedCustomerUrlInLocationHeader() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(false);
        facade.setCustomerToRead(customer);
        String expectedUrl = uriInfo.getBaseUri().toString() + "/ws/v1/customers/" + customer.getAccountNumber();

        Response response = resource.createCustomer(customer, uriInfo);

        assertThat(response.getHeaderString("Location")).isEqualTo(expectedUrl);
    }

    @Test
    public void updateCustomerShouldReturnNotFoundStatusIfCustomerDoesNotExist() {
        Customer customer = new FakeUpdatedCustomer1();
        facade.setExists(false);

        Response response = resource.updateCustomer(customer);

        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void updateCustomerShouldReturnErrorMessageIfCustomerDoesNotExist() {
        Customer customer = new FakeUpdatedCustomer1();
        facade.setExists(false);

        Response response = resource.updateCustomer(customer);

        ErrorMessage error = (ErrorMessage) response.getEntity();
        assertThat(error.getMessage()).isEqualTo("customer 111111 not found");
    }

    @Test
    public void updateCustomerShouldReturnOkStatus() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(true);
        facade.setCustomerToRead(customer);

        Response response = resource.updateCustomer(customer);

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void updateCustomerShouldUpdateCustomer() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(true);
        facade.setCustomerToRead(customer);

        resource.updateCustomer(customer);

        assertThat(facade.getLastUpdatedCustomer()).isEqualTo(customer);
    }

    @Test
    public void updateCustomerShouldReturnUpdatedCustomerInResponseBody() {
        Customer customer = new FakeNewCustomer();
        facade.setExists(true);
        facade.setCustomerToRead(customer);

        Response response = resource.updateCustomer(customer);

        assertThat(response.getEntity()).isEqualTo(customer);
    }

    @Test
    public void deleteCustomerShouldReturnNoContentStatus() {
        String accountNumber = "112233";

        Response response = resource.deleteCustomer(accountNumber);

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void deleteCustomerShouldDeleteCustomer() {
        String accountNumber = "112233";

        resource.deleteCustomer(accountNumber);

        assertThat(facade.getLastDeletedAccountNumber()).isEqualTo(accountNumber);
    }

}
