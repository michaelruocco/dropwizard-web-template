package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.*;

@Path("/createCustomer/")
public class CreateCustomerViewResource {

    private final CustomerErrorMessageBuilder errorMessageBuilder = new CustomerErrorMessageBuilder();
    private final FormToCustomerConverter converter = new FormToCustomerConverter();
    private final CustomerFacade customerFacade;

    public CreateCustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public CreateCustomerView showCreateCustomer() {
        return new CreateCustomerView();
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public View createCustomer(MultivaluedMap<String, String> form, @Context UriInfo info) {
        Customer customer = converter.toCustomer(form);
        if (customerFacade.exists(customer.getAccountNumber()))
            return new CreateCustomerView(errorMessageBuilder.buildAlreadyExists(customer));

        customerFacade.create(customer);
        return new CustomersView(info, customerFacade.read());
    }

}
