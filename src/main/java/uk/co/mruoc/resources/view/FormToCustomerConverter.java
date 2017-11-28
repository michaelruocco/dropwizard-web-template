package uk.co.mruoc.resources.view;

import uk.co.mruoc.Customer;

import javax.ws.rs.core.MultivaluedMap;
import java.math.BigDecimal;

public class FormToCustomerConverter {

    public Customer toCustomer(MultivaluedMap<String, String> form) {
        return new Customer.CustomerBuilder()
                .setAccountNumber(form.getFirst("accountNumber"))
                .setFirstName(form.getFirst("firstName"))
                .setSurname(form.getFirst("surname"))
                .setBalance(new BigDecimal(form.getFirst("balance")))
                .build();
    }

}
