package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriInfo;

@Path("/customers/")
public class CustomersViewResource {

    private final CustomerFacade customerFacade;

    public CustomersViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public View listCustomers(UriInfo uriInfo, @Session HttpSession session) {
        return new CustomersView(session, uriInfo, customerFacade.read());
    }

}
