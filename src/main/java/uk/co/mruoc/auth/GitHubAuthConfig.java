package uk.co.mruoc.auth;

import javax.ws.rs.core.UriInfo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class GitHubAuthConfig implements AuthConfig {

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
        return System.getenv("GITHUB_CLIENT_ID");
    }

    @Override
    public String getClientSecret() {
        return System.getenv("GITHUB_CLIENT_SECRET");
    }

    @Override
    public String getRedirectUrl(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path("oauth2callback/github").toTemplate();
    }
    @Override
    public String getTokenUrl() {
        return "https://github.com/login/oauth/access_token";
    }

    @Override
    public String getUserInfoUrl() {
        return "https://api.github.com/user";
    }

    private String buildAuthUrl(UriInfo uriInfo) {
        StringBuilder url = new StringBuilder();
        url.append("https://github.com/login/oauth/authorize");
        url.append("?scope=user:email");
        url.append("&state=%2Fprofile");
        url.append("&client_id=").append(getClientId());
        url.append("&redirect_uri=").append(getRedirectUrl(uriInfo));
        return url.toString();
    }

}
