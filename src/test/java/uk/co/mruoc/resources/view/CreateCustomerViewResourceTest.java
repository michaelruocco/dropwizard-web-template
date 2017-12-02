package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import org.junit.Test;
import uk.co.mruoc.*;
import uk.co.mruoc.Customer;
import uk.co.mruoc.facade.FakeCustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreateCustomerViewResourceTest {

    private final FakeCustomerFacade facade = new FakeCustomerFacade();
    private final FakeHttpSession session = new FakeHttpSession();
    private final UriInfo uriInfo = new FakeUriInfo();

    private final CreateCustomerViewResource resource = new CreateCustomerViewResource(facade);

    @Test
    public void showCreateCustomerShouldReturnCreateCustomerView() {
        View view = resource.showCreateCustomer(uriInfo, session);

        assertThat(view).isInstanceOf(CreateCustomerView.class);
    }

    @Test
    public void shouldNotCreateCustomerIfCustomerExists() {
        givenCustomerExists();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(new FakeCustomer1());

        resource.createCustomer(uriInfo, session, form);

        assertThat(facade.createCustomerCalled()).isFalse();
    }

    @Test
    public void shouldReturnCreateCustomerViewWithErrorMessageIfCustomerExists() {
        givenCustomerExists();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(new FakeCustomer1());

        CreateCustomerView view = (CreateCustomerView) resource.createCustomer(uriInfo, session, form);

        assertThat(view.getError()).isEqualTo("customer 111111 already exists");
    }

    @Test
    public void shouldCreateCustomerIfCustomerDoesNotExist() {
        givenCustomerDoesNotExist();
        Customer customer = new FakeCustomer1();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(customer);

        resource.createCustomer(uriInfo, session, form);

        assertThat(facade.getLastCreatedCustomer()).isEqualToComparingFieldByField(customer);
    }

    @Test
    public void shouldReturnCustomersViewIfCustomerDoesNotExist() {
        givenCustomerDoesNotExist();
        Customer customer = new FakeCustomer1();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(customer);

        View view = resource.createCustomer(uriInfo, session, form);

        assertThat(view).isInstanceOf(CustomersView.class);
    }

    private void givenCustomerExists() {
        facade.setExists(true);
    }

    private void givenCustomerDoesNotExist() {
        facade.setExists(false);
    }

}
