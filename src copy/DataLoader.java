import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.objectify.util.jackson.ObjectifyJacksonModule;
import com.joejernst.http.Request;
import com.joejernst.http.Response;

import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

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

public class DataLoader {
    private static Logger logger = Logger.getAnonymousLogger();

    static final String artistUrl = "http://localhost:8080/artists/";
    static final String albumUrl = "http://localhost:8080/albums/";

    public static void main(String[] args) {
        theBeatles();
    }

    private static void theBeatles() {
        Artist theBeatles = new Artist();
        theBeatles.setSefName("the-beatles");
        theBeatles.setName("The Beatles");
        theBeatles.setDescription("The greatest rock-n-roll band ever.");

        try {
            Response artistResponse = new Request(artistUrl + theBeatles.getSefName())
                    .addHeader("Content-Type", MediaType.APPLICATION_JSON)
                    .setBody(toJSON(theBeatles))
                    .putResource();

            logger.info("response = " + artistResponse.getBody());

            theBeatles = parse(artistResponse.getBody(), Artist.class);

            Response getResponse = new Request(artistUrl + theBeatles.getSefName())
                    .addHeader("Content-Type", MediaType.APPLICATION_JSON)
                    .getResource();
            logger.info("getResponse = " + getResponse.getBody());

//            Album theWhiteAlbum = new Album();
//            theWhiteAlbum.setArtist(theBeatles.getKey());
//            theWhiteAlbum.setSefName("the-white-album");
//            theWhiteAlbum.setName("The White Album");
//            theWhiteAlbum.setDescription("The Beatles' ninth album, released in 1968.");
//
//            Response albumResponse = new Request(albumUrl + theWhiteAlbum.getSefName())
//                    .addHeader("Content-Type", MediaType.APPLICATION_JSON)
//                    .setBody(toJSON(theWhiteAlbum))
//                    .putResource();
//            logger.info("albumResponse = " + albumResponse.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String toJSON(Object entity) {
        String json = null;
        ObjectifyJacksonModule ojm = new ObjectifyJacksonModule();
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(ojm);

        try {
            json = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }

        return json;
    }

    private static Artist parse(String json, Class<Artist> type) throws Exception {
        return parse(new ByteArrayInputStream(json.getBytes()), type);
    }

    private static Artist parse(InputStream json, Class<Artist> type) throws Exception {
        ObjectifyJacksonModule ojm = new ObjectifyJacksonModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(ojm);

        return (Artist) mapper.readValue(json, type);
    }
}
