package uk.co.mruoc;

import uk.co.mruoc.api.Customer;

public class CustomerErrorMessageBuilder {

    public String buildAlreadyExists(Customer customer) {
        return buildAlreadyExists(customer.getAccountNumber());
    }

    public String buildAlreadyExists(String accountNumber) {
        return String.format("customer %s already exists", accountNumber);
    }

    public String buildNotFound(Customer customer) {
        return buildNotFound(customer.getAccountNumber());
    }

    public String buildNotFound(String accountNumber) {
        return String.format("customer %s not found", accountNumber);
    }

}
