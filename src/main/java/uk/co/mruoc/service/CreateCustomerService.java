package uk.co.mruoc.service;

import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.exception.CustomerAlreadyExistsException;
import uk.co.mruoc.jdbi.CustomerDao;

public class CreateCustomerService {

    private final CustomerErrorMessageBuilder errorMessageBuilder = new CustomerErrorMessageBuilder();
    private final CustomerDao customerDao;

    public CreateCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void create(Customer customer) {
        if (customerDao.exists(customer))
            throw new CustomerAlreadyExistsException(errorMessageBuilder.buildAlreadyExists(customer));
        customerDao.create(customer);
    }

}
