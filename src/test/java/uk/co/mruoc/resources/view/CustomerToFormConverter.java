package uk.co.mruoc.resources.view;

import uk.co.mruoc.api.Customer;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import static java.util.Collections.singletonList;

public class CustomerToFormConverter {

    public MultivaluedMap<String, String> toForm(Customer customer) {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.put("accountNumber", singletonList(customer.getAccountNumber()));
        form.put("firstName", singletonList(customer.getFirstName()));
        form.put("surname", singletonList(customer.getSurname()));
        form.put("balance", singletonList(customer.getBalance().toString()));
        return form;
    }

}
