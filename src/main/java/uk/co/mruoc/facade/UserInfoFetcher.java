package uk.co.mruoc.facade;

import uk.co.mruoc.http.client.*;

public class UserInfoFetcher {

    private final HttpClient httpClient;
    private final AuthConfig config;
    private final UserInfoConverter converter;

    public UserInfoFetcher(HttpClient httpClient, AuthConfig config, UserInfoConverter converter) {
        this.httpClient = httpClient;
        this.config = config;
        this.converter = converter;
    }

    public UserInfo fetch(String accessToken) {
        Response response = requestUserInfo(accessToken);
        return converter.fromJson(response.getBody());
    }

    private Response requestUserInfo(String accessToken) {
        String url = config.getUserInfoUrl();
        Headers headers = new Headers();
        headers.add(new BearerTokenHeader(accessToken));
        return httpClient.get(url, headers);
    }

}
