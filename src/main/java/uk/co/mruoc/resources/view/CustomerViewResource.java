package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomerView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/customers/")
public class CustomerViewResource{

    private final CustomerFacade customerFacade;

    public CustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    @Path("{accountNumber}")
    public CustomerView getCustomer(@Context UriInfo uriInfo, @Session HttpSession session, @PathParam("accountNumber") String accountNumber) {
        return new CustomerView(session, uriInfo, customerFacade.read(accountNumber));
    }

}
