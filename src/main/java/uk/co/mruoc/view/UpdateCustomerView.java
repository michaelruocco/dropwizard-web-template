package uk.co.mruoc.view;

import io.dropwizard.views.View;
import uk.co.mruoc.api.Customer;

public class UpdateCustomerView extends View {

    private final Customer customer;
    private final String error;

    public UpdateCustomerView(Customer customer) {
        this(customer, "");
    }

    public UpdateCustomerView(Customer customer, String error) {
        super("updateCustomer.ftl");
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
