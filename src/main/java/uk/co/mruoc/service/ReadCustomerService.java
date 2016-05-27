package uk.co.mruoc.service;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.jdbi.CustomerDao;

public class ReadCustomerService {

    private final CustomerDao customerDao;

    public ReadCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer read(String accountNumber) {
        return customerDao.readCustomer(accountNumber);
    }

}
