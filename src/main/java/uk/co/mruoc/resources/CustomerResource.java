package uk.co.mruoc.resources;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.jdbi.CustomerDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("ws/v1/customers")
@Api(value = "ws/v1/customers", description = "Customer Maintenance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerDao customerDao;

    public CustomerResource(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GET
    @Path("/{accountNumber}")
    @ApiOperation(value = "Get single customer", response = Customer.class)
    @Timed
    public Customer getCustomer(@PathParam("accountNumber") String accountNumber) {
        return customerDao.getCustomer(accountNumber);
    }

    @POST
    @ApiOperation(value = "Post customer", response = Customer.class)
    @Timed
    public Customer createCustomer(Customer customer) {
        customerDao.insert(customer);
        return customerDao.getCustomer(customer.getAccountNumber());
    }

}
