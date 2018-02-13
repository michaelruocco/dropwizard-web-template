package uk.co.mruoc.resources.view;

import org.junit.Test;
import uk.co.mruoc.*;
import uk.co.mruoc.Customer;
import uk.co.mruoc.facade.FakeCustomerFacade;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.core.UriInfo;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CustomersViewResourceTest {

    private final FakeCustomerFacade facade = new FakeCustomerFacade();
    private final FakeHttpSession session = new FakeHttpSession();
    private final UriInfo uriInfo = new FakeUriInfo();

    private final CustomersViewResource resource = new CustomersViewResource(facade);

    @Test
    public void shouldListCustomers() {
        List<Customer> customers = Arrays.asList(new FakeCustomer1(), new FakeCustomer2());
        facade.setCustomersToRead(customers);

        CustomersView view = (CustomersView) resource.listCustomers(uriInfo, session);

        assertThat(view.getCustomers()).isEqualTo(customers);
    }

}
