package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomerView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CustomerViewResourceTest {

    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final CustomerViewResource resource = new CustomerViewResource(facade);
    private final UriInfo uriInfo = new FakeUriInfo();
    private final HttpSession session = mock(HttpSession.class);

    @Test
    public void shouldGetCustomer() {
        Customer customer = customerBuilder.buildCustomer1();
        given(facade.read(customer.getAccountNumber())).willReturn(customer);

        CustomerView view = resource.getCustomer(uriInfo, session, customer.getAccountNumber());

        assertThat(view.getCustomer()).isEqualTo(customer);
    }

}
