package uk.co.mruoc.facade;

import uk.co.mruoc.Customer;
import uk.co.mruoc.jdbi.CustomerDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeCustomerDao extends CustomerDao {

    private Customer lastCreatedCustomer;
    private Customer lastUpdatedCustomer;
    private Customer customerToRead;

    private String lastReadAccountNumber;
    private String lastDeletedAccountNumber;

    private List<Customer> customersToRead = new ArrayList<>();

    private int count;

    @Override
    public void create(Customer customer) {
        this.lastCreatedCustomer = customer;
    }

    @Override
    public Customer read(String accountNumber) {
        this.lastReadAccountNumber = accountNumber;
        return customerToRead;
    }

    @Override
    public List<Customer> read() {
        return customersToRead;
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
    protected int count(String accountNumber) {
        return count;
    }

    public void setCustomerToRead(Customer customerToRead) {
        this.customerToRead = customerToRead;
    }

    public void setCustomersToRead(Customer... customersToRead) {
        this.customersToRead = Arrays.asList(customersToRead);
    }

    public Customer getLastCreatedCustomer() {
        return lastCreatedCustomer;
    }

    public Customer getLastUpdatedCustomer() {
        return lastUpdatedCustomer;
    }

    public String getLastReadAccountNumber() {
        return lastReadAccountNumber;
    }

    public String getLastDeletedAccountNumber() {
        return lastDeletedAccountNumber;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
