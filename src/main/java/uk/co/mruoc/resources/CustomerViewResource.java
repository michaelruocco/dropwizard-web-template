package uk.co.mruoc.resources;

import uk.co.mruoc.jdbi.CustomerDao;
import uk.co.mruoc.view.CustomerView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/customers/")
public class CustomerViewResource {

    private final CustomerDao customerDao;

    public CustomerViewResource(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GET
    @Path("{accountNumber}")
    public CustomerView getPerson(@PathParam("accountNumber") String accountNumber) {
        return new CustomerView(customerDao.getCustomer(accountNumber));
    }

}
