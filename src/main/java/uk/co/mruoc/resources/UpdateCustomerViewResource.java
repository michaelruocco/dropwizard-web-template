package uk.co.mruoc.resources;

import io.dropwizard.views.View;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;
import uk.co.mruoc.view.UpdateCustomerView;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.math.BigDecimal;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static uk.co.mruoc.api.Customer.CustomerBuilder;

@Path("/updateCustomer/")
public class UpdateCustomerViewResource {

    private final FormToCustomerConverter converter = new FormToCustomerConverter();
    private final CustomerFacade customerFacade;

    public UpdateCustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public View showUpdateCustomer(@QueryParam("accountNumber") String accountNumber) {
        Customer customer = customerFacade.read(accountNumber);
        System.out.println("updating customer " + customer.getFullName());
        return new UpdateCustomerView(customer);
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public View updateCustomer(MultivaluedMap<String, String> form, @Context UriInfo info) {
        try {
            Customer customer = converter.toCustomer(form);
            customerFacade.update(customer);
            return new CustomersView(info, customerFacade.read());
        } catch (Exception e) {
            return new CreateCustomerView(e.getMessage());
        }
    }

}
