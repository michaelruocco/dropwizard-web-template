package uk.co.mruoc.resources;

import io.dropwizard.views.View;
import uk.co.mruoc.view.IndexView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class IndexViewResource {

    @GET
    public View getIndex() {
        return new IndexView();
    }

}
