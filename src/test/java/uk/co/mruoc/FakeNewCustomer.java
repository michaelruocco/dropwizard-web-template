package uk.co.mruoc;

import java.math.BigDecimal;

public class FakeNewCustomer extends Customer {

    public FakeNewCustomer() {
        super(new CustomerBuilder()
                .setAccountNumber("333333")
                .setFirstName("New")
                .setSurname("Customer")
                .setBalance(BigDecimal.valueOf(212)));
    }

}
