package uk.co.mruoc.resources.rest;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("ws/v1/customers/")
@Api(value = "ws/v1/customers/", description = "Customer Maintenance")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerFacade facade;

    public CustomerResource(CustomerFacade facade) {
        this.facade = facade;
    }

    @GET
    @Path("{accountNumber}")
    @ApiOperation(value = "Get customer")
    @Timed
    public Response getCustomer(@PathParam("accountNumber") String accountNumber) {
        Customer customer = facade.read(accountNumber);
        return Response.ok()
                .entity(customer)
                .build();
    }

    @GET
    @ApiOperation(value = "Get all customers")
    @Timed
    public Response getCustomers() {
        List<Customer> customers = facade.read();
        return Response.ok()
                .entity(customers)
                .header("X-Total-Count", customers.size())
                .build();
    }

    @POST
    @ApiOperation(value = "Create customer")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(@ApiParam Customer customer, @Context UriInfo info) {
        facade.create(customer);
        Customer newCustomer = facade.read(customer.getAccountNumber());
        URI uri = info.getBaseUriBuilder().path("ws/v1/customers/" + newCustomer.getAccountNumber()).build();
        return Response.created(uri)
                .entity(newCustomer)
                .build();
    }

    @PUT
    @ApiOperation(value = "Update customer")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@ApiParam Customer customer) {
        facade.update(customer);
        Customer updatedCustomer = facade.read(customer.getAccountNumber());
        return Response.ok()
                .entity(updatedCustomer)
                .build();
    }

    @DELETE
    @Path("{accountNumber}")
    @ApiOperation(value = "Delete customer")
    @Timed
    public Response deleteCustomer(@PathParam("accountNumber") String accountNumber) {
        facade.delete(accountNumber);
        return Response.noContent().build();
    }

}
