package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import org.junit.Test;
import uk.co.mruoc.*;
import uk.co.mruoc.Customer;
import uk.co.mruoc.auth.FakeUserInfo;
import uk.co.mruoc.facade.FakeCustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;
import uk.co.mruoc.view.IndexView;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreateCustomerViewResourceTest {

    private final FakeCustomerFacade facade = new FakeCustomerFacade();
    private final FakeHttpSession session = new FakeHttpSession();
    private final UriInfo uriInfo = new FakeUriInfo();

    private final CreateCustomerViewResource resource = new CreateCustomerViewResource(facade);

    @Test
    public void showCreateCustomerShouldReturnIndexViewIfNotLoggedIn() {
        View view = resource.showCreateCustomer(uriInfo, session);

        assertThat(view).isInstanceOf(IndexView.class);
    }

    @Test
    public void showCreateCustomerShouldReturnCreateCustomerViewIfLoggedIn() {
        givenUserIsLoggedIn();

        View view = resource.showCreateCustomer(uriInfo, session);

        assertThat(view).isInstanceOf(CreateCustomerView.class);
    }

    @Test
    public void shouldNotCreateCustomerIfNotLoggedIn() {
        resource.createCustomer(uriInfo, session, new MultivaluedHashMap<>());

        assertThat(facade.createCustomerCalled()).isFalse();
    }

    @Test
    public void createCustomerShouldReturnIndexViewIfNotLoggedIn() {
        View view = resource.createCustomer(uriInfo, session, new MultivaluedHashMap<>());

        assertThat(view).isInstanceOf(IndexView.class);
    }

    @Test
    public void shouldNotCreateCustomerIfLoggedInAndCustomerExists() {
        givenUserIsLoggedIn();
        givenCustomerExists();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(new FakeCustomer1());

        resource.createCustomer(uriInfo, session, form);

        assertThat(facade.createCustomerCalled()).isFalse();
    }

    @Test
    public void shouldReturnCreateCustomerViewWithErrorMessageIfLoggedInAndCustomerExists() {
        givenUserIsLoggedIn();
        givenCustomerExists();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(new FakeCustomer1());

        CreateCustomerView view = (CreateCustomerView) resource.createCustomer(uriInfo, session, form);

        assertThat(view.getError()).isEqualTo("customer 111111 already exists");
    }

    @Test
    public void shouldCreateCustomerIfLoggedInAndCustomerDoesNotExist() {
        givenUserIsLoggedIn();
        givenCustomerDoesNotExist();
        Customer customer = new FakeCustomer1();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(customer);

        resource.createCustomer(uriInfo, session, form);

        assertThat(facade.getLastCreatedCustomer()).isEqualToComparingFieldByField(customer);
    }

    @Test
    public void shouldReturnCustomersViewIfLoggedInAndCustomerDoesNotExist() {
        givenUserIsLoggedIn();
        givenCustomerDoesNotExist();
        Customer customer = new FakeCustomer1();
        MultivaluedMap<String, String> form = CustomerToFormConverter.toForm(customer);

        View view = resource.createCustomer(uriInfo, session, form);

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
