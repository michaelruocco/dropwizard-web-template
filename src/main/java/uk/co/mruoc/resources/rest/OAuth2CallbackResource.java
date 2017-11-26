package uk.co.mruoc.resources.rest;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.resources.view.SessionUser;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class OAuth2CallbackResource {

    private final Authenticator authenticator;

    public OAuth2CallbackResource(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @GET
    @Path("oauth2callback")
    public View get(@Context UriInfo uriInfo, @Session HttpSession session, @QueryParam("code") String code, @QueryParam("state") String state) {
        authenticator.handleCallback(uriInfo, session, code, state);
        SessionUser sessionUser = new SessionUser(authenticator.getLoginUrl(uriInfo), session);
        return new IndexView(sessionUser);
    }

}
