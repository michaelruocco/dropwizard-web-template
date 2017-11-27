package uk.co.mruoc.facade;

import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.SimpleHttpClient;
import uk.co.mruoc.resources.view.SessionUser;

import javax.ws.rs.core.UriInfo;

public class BaseAuthenticator implements Authenticator {

    private final TokenFetcher tokenFetcher;
    private final UserInfoFetcher userInfoFetcher;

    public BaseAuthenticator(HttpClient httpClient, AuthConfig config, UserInfoConverter userInfoConverter) {
        this.tokenFetcher = new TokenFetcher(httpClient, config);
        this.userInfoFetcher = new UserInfoFetcher(httpClient, config, userInfoConverter);
    }

    @Override
    public void handleCallback(UriInfo uriInfo, SessionUser sessionUser, String code, String state) {
        String accessToken = tokenFetcher.fetch(uriInfo, code);
        UserInfo userInfo = userInfoFetcher.fetch(accessToken);
        sessionUser.setInfo(userInfo);
    }

}
