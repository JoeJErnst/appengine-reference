import com.googlecode.objectify.Key;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * Copyright 2013 Joe J. Ernst
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Path("/albums")
public class AlbumResource {
    @GET
    @Path("{sefName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbum(@PathParam("sefName") String sefName) {
        Response returnResponse;

        try {
            Key<Album> key = Key.create(Album.class, sefName);

            Album album = OfyService.ofy().load().key(key).get();

            returnResponse = Response.ok().entity(album).build();
        } catch (Exception e) {
            String errorMessage = "Could not find Album: " + e.getMessage();
            returnResponse = Response.serverError().entity(errorMessage).build();
        }

        return returnResponse;
    }

    @POST
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAlbum(Album album) {
        Response returnResponse;

        try {
            Key<Album> key = OfyService.ofy().save().entity(album).now();
            Album responseEntity = OfyService.ofy().load().key(key).get();

            returnResponse = Response.ok().entity(responseEntity).build();
        } catch (Exception e) {
            String errorMessage = "Could not create or update Album: " + e.getMessage();
            returnResponse = Response.serverError().entity(errorMessage).build();
        }

        return returnResponse;

    }

}
