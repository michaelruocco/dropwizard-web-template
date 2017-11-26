package uk.co.mruoc.view;

import org.junit.Test;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.resources.view.SessionUser;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CreateCustomerViewTest {

    private final SessionUser sessionUser = new SessionUser("", mock(HttpSession.class));

    @Test
    public void shouldReturnCustomerViewTemplate() {
        CreateCustomerView view = new CreateCustomerView(sessionUser);
        assertThat(view.getTemplateName()).isEqualTo("/uk/co/mruoc/view/createCustomer.ftl");
    }

    @Test
    public void errorShouldDefaultToEmptyString() {
        CreateCustomerView view = new CreateCustomerView(sessionUser);
        assertThat(view.getError()).isEmpty();
    }

    @Test
    public void shouldReturnError() {
        String error = "an error occurred";
        CreateCustomerView view = new CreateCustomerView(sessionUser, error);
        assertThat(view.getError()).isEqualTo(error);
    }

    @Test
    public void shouldReturnEmptyCustomer() {
        CreateCustomerView view = new CreateCustomerView(sessionUser);
        Customer customer = view.getCustomer();

        assertThat(customer.getAccountNumber()).isNull();
        assertThat(customer.getFirstName()).isNull();
        assertThat(customer.getSurname()).isNull();
        assertThat(customer.getBalance()).isNull();
    }

}
