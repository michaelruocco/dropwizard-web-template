package uk.co.mruoc.facade;

import javax.ws.rs.core.UriInfo;

public interface AuthConfig {

    boolean canAuth();

    String getAuthUrl(UriInfo uriInfo);

    String getClientId();

    String getClientSecret();

    String getRedirectUrl(UriInfo uriInfo);

    String getTokenUrl();

    String getUserInfoUrl();

}
