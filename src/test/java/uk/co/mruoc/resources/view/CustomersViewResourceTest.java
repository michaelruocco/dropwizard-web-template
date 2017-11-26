package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import org.junit.Test;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.StubbedAuthenticator;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CustomersViewResourceTest {

    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final HttpSession session = mock(HttpSession.class);
    private final StubbedAuthenticator authenticator = new StubbedAuthenticator();
    private final CustomersViewResource resource = new CustomersViewResource(authenticator, facade);
    private final UriInfo uriInfo = new FakeUriInfo();

    @Test
    public void shouldListCustomersIfLoggedIn() {
        authenticator.setIsLoggedIn(true);
        List<Customer> customers = customerBuilder.buildCustomerList();
        given(facade.read()).willReturn(customers);

        CustomersView view = (CustomersView) resource.listCustomers(uriInfo, session);

        assertThat(view.getCustomers()).isEqualTo(customers);
    }

    @Test
    public void shouldReturnToIndexPageIfNotLoggedIn() {
        authenticator.setIsLoggedIn(false);

        View view = resource.listCustomers(uriInfo, session);

        assertThat(view).isInstanceOf(IndexView.class);
    }

}
