package uk.co.mruoc.service;

import uk.co.mruoc.jdbi.CustomerDao;

public class DeleteCustomerService {

    private final CustomerDao customerDao;

    public DeleteCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void delete(String accountNumber) {
        customerDao.delete(accountNumber);
    }

}
