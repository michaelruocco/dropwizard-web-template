package uk.co.mruoc.view;

import uk.co.mruoc.api.Customer;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class CustomerView extends LoginableView {

    private final Customer customer;

    public CustomerView(HttpSession session, UriInfo uriInfo, Customer customer) {
        super("customer.ftl", session, uriInfo);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
