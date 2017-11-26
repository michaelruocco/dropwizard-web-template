package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;
import uk.co.mruoc.view.UpdateCustomerView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

@Path("/updateCustomer/")
public class UpdateCustomerViewResource extends LoginableViewResource {

    private final CustomerErrorMessageBuilder errorMessageBuilder = new CustomerErrorMessageBuilder();
    private final FormToCustomerConverter converter = new FormToCustomerConverter();
    private final CustomerFacade customerFacade;

    public UpdateCustomerViewResource(Authenticator authenticator, CustomerFacade customerFacade) {
        super(authenticator);
        this.customerFacade = customerFacade;
    }

    @GET
    public UpdateCustomerView showUpdateCustomer(@Context UriInfo uriInfo, @Session HttpSession session, @QueryParam("accountNumber") String accountNumber) {
        Customer customer = customerFacade.read(accountNumber);
        SessionUser sessionUser = createSessionUser(uriInfo, session);
        return new UpdateCustomerView(sessionUser, customer);
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public View updateCustomer(@Context UriInfo uriInfo, @Session HttpSession session, MultivaluedMap<String, String> form) {
        Customer customer = converter.toCustomer(form);
        SessionUser sessionUser = createSessionUser(uriInfo, session);
        if (!customerFacade.exists(customer.getAccountNumber()))
            return new UpdateCustomerView(sessionUser, customer, errorMessageBuilder.buildNotFound(customer));

        customerFacade.update(customer);
        return new CustomersView(sessionUser, customerFacade.read());
    }

}
