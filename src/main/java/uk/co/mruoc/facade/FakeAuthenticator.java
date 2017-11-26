package uk.co.mruoc.facade;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class FakeAuthenticator implements Authenticator {

    private static final String ATTRIBUTE_NAME = "userInfo";

    @Override
    public String getLoginUrl(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path("/oauth2callback").toTemplate();
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

    @Override
    public void handleCallback(UriInfo uriInfo, HttpSession session, String code, String state) {
        session.setAttribute(ATTRIBUTE_NAME, new FakeUserInfo());
    }

}
