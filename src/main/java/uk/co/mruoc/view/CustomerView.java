package uk.co.mruoc.view;

import io.dropwizard.views.View;
import uk.co.mruoc.api.Customer;

public class CustomerView extends View {

    private final Customer customer;

    public CustomerView(Customer customer) {
        super("customer.ftl");
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
