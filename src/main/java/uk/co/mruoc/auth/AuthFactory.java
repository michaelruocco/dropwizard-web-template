package uk.co.mruoc.auth;

import javax.ws.rs.core.UriInfo;

public interface AuthFactory {

    String getAuthUrl(UriInfo uriInfo, String type);

    boolean canAuth(String type);

    Authenticator getAuthenticator(String type);

}
