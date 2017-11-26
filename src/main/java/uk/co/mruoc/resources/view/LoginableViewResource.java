package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;

public abstract class LoginableViewResource {

    private Authenticator authenticator;

    public LoginableViewResource(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public boolean isLoggedIn(HttpSession session) {
        return authenticator.isLoggedIn(session);
    }

    public void logout(HttpSession session) {
        authenticator.logout(session);
    }

    public View createIndexView(UriInfo uriInfo, HttpSession session) {
        return new IndexView(createSessionUser(uriInfo, session));
    }

    public SessionUser createSessionUser(UriInfo uriInfo, HttpSession session) {
        return new SessionUser(authenticator.getLoginUrl(uriInfo), session);
    }

}
