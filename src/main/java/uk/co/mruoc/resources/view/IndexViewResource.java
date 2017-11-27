package uk.co.mruoc.resources.view;

import io.dropwizard.jersey.sessions.Session;
import io.dropwizard.views.View;
import uk.co.mruoc.view.IndexView;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class IndexViewResource {

    @GET
    public View getIndex(@Context UriInfo uriInfo, @Session HttpSession session) {
        return new IndexView(session, uriInfo);
    }

}
