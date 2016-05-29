package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/deleteCustomer/")
public class DeleteCustomerViewResource {

    private final CustomerFacade customerFacade;

    public DeleteCustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public CustomersView deleteCustomer(@QueryParam("accountNumber") String accountNumber, @Context UriInfo info) {
        customerFacade.delete(accountNumber);
        return new CustomersView(info, customerFacade.read());
    }

}
