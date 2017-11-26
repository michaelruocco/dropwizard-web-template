package uk.co.mruoc.facade;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public interface Authenticator {

    String getLoginUrl(UriInfo uriInfo);

    UserInfo getUserInfo(HttpSession session);

    void handleCallback(UriInfo uriInfo, HttpSession session, String code, String state);

    boolean isLoggedIn(HttpSession session);

    void logout(HttpSession session);

}
