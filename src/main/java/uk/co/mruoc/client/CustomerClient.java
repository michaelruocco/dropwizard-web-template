package uk.co.mruoc.client;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import uk.co.mruoc.api.Customer;

import java.io.*;

public class CustomerClient {

    private static final Logger LOG = Logger.getLogger(CustomerClient.class);

    private static final String CUSTOMERS_URL = "http://localhost:8090/web-template/ws/v1/customers";
    private static final String CUSTOMER_URL = CUSTOMERS_URL + "/%s";

    private final Gson gson = new Gson();

    public CustomerResponse createCustomer(Customer customer) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = createPost(customer);
            try (CloseableHttpResponse response = client.execute(post)) {
                return new CustomerResponse(response);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public CustomerResponse getCustomer(String id) {
        String url = String.format(CUSTOMER_URL, id);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = createGet(url);
            try (CloseableHttpResponse response = client.execute(get)) {
                return new CustomerResponse(response);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private HttpPost createPost(Customer customer) {
        logInfo("creating POST request for " + CUSTOMERS_URL);
        HttpPost post = new HttpPost(CUSTOMERS_URL);
        post.setEntity(toEntity(customer));
        post.setHeader("Content-type", "application/json");
        return post;
    }

    private HttpGet createGet(String url) {
        logInfo("creating GET request for " + url);
        return new HttpGet(url);
    }

    private HttpEntity toEntity(Customer customer) {
        try {
            String json = gson.toJson(customer);
            logInfo("building entity with string " + json);
            return new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void logInfo(String message) {
        LOG.info(message);
    }

}
