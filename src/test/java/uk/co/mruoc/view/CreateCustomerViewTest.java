package uk.co.mruoc.view;

import org.junit.Test;
import uk.co.mruoc.api.Customer;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreateCustomerViewTest {

    @Test
    public void shouldReturnCustomerViewTemplate() {
        CreateCustomerView view = new CreateCustomerView();
        assertThat(view.getTemplateName()).isEqualTo("/uk/co/mruoc/view/createCustomer.ftl");
    }

    @Test
    public void errorShouldDefaultToEmptyString() {
        CreateCustomerView view = new CreateCustomerView();
        assertThat(view.getError()).isEmpty();
    }

    @Test
    public void shouldReturnError() {
        String error = "an error occurred";
        CreateCustomerView view = new CreateCustomerView(error);
        assertThat(view.getError()).isEqualTo(error);
    }

    @Test
    public void shouldReturnEmptyCustomer() {
        CreateCustomerView view = new CreateCustomerView();
        Customer customer = view.getCustomer();

        assertThat(customer.getAccountNumber()).isNull();
        assertThat(customer.getFirstName()).isNull();
        assertThat(customer.getSurname()).isNull();
        assertThat(customer.getBalance()).isNull();
    }

}
