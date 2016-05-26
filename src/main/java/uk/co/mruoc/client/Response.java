package uk.co.mruoc.client;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Response {

    private final int statusCode;
    private final String body;
    private final Map<String, String> headers;

    private Response(int statusCode, String body, Map<String, String> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    String getBody() {
        return body;
    }

    int getStatusCode() {
        return statusCode;
    }

    String getHeader(String key) {
        return headers.get(key);
    }

    static Response fromApacheResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        String body = EntityUtils.toString(response.getEntity());
        Map<String, String> headers = extractHeaders(response);

        return new Response(statusCode, body, headers);
    }

    private static Map<String, String> extractHeaders(HttpResponse response) {
        Map<String, String> result = new HashMap<>();
        for (Header header : response.getAllHeaders())
            result.put(header.getName(), header.getValue());
        return result;
    }

}

