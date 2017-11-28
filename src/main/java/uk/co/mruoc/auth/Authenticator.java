package uk.co.mruoc.auth;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public interface Authenticator {

    HttpSession handleCallback(UriInfo uriInfo, HttpSession session, String code, String state);

}
