package uk.co.mruoc.resources.rest;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.facade.Authenticator;
import uk.co.mruoc.facade.AuthenticatorFactory;
import uk.co.mruoc.resources.view.SessionUser;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class OAuth2CallbackResource {

    private final AuthenticatorFactory authenticatorFactory;

    public OAuth2CallbackResource(AuthenticatorFactory authenticatorFactory) {
        this.authenticatorFactory = authenticatorFactory;
    }

    @GET
    @Path("oauth2callback/{type}")
    public View google(@Context UriInfo uriInfo, @Session HttpSession session, @PathParam("type") String type, @QueryParam("code") String code, @QueryParam("state") String state) {
        Authenticator authenticator = authenticatorFactory.build(type);
        SessionUser sessionUser = new SessionUser(session);
        authenticator.handleCallback(uriInfo, sessionUser, code, state);
        return new IndexView(session, uriInfo);
    }

}
