package uk.co.mruoc.facade;

import uk.co.mruoc.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeCustomerFacade implements CustomerFacade {

    private String lastDeletedAccountNumber;
    private List<Customer> customersToRead = new ArrayList<>();
    private Customer customerToRead;
    private Customer lastCreatedCustomer;
    private Customer lastUpdatedCustomer;
    private boolean exists;

    @Override
    public Customer read(String accountNumber) {
        return customerToRead;
    }

    @Override
    public List<Customer> read() {
        return customersToRead;
    }

    @Override
    public void create(Customer customer) {
        this.lastCreatedCustomer = customer;
    }

    @Override
    public void update(Customer customer) {
        this.lastUpdatedCustomer = customer;
    }

    @Override
    public void delete(String accountNumber) {
        this.lastDeletedAccountNumber = accountNumber;
    }

    @Override
    public boolean exists(String accountNumber) {
        return exists;
    }

    public String getLastDeletedAccountNumber() {
        return lastDeletedAccountNumber;
    }

    public void setCustomerToRead(Customer customerToRead) {
        this.customerToRead = customerToRead;
    }

    public void setCustomersToRead(Customer... customersToRead) {
        this.customersToRead = Arrays.asList(customersToRead);
    }

    public void setCustomersToRead(List<Customer> customersToRead) {
        this.customersToRead = customersToRead;
    }

    public Customer getLastCreatedCustomer() {
        return lastCreatedCustomer;
    }

    public Customer getLastUpdatedCustomer() {
        return lastUpdatedCustomer;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean createCustomerCalled() {
        return lastCreatedCustomer != null;
    }

    public boolean updateCustomerCalled() {
        return lastUpdatedCustomer != null;
    }

}
