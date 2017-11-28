package uk.co.mruoc.auth;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class FakeAuthenticator implements Authenticator {

    @Override
    public HttpSession handleCallback(UriInfo uriInfo, HttpSession session, String code, String state) {
        SessionUser sessionUser = new SessionUser(session);
        sessionUser.setInfo(new FakeUserInfo());
        return session;
    }

}
