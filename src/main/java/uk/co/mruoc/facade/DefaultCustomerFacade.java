package uk.co.mruoc.facade;

import uk.co.mruoc.Customer;
import uk.co.mruoc.jdbi.CustomerDao;

import java.util.List;

public class DefaultCustomerFacade implements CustomerFacade {

    private final CustomerDao dao;

    public DefaultCustomerFacade(CustomerDao dao) {
        this.dao = dao;
    }

    @Override
    public Customer read(String accountNumber) {
        return dao.read(accountNumber);
    }

    @Override
    public List<Customer> read() { return dao.read(); }

    @Override
    public void create(Customer customer) {
        dao.create(customer);
    }

    @Override
    public void update(Customer customer) {
        dao.update(customer);
    }

    @Override
    public void delete(String accountNumber) {
        dao.delete(accountNumber);
    }

    @Override
    public boolean exists(String accountNumber) {
        return dao.exists(accountNumber);
    }

}
