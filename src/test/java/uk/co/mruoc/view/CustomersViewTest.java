package uk.co.mruoc.view;

import org.junit.Before;
import org.junit.Test;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CustomersViewTest {

    private static final String CONTEXT_PATH = "/web-template";
    private static final String BASE_URI = "http://localhost:8090" + CONTEXT_PATH;

    private final TestCustomerBuilder customerBuilder = new TestCustomerBuilder();
    private final List<Customer> customers = customerBuilder.buildCustomerList();
    private final UriInfo uriInfo = mock(UriInfo.class);

    private final CustomersView view = new CustomersView(uriInfo, customers);

    @Before
    public void setUp() throws URISyntaxException {
        URI baseUri = new URI(BASE_URI);
        given(uriInfo.getBaseUri()).willReturn(baseUri);
    }

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
        assertThat(view.getContextPath()).isEqualTo(CONTEXT_PATH);
    }

}
