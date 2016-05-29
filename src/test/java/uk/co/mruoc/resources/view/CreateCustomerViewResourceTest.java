package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.*;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CreateCustomerViewResourceTest {

    private final MockUriInfoBuilder uriInfoBuilder = new MockUriInfoBuilder();
    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final CreateCustomerViewResource resource = new CreateCustomerViewResource(facade);
    private final UriInfo uriInfo = uriInfoBuilder.build();

    @Test
    public void shouldShowCreateCustomerView() {
        assertThat(resource.showCreateCustomer()).isNotNull();
    }

    @Test
    public void shouldCreateCustomer() {
        Customer customer = customerBuilder.buildCustomer1();
        MultivaluedMap<String, String> form = toForm(customer);
        List<Customer> customers = Collections.singletonList(customer);
        given(facade.read()).willReturn(customers);

        CustomersView view = (CustomersView) resource.createCustomer(form, uriInfo);

        verify(facade).create(any(Customer.class));
        assertThat(view.getCustomers()).isEqualTo(customers);
        assertThat(view.getContextPath()).isEqualTo(uriInfoBuilder.getContextPath());
    }

    @Test
    public void shouldShowErrorIfCreateCustomerFails() {
        Customer customer = customerBuilder.buildCustomer1();
        MultivaluedMap<String, String> form = toForm(customer);
        String error = "an error occurred";
        doThrow(new RuntimeException(error)).when(facade).create(any(Customer.class));

        CreateCustomerView view = (CreateCustomerView) resource.createCustomer(form, uriInfo);

        verify(facade).create(any(Customer.class));
        assertThat(view.getError()).isEqualTo(error);
    }

    private MultivaluedMap<String, String> toForm(Customer customer) {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.put("accountNumber", singletonList(customer.getAccountNumber()));
        form.put("firstName", singletonList(customer.getFirstName()));
        form.put("surname", singletonList(customer.getSurname()));
        form.put("balance", singletonList(customer.getBalance().toString()));
        return form;
    }

}
