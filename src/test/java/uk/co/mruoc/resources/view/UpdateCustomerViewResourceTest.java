package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import org.junit.Test;
import uk.co.mruoc.FakeCustomer1;
import uk.co.mruoc.FakeHttpSession;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.Customer;
import uk.co.mruoc.auth.FakeUserInfo;
import uk.co.mruoc.facade.FakeCustomerFacade;
import uk.co.mruoc.view.*;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UpdateCustomerViewResourceTest {

    private final FakeCustomerFacade facade = new FakeCustomerFacade();
    private final FakeHttpSession session = new FakeHttpSession();
    private final UriInfo uriInfo = new FakeUriInfo();

    private final UpdateCustomerViewResource resource = new UpdateCustomerViewResource(facade);

    @Test
    public void showUpdateCustomerShouldReturnIndexViewIfNotLoggedIn() {
        View view = resource.showUpdateCustomer(uriInfo, session, "");

        assertThat(view).isInstanceOf(IndexView.class);
    }

    @Test
    public void showUpdateCustomerShouldReturnUpdateCustomerViewIfLoggedIn() {
        givenUserIsLoggedIn();
        Customer customer = new FakeCustomer1();
        facade.setCustomerToRead(customer);

        UpdateCustomerView view = (UpdateCustomerView) resource.showUpdateCustomer(uriInfo, session, customer.getAccountNumber());

        assertThat(view.getCustomer()).isEqualTo(customer);
    }

    @Test
    public void shouldNotUpdateCustomerIfNotLoggedIn() {
        resource.updateCustomer(uriInfo, session, new MultivaluedHashMap<>());

        assertThat(facade.updateCustomerCalled()).isFalse();
    }

    @Test
    public void shouldReturnIndexViewIfNotLoggedIn() {
        View view = resource.updateCustomer(uriInfo, session, new MultivaluedHashMap<>());

        assertThat(view).isInstanceOf(IndexView.class);
    }

    @Test
    public void shouldNotUpdateCustomerIfLoggedInAndCustomerDoesNotExist() {
        givenUserIsLoggedIn();
        givenCustomerDoesNotExist();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(new FakeCustomer1());

        resource.updateCustomer(uriInfo, session, form);

        assertThat(facade.updateCustomerCalled()).isFalse();
    }

    @Test
    public void shouldReturnUpdateCustomerViewWithErrorMessageIfLoggedInAndCustomerDoesNotExist() {
        givenUserIsLoggedIn();
        givenCustomerDoesNotExist();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(new FakeCustomer1());

        UpdateCustomerErrorView view = (UpdateCustomerErrorView) resource.updateCustomer(uriInfo, session, form);

        assertThat(view.getError()).isEqualTo("customer 111111 not found");
    }

    @Test
    public void shouldUpdateCustomerIfLoggedInAndCustomerExists() {
        givenUserIsLoggedIn();
        givenCustomerExists();
        Customer customer = new FakeCustomer1();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(customer);

        resource.updateCustomer(uriInfo, session, form);

        assertThat(facade.getLastUpdatedCustomer()).isEqualToComparingFieldByField(customer);
    }

    @Test
    public void shouldReturnCustomersViewIfLoggedInAndCustomerExists() {
        givenUserIsLoggedIn();
        givenCustomerExists();
        Customer customer = new FakeCustomer1();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(customer);

        View view = resource.updateCustomer(uriInfo, session, form);

        assertThat(view).isInstanceOf(CustomersView.class);
    }

    private void givenUserIsLoggedIn() {
        session.setLoggedInUser(new FakeUserInfo());
    }

    private void givenCustomerExists() {
        facade.setExists(true);
    }

    private void givenCustomerDoesNotExist() {
        facade.setExists(false);
    }

}
