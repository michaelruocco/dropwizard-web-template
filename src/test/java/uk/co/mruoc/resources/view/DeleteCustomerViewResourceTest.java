package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.facade.FakeAuthenticator;
import uk.co.mruoc.view.CustomersView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class DeleteCustomerViewResourceTest {

    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final HttpSession session = mock(HttpSession.class);
    private final Authenticator authenticator = new FakeAuthenticator();
    private final DeleteCustomerViewResource resource = new DeleteCustomerViewResource(authenticator, facade);
    private final UriInfo uriInfo = new FakeUriInfo();

    @Test
    public void shouldDeleteCustomer() {
        String accountNumber = "123456";
        List<Customer> customers = customerBuilder.buildCustomerList();
        given(facade.read()).willReturn(customers);

        CustomersView view = resource.deleteCustomer(uriInfo, session, accountNumber);

        verify(facade).delete(accountNumber);
        assertThat(view.getCustomers()).isEqualTo(customers);
    }

}
