package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.MockUriInfoBuilder;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.core.UriInfo;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CustomersViewResourceTest {

    private final MockUriInfoBuilder uriInfoBuilder = new MockUriInfoBuilder();
    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final CustomersViewResource resource = new CustomersViewResource(facade);

    @Test
    public void shouldListCustomers() {
        List<Customer> customers = customerBuilder.buildCustomerList();
        given(facade.read()).willReturn(customers);
        String contextPath = "/web-template";
        UriInfo uriInfo = uriInfoBuilder.build(contextPath);

        CustomersView view = resource.listCustomers(uriInfo);

        assertThat(view.getContextPath()).isEqualTo(contextPath);
        assertThat(view.getCustomers()).isEqualTo(customers);
    }

}
