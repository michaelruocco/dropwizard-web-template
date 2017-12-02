package uk.co.mruoc.auth;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public class FakeAuthenticator implements Authenticator {

    @Override
    public HttpSession handleCallback(UriInfo uriInfo, HttpSession session, String code, String state) {
        SessionUser user = new SessionUser(session);
        SessionFakeId fakeId = new SessionFakeId(session);
        user.setInfo(new FakeUserInfo(fakeId.get()));
        fakeId.clear();
        return session;
    }

}
