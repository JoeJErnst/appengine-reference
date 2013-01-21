import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Copyright 2012: ux1.tv
 *
 * @author Joe Ernst
 *         Date: 1/20/13
 */
@Path("/artists")
public class ArtistResource {
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPublisher(@PathParam("name") String name) {
        Response returnResponse;

        try {
            Key<Artist> key = Key.create(Artist.class, name);

            Ref<Artist> artist = OfyService.ofy().load().key(key);

            returnResponse = Response.ok().entity(artist).build();
        } catch (Exception e) {
            String errorMessage = "Could not find Publisher: " + e.getMessage();
            returnResponse = Response.serverError().entity(errorMessage).build();
        }

        return returnResponse;
    }

}
