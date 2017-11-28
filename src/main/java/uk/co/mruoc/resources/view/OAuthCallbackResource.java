package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.auth.Authenticator;
import uk.co.mruoc.auth.AuthFactory;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class OAuthCallbackResource {

    private final AuthFactory authFactory;

    public OAuthCallbackResource(AuthFactory authFactory) {
        this.authFactory = authFactory;
    }

    @GET
    @Path("oauth2callback/{type}")
    public View auth(@Context UriInfo uriInfo, @Session HttpSession session, @PathParam("type") String type, @QueryParam("code") String code, @QueryParam("state") String state) {
        Authenticator authenticator = authFactory.getAuthenticator(type);
        HttpSession sessionWithUser = authenticator.handleCallback(uriInfo, session, code, state);
        return new IndexView(sessionWithUser, uriInfo);
    }

}
