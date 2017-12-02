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

public class DeleteCustomerViewResourceTest {

    private final FakeCustomerFacade facade = new FakeCustomerFacade();
    private final FakeHttpSession session = new FakeHttpSession();
    private final UriInfo uriInfo = new FakeUriInfo();

    private final DeleteCustomerViewResource resource = new DeleteCustomerViewResource(facade);

    @Test
    public void shouldDeleteCustomer() {
        String accountNumber = "123456";

        resource.deleteCustomer(uriInfo, session, accountNumber);

        assertThat(facade.getLastDeletedAccountNumber()).isEqualTo(accountNumber);
    }

    @Test
    public void shouldReturnCustomersViewWithRemainingCustomers() {
        String accountNumber = "123456";
        List<Customer> customers = Arrays.asList(new FakeCustomer1(), new FakeCustomer2());
        facade.setCustomersToRead(customers);

        CustomersView view = (CustomersView) resource.deleteCustomer(uriInfo, session, accountNumber);

        assertThat(view.getCustomers()).isEqualTo(customers);
    }

}
