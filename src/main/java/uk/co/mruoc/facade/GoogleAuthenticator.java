package uk.co.mruoc.facade;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import uk.co.mruoc.http.client.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class GoogleAuthenticator implements Authenticator {

    private static final String ATTRIBUTE_NAME = "userInfo";

    private final HttpClient httpClient = new SimpleHttpClient();
    private final UserInfoConverter userInfoConverter = new UserInfoConverter();

    private final GoogleAuthConfig config;

    public GoogleAuthenticator() {
        this(new DefaultGoogleAuthConfig());
    }

    public GoogleAuthenticator(GoogleAuthConfig config) {
        this.config = config;
    }

    @Override
    public String getLoginUrl(UriInfo uriInfo) {
        StringBuilder url = new StringBuilder();
        url.append(config.getAuthUrl());
        url.append("?scope=https://www.googleapis.com/auth/userinfo.profile+https://www.googleapis.com/auth/userinfo.email");
        url.append("&state=%2Fprofile");
        url.append("&response_type=code");
        url.append("&client_id=").append(config.getClientId());
        url.append("&redirect_uri=").append(buildRedirectUrl(uriInfo));
        return url.toString();
    }

    @Override
    public void handleCallback(UriInfo uriInfo, HttpSession session, String code, String state) {
        Response tokenResponse = requestToken(uriInfo, code);
        String accessToken = extractAccessToken(tokenResponse);

        Response userInfoResponse = requestUserInfo(accessToken);
        UserInfo userInfo = extractUserInfo(userInfoResponse);
        session.setAttribute(ATTRIBUTE_NAME, userInfo);
    }

    @Override
    public UserInfo getUserInfo(HttpSession session) {
        return (UserInfo) session.getAttribute(ATTRIBUTE_NAME);
    }

    @Override
    public boolean isLoggedIn(HttpSession session) {
        return getUserInfo(session) != null;
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute(ATTRIBUTE_NAME);
    }

    private Response requestToken(UriInfo uriInfo, String code) {
        String body = buildTokenRequestBody(uriInfo, code);
        Headers headers = new Headers();
        headers.add(new ContentTypeHeader("application/x-www-form-urlencoded"));
        return httpClient.post(config.getTokenUrl(), body, headers);
    }

    private String extractAccessToken(Response response) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response.getBody()).getAsJsonObject();
        return json.get("access_token").getAsString();
    }

    private String buildTokenRequestBody(UriInfo uriInfo, String code) {
        StringBuilder url = new StringBuilder();
        url.append("code=").append(code);
        url.append("&client_id=").append(config.getClientId());
        url.append("&client_secret=").append(config.getClientSecret());
        url.append("&redirect_uri=").append(buildRedirectUrl(uriInfo));
        url.append("&grant_type=authorization_code");
        return url.toString();
    }

    private Response requestUserInfo(String accessToken) {
        String url = buildUserInfoUrl(accessToken);
        return httpClient.get(url);
    }

    private String buildUserInfoUrl(String accessToken) {
        StringBuilder url = new StringBuilder();
        url.append(config.getUserInfoUrl());
        url.append("?access_token=").append(accessToken);
        return url.toString();
    }

    private String buildRedirectUrl(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(config.getRedirectResource()).toTemplate();
    }

    private UserInfo extractUserInfo(Response response) {
        return userInfoConverter.fromJson(response.getBody());
    }

}
