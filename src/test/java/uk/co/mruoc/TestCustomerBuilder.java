package uk.co.mruoc;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.api.Customer.CustomerBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TestCustomerBuilder {

    public Customer buildCustomer1() {
        return new CustomerBuilder()
                .setAccountNumber("111111")
                .setFirstName("Michael")
                .setSurname("Ruocco")
                .setBalance(BigDecimal.valueOf(999))
                .build();
    }

    public Customer buildUpdateCustomer1() {
        return new CustomerBuilder()
                .setAccountNumber("111111")
                .setFirstName("Updated")
                .setSurname("Updated")
                .setBalance(BigDecimal.valueOf(777))
                .build();
    }

    public Customer buildNewCustomer() {
        return new CustomerBuilder()
                .setAccountNumber("333333")
                .setFirstName("New")
                .setSurname("Customer")
                .setBalance(BigDecimal.valueOf(212))
                .build();
    }

    private Customer buildCustomer2() {
        return new CustomerBuilder()
                .setAccountNumber("222222")
                .setFirstName("Laura")
                .setSurname("Noble")
                .setBalance(BigDecimal.valueOf(123))
                .build();
    }

    public List<Customer> buildCustomerList() {
        return Arrays.asList(buildCustomer1(), buildCustomer2());
    }

}
