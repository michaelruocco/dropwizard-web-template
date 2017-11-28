package uk.co.mruoc.resources.view;

import io.dropwizard.views.View;
import uk.co.mruoc.auth.SessionUser;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriInfo;


public abstract class LoginableViewResource {

    public boolean isLoggedIn(HttpSession session) {
        SessionUser sessionUser = new SessionUser(session);
        return sessionUser.isPresent();
    }

    public View buildIndexView(UriInfo uriInfo, HttpSession session) {
        return new IndexView(session, uriInfo);
    }

}
