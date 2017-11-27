package uk.co.mruoc.view;

import org.junit.Test;
import uk.co.mruoc.FakeUriInfo;
import uk.co.mruoc.api.Customer;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CreateCustomerViewTest {

    private final HttpSession session = mock(HttpSession.class);
    private final UriInfo uriInfo = new FakeUriInfo();

    @Test
    public void shouldReturnCustomerViewTemplate() {
        CreateCustomerView view = new CreateCustomerView(session, uriInfo);
        assertThat(view.getTemplateName()).isEqualTo("/uk/co/mruoc/view/createCustomer.ftl");
    }

    @Test
    public void errorShouldDefaultToEmptyString() {
        CreateCustomerView view = new CreateCustomerView(session, uriInfo);
        assertThat(view.getError()).isEmpty();
    }

    @Test
    public void shouldReturnError() {
        String error = "an error occurred";
        CreateCustomerView view = new CreateCustomerView(session, uriInfo, error);
        assertThat(view.getError()).isEqualTo(error);
    }

    @Test
    public void shouldReturnEmptyCustomer() {
        CreateCustomerView view = new CreateCustomerView(session, uriInfo);
        Customer customer = view.getCustomer();

        assertThat(customer.getAccountNumber()).isNull();
        assertThat(customer.getFirstName()).isNull();
        assertThat(customer.getSurname()).isNull();
        assertThat(customer.getBalance()).isNull();
    }

}
