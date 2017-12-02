package uk.co.mruoc.view;

import uk.co.mruoc.Customer;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class UpdateCustomerView extends AuthenticatedView {

    private final Customer customer;

    public UpdateCustomerView(HttpSession session, UriInfo uriInfo, Customer customer) {
        super("updateCustomer.ftl", session, uriInfo);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
