package uk.co.mruoc.facade;

import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.UriInfo;

public class FakeAuthConfig implements AuthConfig {

    @Override
    public boolean canAuth() {
        return StringUtils.isNotEmpty(getClientId());
    }

    @Override
    public String getAuthUrl(UriInfo uriInfo) {
        return getRedirectUrl(uriInfo);
    }

    @Override
    public String getClientId() {
        return System.getenv("FAKE_CLIENT_ID");
    }

    @Override
    public String getClientSecret() {
        return "";
    }

    @Override
    public String getRedirectUrl(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path("oauth2callback/fake").toTemplate();
    }

    @Override
    public String getTokenUrl() {
        return "";
    }

    @Override
    public String getUserInfoUrl() {
        return "";
    }

}
