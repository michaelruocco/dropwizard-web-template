package uk.co.mruoc;

import uk.co.mruoc.http.client.Response;

import java.util.List;

public class CustomerResponse {

    private final JsonConverter jsonConverter = new JsonConverter();
    private final Response response;

    public CustomerResponse(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getHeader(String key) {
        return response.getHeader(key);
    }

    public Customer getCustomer() {
        return jsonConverter.toCustomer(getBody());
    }

    public List<Customer> getCustomers() {
        return jsonConverter.toCustomers(getBody());
    }

    public String getErrorMessage() {
        ErrorMessage error = jsonConverter.toErrorMessage(getBody());
        return error.getMessage();
    }

    private String getBody() {
        return response.getBody();
    }

}
