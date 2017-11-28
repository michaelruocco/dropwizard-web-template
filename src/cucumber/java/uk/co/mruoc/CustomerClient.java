package uk.co.mruoc;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

public class CustomerClient {

    private static final String CUSTOMERS_URL = "http://localhost:8090/ws/v1/customers/";

    private final JsonConverter jsonConverter = new JsonConverter();
    private HttpClient httpClient;

    public CustomerClient() {
        this(new SimpleHttpClient());
    }

    public CustomerClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CustomerResponse getCustomer(String accountNumber) {
        return doGet(CUSTOMERS_URL + accountNumber);
    }

    public CustomerResponse getCustomers() {
        return doGet(CUSTOMERS_URL);
    }

    public CustomerResponse createCustomer(Customer customer) {
        String body = toJson(customer);
        Response response = httpClient.post(CUSTOMERS_URL, body);
        return new CustomerResponse(response);
    }

    public CustomerResponse updateCustomer(Customer customer) {
        String body = toJson(customer);
        Response response = httpClient.put(CUSTOMERS_URL, body);
        return new CustomerResponse(response);
    }

    public CustomerResponse deleteCustomer(String accountNumber) {
        Response response = httpClient.delete(CUSTOMERS_URL + accountNumber);
        return new CustomerResponse(response);
    }

    private CustomerResponse doGet(String endpoint) {
        Response response = httpClient.get(endpoint);
        return new CustomerResponse(response);
    }

    private String toJson(Customer customer) {
        return jsonConverter.toJson(customer);
    }

}
