package uk.co.mruoc.service;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.exception.CustomerAlreadyExistsException;
import uk.co.mruoc.jdbi.CustomerDao;

public class CreateCustomerService {

    private final CustomerDao customerDao;

    public CreateCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void create(Customer customer) {
        if (exists(customer))
            throw new CustomerAlreadyExistsException(customer.getAccountNumber());
        customerDao.create(customer);
    }

    private boolean exists(Customer customer) {
        return customerDao.count(customer.getAccountNumber()) > 0;
    }

}
