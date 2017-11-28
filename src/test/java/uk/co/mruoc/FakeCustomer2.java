package uk.co.mruoc;

import java.math.BigDecimal;

public class FakeCustomer2 extends Customer {

    public FakeCustomer2() {
        super(new CustomerBuilder()
                .setAccountNumber("222222")
                .setFirstName("Laura")
                .setSurname("Noble")
                .setBalance(BigDecimal.valueOf(123)));
    }

}
