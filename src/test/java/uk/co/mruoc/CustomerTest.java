package uk.co.mruoc;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.Customer.*;

public class CustomerTest {

    private final CustomerBuilder builder = new CustomerBuilder();

    @Test
    public void shouldReturnAccountNumber() {
        String accountNumber = "123456";

        Customer customer = builder.setAccountNumber(accountNumber).build();

        assertThat(customer.getAccountNumber()).isEqualTo(accountNumber);
    }

    @Test
    public void shouldReturnFirstName() {
        String firstName = "Michael";

        Customer customer = builder.setFirstName(firstName).build();

        assertThat(customer.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void shouldReturnSurname() {
        String surname = "Ruocco";

        Customer customer = builder.setSurname(surname).build();

        assertThat(customer.getSurname()).isEqualTo(surname);
    }

    @Test
    public void shouldReturnBalance() {
        BigDecimal balance = BigDecimal.valueOf(107);

        Customer customer = builder.setBalance(balance).build();

        assertThat(customer.getBalance()).isEqualTo(balance);
    }

    @Test
    public void shouldReturnFullName() {
        String firstName = "Michael";
        String surname = "Ruocco";

        Customer customer = builder.setFirstName(firstName).setSurname(surname).build();

        assertThat(customer.getFullName()).isEqualTo("Michael Ruocco");
    }

}
