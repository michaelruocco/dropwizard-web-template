package uk.co.mruoc.facade;

import uk.co.mruoc.resources.view.SessionUser;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public interface Authenticator {

    void handleCallback(UriInfo uriInfo, SessionUser sessionUser, String code, String state);

}
