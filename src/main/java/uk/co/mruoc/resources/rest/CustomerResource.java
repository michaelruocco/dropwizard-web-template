package uk.co.mruoc.resources.rest;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.Customer;
import uk.co.mruoc.ErrorMessage;
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

    private final CustomerErrorMessageBuilder errorMessageBuilder = new CustomerErrorMessageBuilder();
    private final CustomerFacade facade;

    public CustomerResource(CustomerFacade facade) {
        this.facade = facade;
    }

    @GET
    @Path("{accountNumber}")
    @ApiOperation(value = "Get customer")
    @Timed
    public Response getCustomer(@PathParam("accountNumber") String accountNumber) {
        if (!facade.exists(accountNumber))
            return buildNotFoundResponse(accountNumber);

        Customer customer = facade.read(accountNumber);
        return Response.ok().entity(customer).build();
    }

    @GET
    @ApiOperation(value = "Get all customers")
    @Timed
    public Response getCustomers() {
        List<Customer> customers = facade.read();
        return Response.ok().entity(customers).header("X-Total-Count", customers.size()).build();
    }

    @POST
    @ApiOperation(value = "Create customer")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(@ApiParam Customer customer, @Context UriInfo info) {
        if (facade.exists(customer.getAccountNumber()))
            return Response.status(409).entity(toError(errorMessageBuilder.buildAlreadyExists(customer))).build();

        facade.create(customer);
        Customer newCustomer = facade.read(customer.getAccountNumber());
        URI uri = getNewCustomerUri(newCustomer, info);
        return Response.created(uri).entity(newCustomer).build();
    }

    @PUT
    @ApiOperation(value = "Update customer")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@ApiParam Customer customer) {
        String accountNumber = customer.getAccountNumber();
        if (!facade.exists(accountNumber))
            return buildNotFoundResponse(accountNumber);

        facade.update(customer);
        Customer updatedCustomer = facade.read(accountNumber);
        return Response.ok().entity(updatedCustomer).build();
    }

    @DELETE
    @Path("{accountNumber}")
    @ApiOperation(value = "Delete customer")
    @Timed
    public Response deleteCustomer(@PathParam("accountNumber") String accountNumber) {
        facade.delete(accountNumber);
        return Response.noContent().build();
    }

    private URI getNewCustomerUri(Customer customer, UriInfo info) {
        return info.getBaseUriBuilder()
                .path("ws/v1/customers/")
                .path(customer.getAccountNumber())
                .build();
    }

    private ErrorMessage toError(String message) {
        return new ErrorMessage(message);
    }

    private Response buildNotFoundResponse(String accountNumber) {
        ErrorMessage error = toError(errorMessageBuilder.buildNotFound(accountNumber));
        return Response.status(404).entity(error).build();
    }

}
