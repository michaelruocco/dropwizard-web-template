package uk.co.mruoc.view;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class UpdateCustomerErrorView extends LoginableView {

    private final String error;

    public UpdateCustomerErrorView(HttpSession session, UriInfo uriInfo, String error) {
        super("updateCustomer.ftl", session, uriInfo);
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
