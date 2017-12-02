package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import org.junit.Test;
import uk.co.mruoc.FakeCustomer1;
import uk.co.mruoc.FakeHttpSession;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.Customer;
import uk.co.mruoc.facade.FakeCustomerFacade;
import uk.co.mruoc.view.*;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UpdateCustomerViewResourceTest {

    private final FakeCustomerFacade facade = new FakeCustomerFacade();
    private final FakeHttpSession session = new FakeHttpSession();
    private final UriInfo uriInfo = new FakeUriInfo();

    private final UpdateCustomerViewResource resource = new UpdateCustomerViewResource(facade);

    @Test
    public void showUpdateCustomerShouldReturnUpdateCustomerView() {
        Customer customer = new FakeCustomer1();
        facade.setCustomerToRead(customer);

        UpdateCustomerView view = (UpdateCustomerView) resource.showUpdateCustomer(uriInfo, session, customer.getAccountNumber());

        assertThat(view.getCustomer()).isEqualTo(customer);
    }

    @Test
    public void shouldNotUpdateCustomerIfCustomerDoesNotExist() {
        givenCustomerDoesNotExist();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(new FakeCustomer1());

        resource.updateCustomer(uriInfo, session, form);

        assertThat(facade.updateCustomerCalled()).isFalse();
    }

    @Test
    public void shouldReturnUpdateCustomerViewWithErrorMessageIfCustomerDoesNotExist() {
        givenCustomerDoesNotExist();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(new FakeCustomer1());

        UpdateCustomerErrorView view = (UpdateCustomerErrorView) resource.updateCustomer(uriInfo, session, form);

        assertThat(view.getError()).isEqualTo("customer 111111 not found");
    }

    @Test
    public void shouldUpdateCustomerIfCustomerExists() {
        givenCustomerExists();
        Customer customer = new FakeCustomer1();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(customer);

        resource.updateCustomer(uriInfo, session, form);

        assertThat(facade.getLastUpdatedCustomer()).isEqualToComparingFieldByField(customer);
    }

    @Test
    public void shouldReturnCustomersViewIfCustomerExists() {
        givenCustomerExists();
        Customer customer = new FakeCustomer1();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(customer);

        View view = resource.updateCustomer(uriInfo, session, form);

        assertThat(view).isInstanceOf(CustomersView.class);
    }

    private void givenCustomerExists() {
        facade.setExists(true);
    }

    private void givenCustomerDoesNotExist() {
        facade.setExists(false);
    }

}
