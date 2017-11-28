package uk.co.mruoc.facade;

import uk.co.mruoc.Customer;

import java.util.List;

public interface CustomerFacade {

    Customer read(String accountNumber);

    List<Customer> read();

    void create(Customer customer);

    void update(Customer customer);

    void delete(String accountNumber);

    boolean exists(String accountNumber);

}
