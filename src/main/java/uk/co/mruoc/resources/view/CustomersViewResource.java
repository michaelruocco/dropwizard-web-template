package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/customers/")
public class CustomersViewResource extends LoginableViewResource {

    private final CustomerFacade customerFacade;

    public CustomersViewResource(Authenticator authenticator, CustomerFacade customerFacade) {
        super(authenticator);
        this.customerFacade = customerFacade;
    }

    @GET
    public View listCustomers(@Context UriInfo uriInfo, @Session HttpSession session) {
        if (!isLoggedIn(session))
            return createIndexView(uriInfo, session);

        SessionUser sessionUser = createSessionUser(uriInfo, session);
        return new CustomersView(sessionUser, customerFacade.read());
    }

}
