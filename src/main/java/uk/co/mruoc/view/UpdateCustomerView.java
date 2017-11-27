package uk.co.mruoc.view;

import uk.co.mruoc.api.Customer;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class UpdateCustomerView extends LoginableView {

    private final Customer customer;
    private final String error;

    public UpdateCustomerView(HttpSession session, UriInfo uriInfo, Customer customer) {
        this(session, uriInfo, customer, "");
    }

    public UpdateCustomerView(HttpSession session, UriInfo uriInfo, Customer customer, String error) {
        super("updateCustomer.ftl", session, uriInfo);
        this.customer = customer;
        this.error = error;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getError() {
        return error;
    }

}
