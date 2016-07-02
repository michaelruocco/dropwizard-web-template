package uk.co.mruoc.client;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import uk.co.mruoc.exception.ClientException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.net.HttpHeaders.ACCEPT;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static java.util.Collections.singletonList;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

class Client {

    private static final Logger LOG = Logger.getLogger(Client.class);

    private HttpClient client = HttpClientBuilder.create().build();

    protected Response execute(HttpRequestBase request) {
        try {
            return Response.fromApacheResponse(client.execute(request));
        } catch (IOException e) {
            throw new ClientException(e);
        } finally {
            request.releaseConnection();
        }
    }

    protected HttpGet createGet(String endpoint) {
        return createGet(endpoint, new HashMap<>());
    }

    protected HttpPost createPost(String endpoint, String entity) {
        Map<String, String> headers = new HashMap<>();
        return createPost(endpoint, entity, headers);
    }

    protected HttpPut createPut(String endpoint, String entity) {
        Map<String, String> headers = new HashMap<>();
        return createPut(endpoint, entity, headers);
    }

    protected HttpDelete createDelete(String endpoint) {
        HttpDelete delete = new HttpDelete(endpoint);
        logInfo("creating DELETE request for " + endpoint);
        return delete;
    }

    private HttpGet createGet(String endpoint, Map<String, String> headers) {
        HttpGet get = new HttpGet(endpoint);
        headers.put(ACCEPT, APPLICATION_JSON.getMimeType());
        addHeaders(get, headers);
        logInfo("creating GET request for " + endpoint + " with headers " + singletonList(headers));
        return get;
    }

    private HttpPost createPost(String endpoint, String entity, Map<String, String> headers) {
        HttpPost post = new HttpPost(endpoint);
        setupEntityRequest(post, entity, headers);
        logInfo("creating POST request for " + endpoint + " with entity " + entity + " and headers " + singletonList(headers));
        return post;
    }

    private HttpPut createPut(String endpoint, String entity, Map<String, String> headers) {
        HttpPut put = new HttpPut(endpoint);
        setupEntityRequest(put, entity, headers);
        logInfo("creating PUT request for " + endpoint + " with entity " + entity + " and headers " + singletonList(headers));
        return put;
    }

    private void setupEntityRequest(HttpEntityEnclosingRequest request, String entity, Map<String, String> headers) {
        request.setEntity(new StringEntity(entity, APPLICATION_JSON));
        headers.put(CONTENT_TYPE, APPLICATION_JSON.toString());
        addHeaders(request, headers);
    }

    private void addHeaders(HttpRequest request, Map<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet())
            request.setHeader(header.getKey(), header.getValue());
    }

    private void logInfo(String message) {
        LOG.info(message);
    }

}
