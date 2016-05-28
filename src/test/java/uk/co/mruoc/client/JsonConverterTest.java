package uk.co.mruoc.client;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import uk.co.mruoc.TestCustomerBuilder;
import uk.co.mruoc.api.Customer;
import uk.co.mruoc.client.JsonConverter.JsonException;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class JsonConverterTest {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String QUOTE = "\"";

    private final TestCustomerBuilder builder = new TestCustomerBuilder();
    private final JsonConverter converter = new JsonConverter();

    @Test
    public void shouldConvertToCustomer() {
        Customer expectedCustomer = builder.buildCustomer1();
        String json = toJson(expectedCustomer);

        Customer customer = converter.toCustomer(json);

        assertThat(customer).isEqualToComparingFieldByField(expectedCustomer);
    }

    @Test
    public void shouldConvertToCustomers() {
        List<Customer> expectedCustomers = builder.buildCustomerList();
        String json = toJson(expectedCustomers);

        List<Customer> customers = converter.toCustomers(json);

        assertThat(customers.size()).isEqualTo(expectedCustomers.size());
        assertThat(customers.get(0)).isEqualToComparingFieldByField(expectedCustomers.get(0));
        assertThat(customers.get(1)).isEqualToComparingFieldByField(expectedCustomers.get(1));
    }

    @Test
    public void shouldConvertCustomerToJson() {
        Customer customer = builder.buildCustomer1();
        String expectedJson = toJson(customer);

        String json = converter.toJson(customer);

        assertThat(json).isEqualTo(expectedJson);
    }

    @Test
    public void shouldConvertCustomersToJson() {
        List<Customer> customers = builder.buildCustomerList();
        String expectedJson = toJson(customers);

        String json = converter.toJson(customers);

        assertThat(json).isEqualTo(expectedJson);
    }

    @Test(expected = JsonException.class)
    public void toCustomerShouldThrowExceptionIfJsonInvalid() {
        converter.toCustomer("not json");
    }

    @Test(expected = JsonException.class)
    public void toCustomerwShouldThrowExceptionIfJsonInvalid() {
        converter.toCustomers("not json");
    }

    private String toJson(Customer customer) {
        return "{" + NEW_LINE +
                "  " + enquote("accountNumber") + " : " + enquote(customer.getAccountNumber()) + "," + NEW_LINE +
                "  " + enquote("firstName") + " : " + enquote(customer.getFirstName()) + "," + NEW_LINE +
                "  " + enquote("surname") + " : " + enquote(customer.getSurname()) + "," + NEW_LINE +
                "  " + enquote("balance") + " : " + customer.getBalance() + NEW_LINE +
                "}";
    }

    private String toJson(List<Customer> customers) {
        String json = "[ ";
        for (Customer customer : customers) {
            json += toJson(customer);
            json += ", ";
        }
        json = StringUtils.removeEnd(json, ", ");
        return json + " ]";
    }

    private String enquote(Object s) {
        return QUOTE + s + QUOTE;
    }

}
