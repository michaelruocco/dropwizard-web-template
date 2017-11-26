package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/logout")
public class LogoutViewResource extends LoginableViewResource {

    public LogoutViewResource(Authenticator authenticator) {
        super(authenticator);
    }

    @GET
    public View doLogout(@Context UriInfo uriInfo, @Session HttpSession session) {
        logout(session);
        return createIndexView(uriInfo, session);
    }

}
