package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.auth.SessionUser;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/logout")
public class LogoutViewResource {

    @GET
    public View doLogout(@Session HttpSession session, @Context UriInfo uriInfo) {
        SessionUser sessionUser = new SessionUser(session);
        sessionUser.logout();
        return new IndexView(session, uriInfo);
    }

}
