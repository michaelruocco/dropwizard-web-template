package uk.co.mruoc;

import java.math.BigDecimal;

public class FakeUpdatedCustomer1 extends Customer {

    public FakeUpdatedCustomer1() {
        super(new CustomerBuilder()
                .setAccountNumber("111111")
                .setFirstName("Updated")
                .setSurname("Updated")
                .setBalance(BigDecimal.valueOf(777)));
    }

}
