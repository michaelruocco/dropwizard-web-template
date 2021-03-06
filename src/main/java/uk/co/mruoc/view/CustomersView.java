package uk.co.mruoc.view;

import uk.co.mruoc.Customer;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public class CustomersView extends AuthenticatedView {

    private List<Customer> customers;

    public CustomersView(HttpSession session, UriInfo uriInfo, List<Customer> customers) {
        super("customers.ftl", session, uriInfo);
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}
