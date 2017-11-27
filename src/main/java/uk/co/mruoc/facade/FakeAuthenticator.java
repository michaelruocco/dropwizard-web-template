package uk.co.mruoc.facade;

import uk.co.mruoc.resources.view.SessionUser;

import javax.ws.rs.core.UriInfo;

public class FakeAuthenticator implements Authenticator {

    @Override
    public void handleCallback(UriInfo uriInfo, SessionUser sessionUser, String code, String state) {
        sessionUser.setInfo(new FakeUserInfo());
    }

}
