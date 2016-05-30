package uk.co.mruoc.service;

import uk.co.mruoc.CustomerErrorMessageBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.exception.CustomerNotFoundException;
import uk.co.mruoc.jdbi.CustomerDao;

public class UpdateCustomerService {

    private final CustomerErrorMessageBuilder errorMessageBuilder = new CustomerErrorMessageBuilder();
    private final CustomerDao customerDao;

    public UpdateCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void update(Customer customer) {
        if (!customerDao.exists(customer))
            throw new CustomerNotFoundException(errorMessageBuilder.buildNotFound(customer));
        customerDao.update(customer);
    }

}
