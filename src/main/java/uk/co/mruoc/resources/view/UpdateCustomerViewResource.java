package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;
import uk.co.mruoc.view.UpdateCustomerErrorView;
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

    public UpdateCustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public View showUpdateCustomer(@Context UriInfo uriInfo, @Session HttpSession session, @QueryParam("accountNumber") String accountNumber) {
        if (!isLoggedIn(session))
            return buildIndexView(uriInfo, session);

        Customer customer = customerFacade.read(accountNumber);
        return new UpdateCustomerView(session, uriInfo, customer);
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public View updateCustomer(@Context UriInfo uriInfo, @Session HttpSession session, MultivaluedMap<String, String> form) {
        if (!isLoggedIn(session))
            return buildIndexView(uriInfo, session);

        return handleUpdateCustomer(uriInfo, session, form);
    }

    private View handleUpdateCustomer(UriInfo uriInfo, HttpSession session, MultivaluedMap<String, String> form) {
        Customer customer = converter.toCustomer(form);
        if (!customerFacade.exists(customer.getAccountNumber()))
            return new UpdateCustomerErrorView(session, uriInfo, errorMessageBuilder.buildNotFound(customer));

        customerFacade.update(customer);
        return new CustomersView(session, uriInfo, customerFacade.read());
    }

}
