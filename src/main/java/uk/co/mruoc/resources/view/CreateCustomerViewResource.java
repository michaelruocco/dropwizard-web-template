package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.*;

@Path("/createCustomer/")
public class CreateCustomerViewResource extends LoginableViewResource {

    private final CustomerErrorMessageBuilder errorMessageBuilder = new CustomerErrorMessageBuilder();
    private final FormToCustomerConverter converter = new FormToCustomerConverter();
    private final CustomerFacade customerFacade;

    public CreateCustomerViewResource(Authenticator authenticator, CustomerFacade customerFacade) {
        super(authenticator);
        this.customerFacade = customerFacade;
    }

    @GET
    public CreateCustomerView showCreateCustomer(@Context UriInfo uriInfo, @Session HttpSession session) {
        return new CreateCustomerView(createSessionUser(uriInfo, session));
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public View createCustomer(@Context UriInfo uriInfo, @Session HttpSession session, MultivaluedMap<String, String> form) {
        Customer customer = converter.toCustomer(form);
        SessionUser sessionUser = createSessionUser(uriInfo, session);

        if (customerFacade.exists(customer.getAccountNumber()))
            return new CreateCustomerView(sessionUser, errorMessageBuilder.buildAlreadyExists(customer));

        customerFacade.create(customer);

        return new CustomersView(sessionUser, customerFacade.read());
    }

}
