package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.MockUriInfoBuilder;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;
import uk.co.mruoc.view.UpdateCustomerView;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UpdateCustomerViewResourceTest {

    private final CustomerToFormConverter formConverter = new CustomerToFormConverter();
    private final MockUriInfoBuilder uriInfoBuilder = new MockUriInfoBuilder();
    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final UpdateCustomerViewResource resource = new UpdateCustomerViewResource(facade);
    private final UriInfo uriInfo = uriInfoBuilder.build();

    @Test
    public void shouldShowUpdateCustomer() {
        String accountNumber = "123456";
        Customer customer = customerBuilder.buildCustomer1();
        given(facade.read(accountNumber)).willReturn(customer);

        UpdateCustomerView view = resource.showUpdateCustomer(accountNumber);

        assertThat(view.getCustomer()).isEqualTo(customer);
    }

    @Test
    public void shouldUpdateCustomer() {
        List<Customer> customers = customerBuilder.buildCustomerList();
        Customer customer = customers.get(0);
        MultivaluedMap<String, String> form = formConverter.toForm(customer);
        given(facade.exists(customer.getAccountNumber())).willReturn(true);
        given(facade.read()).willReturn(customers);

        CustomersView view = (CustomersView) resource.updateCustomer(form, uriInfo);

        verify(facade).update(any(Customer.class));
        assertThat(view.getCustomers()).isEqualTo(customers);
        assertThat(view.getContextPath()).isEqualTo(uriInfoBuilder.getContextPath());
    }

    @Test
    public void shouldShowErrorIfUpdateCustomerFails() {
        Customer customer = customerBuilder.buildCustomer1();
        MultivaluedMap<String, String> form = formConverter.toForm(customer);
        given(facade.exists(customer.getAccountNumber())).willReturn(false);

        UpdateCustomerView view = (UpdateCustomerView) resource.updateCustomer(form, uriInfo);

        assertThat(view.getError()).isEqualTo("customer 111111 not found");
    }

}
