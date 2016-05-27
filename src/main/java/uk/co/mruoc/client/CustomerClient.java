package uk.co.mruoc.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import uk.co.mruoc.api.Customer;

public class CustomerClient {

    private static final String CUSTOMERS_URL = "http://localhost:8090/web-template/ws/v1/customers/";

    private final JsonConverter jsonConverter = new JsonConverter();

    private Client client = new Client();

    public CustomerResponse getCustomer(String accountNumber) {
        return doGet(CUSTOMERS_URL + accountNumber);
    }

    public CustomerResponse getCustomers() {
        return doGet(CUSTOMERS_URL);
    }

    public CustomerResponse createCustomer(Customer customer) {
        HttpPost post = client.createPost(CUSTOMERS_URL, jsonConverter.toJson(customer));
        Response response = execute(post);
        return new CustomerResponse(response);
    }

    public CustomerResponse updateCustomer(Customer customer) {
        HttpPut put = client.createPut(CUSTOMERS_URL, jsonConverter.toJson(customer));
        Response response = execute(put);
        return new CustomerResponse(response);
    }

    private CustomerResponse doGet(String endpoint) {
        HttpGet get = client.createGet(endpoint);
        Response response = execute(get);
        return new CustomerResponse(response);
    }

    private Response execute(HttpRequestBase request) {
        try {
            return client.execute(request);
        } finally {
            request.releaseConnection();
        }
    }

}
