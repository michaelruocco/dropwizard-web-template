package uk.co.mruoc.view;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.resources.view.SessionUser;

public class UpdateCustomerView extends DefaultLoginableView {

    private final Customer customer;
    private final String error;

    public UpdateCustomerView(SessionUser sessionUser, Customer customer) {
        this(sessionUser, customer, "");
    }

    public UpdateCustomerView(SessionUser sessionUser, Customer customer, String error) {
        super("updateCustomer.ftl", sessionUser);
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
