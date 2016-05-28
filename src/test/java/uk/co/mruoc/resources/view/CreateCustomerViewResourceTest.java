package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import org.junit.Test;
import uk.co.mruoc.*;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CreateCustomerViewResourceTest {

    private static final String CONTEXT_PATH = "/web-template";

    private final MockUriInfoBuilder uriInfoBuilder = new MockUriInfoBuilder();
    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final CustomerFacade facade = mock(CustomerFacade.class);
    private final CreateCustomerViewResource resource = new CreateCustomerViewResource(facade);
    private final UriInfo uriInfo = uriInfoBuilder.build(CONTEXT_PATH);

    @Test
    public void shouldShowCreateCustomerView() {
        assertThat(resource.showCreateCustomer()).isNotNull();
    }

    @Test
    public void shouldCreateCustomer() {
        Customer customer = customerBuilder.buildCustomer1();
        MultivaluedMap<String, String> form = toForm(customer);
        given(facade.read(customer.getAccountNumber())).willReturn(customer);

        View view = resource.createCustomer(form, uriInfo);

        assertThat(view instanceof CustomersView).isTrue();
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
