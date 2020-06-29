package uk.co.mruoc;

import java.math.BigDecimal;

public class FakeCustomer1 extends Customer {

    public FakeCustomer1() {
        super(new CustomerBuilder()
                .setAccountNumber("111111")
                .setFirstName("Michael")
                .setSurname("Ruocco")
                .setBalance(BigDecimal.valueOf(999)));
    }

}
