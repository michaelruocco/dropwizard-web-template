package uk.co.mruoc.resources;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.jdbi.CustomerDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

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
    @ApiOperation(value = "Get single customer")
    @Timed
    public Response getCustomer(@PathParam("accountNumber") String accountNumber) {
        Customer customer = customerDao.getCustomer(accountNumber);
        return Response.ok().entity(customer).build();
    }

    @POST
    @ApiOperation(value = "Post single customer")
    @Timed
    public Response createCustomer(@ApiParam Customer customer, @Context UriInfo info) {
        customerDao.insert(customer);
        Customer newCustomer = customerDao.getCustomer(customer.getAccountNumber());
        URI uri = info.getBaseUriBuilder().path("ws/v1/customers" + newCustomer.getAccountNumber()).build();
        return Response.created(uri).entity(newCustomer).build();
    }

}
