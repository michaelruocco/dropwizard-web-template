package uk.co.mruoc.resources;

import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/customers/")
public class CustomersViewResource {

    private final CustomerFacade customerFacade;

    public CustomersViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public CustomersView listCustomers(@Context UriInfo info) {
        return new CustomersView(info, customerFacade.read());
    }

}
