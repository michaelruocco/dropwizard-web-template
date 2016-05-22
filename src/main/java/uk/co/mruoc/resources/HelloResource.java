package uk.co.mruoc.resources;

import com.codahale.metrics.annotation.Timed;
import uk.co.mruoc.api.Saying;
import uk.co.mruoc.jdbi.SayingDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/web-template")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloResource {

    private final SayingDao sayingDao;

    public HelloResource(SayingDao sayingDao) {
        this.sayingDao = sayingDao;
    }

    @GET
    @Path("/{id}")
    @Timed
    public Saying sayHello(@PathParam("id") long id) {
        return sayingDao.getSaying(id);
    }

    @POST
    @Timed
    public Saying createSaying(Saying saying) {
        sayingDao.insert(saying);
        return sayingDao.getSaying(saying.getId());
    }

}
