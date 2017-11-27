package uk.co.mruoc.view;

import uk.co.mruoc.api.Customer;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class CreateCustomerView extends LoginableView {

    private final String error;

    public CreateCustomerView(HttpSession session, UriInfo uriInfo) {
        this(session, uriInfo, "");
    }

    public CreateCustomerView(HttpSession session, UriInfo uriInfo, String error) {
        super("createCustomer.ftl", session, uriInfo);
        this.error = error;
    }

    public Customer getCustomer() {
        return new Customer();
    }

    public String getError() {
        return error;
    }

}
