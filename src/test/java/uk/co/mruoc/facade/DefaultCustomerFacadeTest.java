package uk.co.mruoc.facade;

import org.junit.Test;
import uk.co.mruoc.Customer;
import uk.co.mruoc.FakeCustomer1;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultCustomerFacadeTest {

    private final FakeCustomerDao dao = new FakeCustomerDao();

    private final CustomerFacade facade = new DefaultCustomerFacade(dao);

    @Test
    public void shouldReadCustomer() {
        String accountNumber = "123456";
        Customer customer = new FakeCustomer1();
        dao.setCustomerToRead(customer);

        Customer result = facade.read(accountNumber);

        assertThat(dao.getLastReadAccountNumber()).isEqualTo(accountNumber);
        assertThat(result).isEqualTo(customer);
    }

    @Test
    public void shouldReadCustomers() {
        Customer customer1 = new FakeCustomer1();
        Customer customer2 = new FakeCustomer1();
        dao.setCustomersToRead(customer1, customer2);

        List<Customer> result = facade.read();

        assertThat(result).containsExactly(customer1, customer2);
    }

    @Test
    public void shouldCreateCustomer() {
        Customer customer = new FakeCustomer1();

        facade.create(customer);

        assertThat(dao.getLastCreatedCustomer()).isEqualTo(customer);
    }

    @Test
    public void shouldUpdateCustomer() {
        Customer customer = new FakeCustomer1();

        facade.update(customer);

        assertThat(dao.getLastUpdatedCustomer()).isEqualTo(customer);
    }

    @Test
    public void shouldDeleteCustomer() {
        String accountNumber = "123456";

        facade.delete(accountNumber);

        assertThat(dao.getLastDeletedAccountNumber()).isEqualTo(accountNumber);
    }

    @Test
    public void shouldReturnCustomerExistsFalseIfNoCustomersMatchId() {
        dao.setCount(0);

        assertThat(facade.exists("")).isFalse();
    }

    @Test
    public void shouldReturnCustomerExistsTrueIfOneOrMoreCustomersMatchId() {
        dao.setCount(1);

        assertThat(facade.exists("")).isTrue();
    }

}
