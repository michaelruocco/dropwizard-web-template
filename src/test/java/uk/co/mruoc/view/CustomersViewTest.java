package uk.co.mruoc.view;

import org.junit.Test;
import uk.co.mruoc.MockUriInfoBuilder;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;

import javax.ws.rs.core.UriInfo;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CustomersViewTest {

    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final MockUriInfoBuilder uriInfoBuilder = new MockUriInfoBuilder();
    private final List<Customer> customers = customerBuilder.buildCustomerList();
    private final UriInfo uriInfo = uriInfoBuilder.build();

    private final CustomersView view = new CustomersView(uriInfo, customers);

    @Test
    public void shouldReturnCustomerViewTemplate() {
        assertThat(view.getTemplateName()).isEqualTo("/uk/co/mruoc/view/customers.ftl");
    }

    @Test
    public void shouldReturnCustomers() {
        assertThat(view.getCustomers()).isEqualTo(customers);
    }

    @Test
    public void shouldReturnContextPath() {
        assertThat(view.getContextPath()).isEqualTo(uriInfoBuilder.getContextPath());
    }

}
