package uk.co.mruoc.view;

import io.dropwizard.views.View;
import uk.co.mruoc.api.Customer;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class CustomersView extends View {

    private UriInfo info;
    private List<Customer> customers;

    public CustomersView(UriInfo info, List<Customer> customers) {
        super("customers.ftl");
        this.info = info;
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public String getContextPath() {
        return info.getBaseUri().getPath();
    }

}
