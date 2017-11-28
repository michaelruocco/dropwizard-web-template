package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/deleteCustomer/")
public class DeleteCustomerViewResource extends LoginableViewResource {

    private final CustomerFacade customerFacade;

    public DeleteCustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public View deleteCustomer(@Context UriInfo uriInfo, @Session HttpSession session, @QueryParam("accountNumber") String accountNumber) {
        if (!isLoggedIn(session))
            return buildIndexView(uriInfo, session);

        customerFacade.delete(accountNumber);
        return new CustomersView(session, uriInfo, customerFacade.read());
    }

}
