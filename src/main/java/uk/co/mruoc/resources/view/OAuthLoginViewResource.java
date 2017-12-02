package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.auth.AuthFactory;
import uk.co.mruoc.auth.SessionFakeId;
import uk.co.mruoc.view.LoginView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

@Path("/")
public class OAuthLoginViewResource {

    @GET
    @Path("oauth2fakeLogin")
    public View showFakeLogin(@Context UriInfo uriInfo, @Session HttpSession session) {
        return new LoginView(session, uriInfo);
    }

    @POST
    @Path("oauth2fakeLogin")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response handleLogin(@Context UriInfo uriInfo, @Session HttpSession session, MultivaluedMap<String, String> form) {
        String id = form.getFirst("userId");
        SessionFakeId fakeId = new SessionFakeId(session);
        fakeId.set(id);
        URI uri = uriInfo.getBaseUriBuilder().path("oauth2callback/fake").build();
        return Response.seeOther(uri).build();
    }

}
