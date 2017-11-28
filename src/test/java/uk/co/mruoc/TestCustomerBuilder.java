package uk.co.mruoc;

import java.util.Arrays;
import java.util.List;

public class TestCustomerBuilder {

    public Customer buildCustomer1() {
        return new FakeCustomer1();
    }

    public Customer buildUpdateCustomer1() {
        return new FakeUpdatedCustomer1();
    }

    public Customer buildNewCustomer() {
        return new FakeNewCustomer();
    }

    private Customer buildCustomer2() {
        return new FakeCustomer2();
    }

    public List<Customer> buildCustomerList() {
        return Arrays.asList(buildCustomer1(), buildCustomer2());
    }

}
