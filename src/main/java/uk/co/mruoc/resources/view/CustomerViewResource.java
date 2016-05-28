package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomerView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/customers/")
public class CustomerViewResource {

    private final CustomerFacade customerFacade;

    public CustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    @Path("{accountNumber}")
    public CustomerView getCustomer(@PathParam("accountNumber") String accountNumber) {
        return new CustomerView(customerFacade.read(accountNumber));
    }

}
