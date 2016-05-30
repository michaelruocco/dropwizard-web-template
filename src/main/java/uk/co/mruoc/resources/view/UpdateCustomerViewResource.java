package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;
import uk.co.mruoc.view.UpdateCustomerView;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

@Path("/updateCustomer/")
public class UpdateCustomerViewResource {

    private final CustomerErrorMessageBuilder errorMessageBuilder = new CustomerErrorMessageBuilder();
    private final FormToCustomerConverter converter = new FormToCustomerConverter();
    private final CustomerFacade customerFacade;

    public UpdateCustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public UpdateCustomerView showUpdateCustomer(@QueryParam("accountNumber") String accountNumber) {
        Customer customer = customerFacade.read(accountNumber);
        return new UpdateCustomerView(customer);
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public View updateCustomer(MultivaluedMap<String, String> form, @Context UriInfo info) {
        Customer customer = converter.toCustomer(form);
        if (!customerFacade.exists(customer.getAccountNumber()))
            return new UpdateCustomerView(customer, errorMessageBuilder.buildNotFound(customer));

        customerFacade.update(customer);
        return new CustomersView(info, customerFacade.read());
    }

}
