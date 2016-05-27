package uk.co.mruoc.resources;

import uk.co.mruoc.view.IndexView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class IndexViewResource {

    @GET
    public IndexView getIndex() {
        return new IndexView();
    }

}
