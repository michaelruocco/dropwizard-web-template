package uk.co.mruoc.view;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.resources.view.SessionUser;

import java.util.List;

public class CustomersView extends DefaultLoginableView {

    private List<Customer> customers;

    public CustomersView(SessionUser sessionUser, List<Customer> customers) {
        super("customers.ftl", sessionUser);
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}
