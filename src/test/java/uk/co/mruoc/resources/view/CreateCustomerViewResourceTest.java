package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.*;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.facade.FakeAuthenticator;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CreateCustomerViewResourceTest {

    private final CustomerToFormConverter formConverter = new CustomerToFormConverter();
    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final HttpSession session = mock(HttpSession.class);
    private final Authenticator authenticator = new FakeAuthenticator();
    private final CreateCustomerViewResource resource = new CreateCustomerViewResource(authenticator, facade);
    private final UriInfo uriInfo = new FakeUriInfo();

    @Test
    public void shouldShowCreateCustomerView() {
        assertThat(resource.showCreateCustomer(uriInfo, session)).isNotNull();
    }

    @Test
    public void shouldCreateCustomer() {
        Customer customer = customerBuilder.buildCustomer1();
        MultivaluedMap<String, String> form = formConverter.toForm(customer);
        List<Customer> customers = Collections.singletonList(customer);
        given(facade.read()).willReturn(customers);

        CustomersView view = (CustomersView) resource.createCustomer(uriInfo, session, form);

        verify(facade).create(any(Customer.class));
        assertThat(view.getCustomers()).isEqualTo(customers);
    }

    @Test
    public void shouldShowErrorIfCreateCustomerFails() {
        Customer customer = customerBuilder.buildCustomer1();
        MultivaluedMap<String, String> form = formConverter.toForm(customer);
        given(facade.exists(customer.getAccountNumber())).willReturn(true);

        CreateCustomerView view = (CreateCustomerView) resource.createCustomer(uriInfo, session, form);

        assertThat(view.getError()).isEqualTo("customer 111111 already exists");
    }

}
