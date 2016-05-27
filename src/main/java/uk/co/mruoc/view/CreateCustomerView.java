package uk.co.mruoc.view;

import io.dropwizard.views.View;
import uk.co.mruoc.api.Customer;

import javax.ws.rs.core.UriInfo;
import java.math.BigDecimal;
import java.util.List;

public class CreateCustomerView extends View {

    private final String error;

    public CreateCustomerView() {
        this("");
    }

    public CreateCustomerView(String error) {
        super("createCustomer.ftl");
        this.error = error;
    }

    public Customer getCustomer() {
        return new Customer();
    }

    public String getError() {
        return error;
    }

}
