package uk.co.mruoc.client;

import uk.co.mruoc.api.Customer;

public class CustomerResponse {

    private final JsonConverter jsonConverter = new JsonConverter();
    private final Response response;

    public CustomerResponse(Response response) {
        this.response = response;
    }

    public Customer getCustomer() {
        return jsonConverter.toCustomer(response.getBody());
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getHeader(String key) {
        return response.getHeader(key);
    }

}
