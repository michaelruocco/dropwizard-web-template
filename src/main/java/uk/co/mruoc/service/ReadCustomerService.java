package uk.co.mruoc.service;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.jdbi.CustomerDao;

import java.util.List;

public class ReadCustomerService {

    private final CustomerDao customerDao;

    public ReadCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer read(String accountNumber) {
        return customerDao.read(accountNumber);
    }

    public List<Customer> read() {
        return customerDao.read();
    }

}
