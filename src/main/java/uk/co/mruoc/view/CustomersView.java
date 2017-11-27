package uk.co.mruoc.view;

import uk.co.mruoc.api.Customer;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public class CustomersView extends LoginableView {

    private List<Customer> customers;

    public CustomersView(HttpSession session, UriInfo uriInfo, List<Customer> customers) {
        super("customers.ftl", session, uriInfo);
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}
