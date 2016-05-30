package uk.co.mruoc.service;

import uk.co.mruoc.api.Customer;
import uk.co.mruoc.jdbi.CustomerDao;

public class CreateCustomerService {

    private final CustomerDao customerDao;

    public CreateCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void create(Customer customer) {
        customerDao.create(customer);
    }

}
