package uk.co.mruoc.view;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.resources.view.SessionUser;

public class CreateCustomerView extends DefaultLoginableView {

    private final String error;

    public CreateCustomerView(SessionUser sessionUser) {
        this(sessionUser,"");
    }

    public CreateCustomerView(SessionUser sessionUser, String error) {
        super("createCustomer.ftl", sessionUser);
        this.error = error;
    }

    public Customer getCustomer() {
        return new Customer();
    }

    public String getError() {
        return error;
    }

}
