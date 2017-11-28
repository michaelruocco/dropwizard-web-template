package uk.co.mruoc.auth;

import uk.co.mruoc.http.client.HttpClient;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class BaseAuthenticator implements Authenticator {

    private final TokenFetcher tokenFetcher;
    private final UserInfoFetcher userInfoFetcher;

    public BaseAuthenticator(HttpClient httpClient, AuthConfig config, UserInfoConverter userInfoConverter) {
        this(new TokenFetcher(httpClient, config), new UserInfoFetcher(httpClient, config, userInfoConverter));
    }

    public BaseAuthenticator(TokenFetcher tokenFetcher, UserInfoFetcher userInfoFetcher) {
        this.tokenFetcher = tokenFetcher;
        this.userInfoFetcher = userInfoFetcher;
    }

    @Override
    public HttpSession handleCallback(UriInfo uriInfo, HttpSession session, String code, String state) {
        String accessToken = tokenFetcher.fetch(uriInfo, code);
        UserInfo userInfo = userInfoFetcher.fetch(accessToken);
        SessionUser sessionUser = new SessionUser(session);
        sessionUser.setInfo(userInfo);
        return session;
    }

}
