package uk.co.mruoc.auth;

import javax.ws.rs.core.UriInfo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class GoogleAuthConfig implements AuthConfig {

    @Override
    public boolean canAuth() {
        return isNotEmpty(getClientId()) && isNotEmpty(getClientSecret());
    }

    @Override
    public String getAuthUrl(UriInfo uriInfo) {
        return buildAuthUrl(uriInfo);
    }

    @Override
    public String getClientId() {
        return System.getenv("GOOGLE_CLIENT_ID");
    }

    @Override
    public String getClientSecret() {
        return System.getenv("GOOGLE_CLIENT_SECRET");
    }

    @Override
    public String getRedirectUrl(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path("oauth2callback/google").toTemplate();
    }

    @Override
    public String getTokenUrl() {
        return "https://accounts.google.com/o/oauth2/token";
    }

    @Override
    public String getUserInfoUrl() {
        return "https://www.googleapis.com/oauth2/v1/userinfo";
    }

    private String buildAuthUrl(UriInfo uriInfo) {
        StringBuilder url = new StringBuilder();
        url.append("https://accounts.google.com/o/oauth2/auth");
        url.append("?scope=https://www.googleapis.com/auth/userinfo.profile+https://www.googleapis.com/auth/userinfo.email");
        url.append("&state=%2Fprofile");
        url.append("&response_type=code");
        url.append("&client_id=").append(getClientId());
        url.append("&redirect_uri=").append(getRedirectUrl(uriInfo));
        return url.toString();
    }

}
