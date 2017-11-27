package uk.co.mruoc.facade;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import uk.co.mruoc.http.client.*;

import javax.ws.rs.core.UriInfo;

public class TokenFetcher {

    private final AuthConfig config;
    private final HttpClient httpClient;

    public TokenFetcher(HttpClient httpClient, AuthConfig config) {
        this.httpClient = httpClient;
        this.config = config;
    }

    public String fetch(UriInfo uriInfo, String code) {
        Response response = requestToken(uriInfo, code);
        return extractToken(response);
    }

    private Response requestToken(UriInfo uriInfo, String code) {
        String body = buildRequestBody(uriInfo, code);
        Headers headers = new Headers();
        headers.add(new ContentTypeHeader("application/x-www-form-urlencoded"));
        headers.add(new AcceptHeader("application/json"));
        return httpClient.post(config.getTokenUrl(), body, headers);
    }

    private String extractToken(Response response) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response.getBody()).getAsJsonObject();
        return json.get("access_token").getAsString();
    }

    private String buildRequestBody(UriInfo uriInfo, String code) {
        StringBuilder body = new StringBuilder();
        body.append("code=").append(code);
        body.append("&client_id=").append(config.getClientId());
        body.append("&client_secret=").append(config.getClientSecret());
        body.append("&redirect_uri=").append(config.getRedirectUrl(uriInfo));
        body.append("&grant_type=authorization_code");
        return body.toString();
    }


}
