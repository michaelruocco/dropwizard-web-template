package uk.co.mruoc.service;

import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.jdbi.CustomerDao;

public class UpdateCustomerService {

    private final CustomerDao customerDao;

    public UpdateCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void update(Customer customer) {
        customerDao.update(customer);
    }

}
