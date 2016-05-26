package uk.co.mruoc.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import uk.co.mruoc.api.Customer;

public class CustomerClient {

    private static final String CUSTOMERS_URL = "http://localhost:8090/web-template/ws/v1/customers";

    private final JsonConverter jsonConverter = new JsonConverter();

    private Client client = new Client();

    public CustomerResponse createCustomer(Customer customer) {
        HttpPost post = client.createPost(CUSTOMERS_URL, jsonConverter.toJson(customer));
        Response response = execute(post);
        return new CustomerResponse(response);
    }

    public CustomerResponse getCustomer(String accountNumber) {
        HttpGet get = client.createGet(CUSTOMERS_URL + "/" + accountNumber);
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
