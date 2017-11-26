package uk.co.mruoc;

import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.facade.UserInfo;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class StubbedAuthenticator implements Authenticator {

    private boolean isLoggedIn;

    @Override
    public String getLoginUrl(UriInfo uriInfo) {
        return null;
    }

    @Override
    public UserInfo getUserInfo(HttpSession session) {
        return null;
    }

    @Override
    public void handleCallback(UriInfo uriInfo, HttpSession session, String code, String state) {

    }

    @Override
    public boolean isLoggedIn(HttpSession session) {
        return isLoggedIn;
    }

    @Override
    public void logout(HttpSession session) {

    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

}
