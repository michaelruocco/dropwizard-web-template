package uk.co.mruoc.resources;

import io.dropwizard.views.View;
import org.jboss.resteasy.annotations.Form;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.facade.CustomerFacade;
import uk.co.mruoc.view.CreateCustomerView;
import uk.co.mruoc.view.CustomersView;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import java.math.BigDecimal;

import static javax.ws.rs.core.MediaType.*;
import static uk.co.mruoc.api.Customer.*;

@Path("/createCustomer/")
public class CreateCustomerViewResource {

    private final FormConverter converter = new FormConverter();
    private final CustomerFacade customerFacade;

    public CreateCustomerViewResource(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @GET
    public CreateCustomerView showCreateCustomer() {
        return new CreateCustomerView();
    }

    @POST
    @Consumes(APPLICATION_FORM_URLENCODED)
    public View createCustomer(MultivaluedMap<String, String> form, @Context UriInfo info) {
        try {
            Customer customer = converter.toCustomer(form);
            customerFacade.create(customer);
            return new CustomersView(info, customerFacade.read());
        } catch (Exception e) {
            System.out.println("set error" + e.getMessage());
            return new CreateCustomerView(e.getMessage());
        }
    }

    private static class FormConverter {

        public Customer toCustomer(MultivaluedMap<String, String> form) {
            return new CustomerBuilder()
                    .setAccountNumber(form.getFirst("accountNumber"))
                    .setFirstName(form.getFirst("firstName"))
                    .setSurname(form.getFirst("surname"))
                    .setBalance(new BigDecimal(form.getFirst("balance")))
                    .build();
        }

    }

}
