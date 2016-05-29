package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.MockUriInfoBuilder;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DeleteCustomerViewResourceTest {

    private final MockUriInfoBuilder uriInfoBuilder = new MockUriInfoBuilder();
    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final DeleteCustomerViewResource resource = new DeleteCustomerViewResource(facade);
    private final UriInfo uriInfo = uriInfoBuilder.build();

    @Test
    public void shouldDeleteCustomer() {
        String accountNumber = "123456";
        List<Customer> customers = customerBuilder.buildCustomerList();
        given(facade.read()).willReturn(customers);

        CustomersView view = resource.deleteCustomer(accountNumber, uriInfo);

        verify(facade).delete(accountNumber);
        assertThat(view.getCustomers()).isEqualTo(customers);
        assertThat(view.getContextPath()).isEqualTo(uriInfoBuilder.getContextPath());
    }

}
