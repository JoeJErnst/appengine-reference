import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.objectify.util.jackson.ObjectifyJacksonModule;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntityProvider<E> implements MessageBodyReader<E>, MessageBodyWriter<E> {

    Logger logger = Logger.getAnonymousLogger();

    @Override
    public boolean isReadable(Class<?> type,
                              Type genericType,
                              Annotation[] annotations,
                              MediaType mediaType) {
        // Todo: figure out how to do this properly
        return true;
    }


    @Override
    public E readFrom(Class<E> type,
                      Type genericType,
                      Annotation[] annotations,
                      MediaType mediaType,
                      MultivaluedMap<String, String> httpHeaders,
                      InputStream entityStream) throws IOException, WebApplicationException {

        E entity = null;

        try {
            /* This InputStream reads from the entityStream and constructs the object. */
            entity = parse(entityStream, type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }

        return entity;

    }

    @Override
    public long getSize(E e,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType) {
        /* return -1 if the content length cannot be determined */

        return toJSON(e).length();
    }


    @Override
    public boolean isWriteable(Class<?> type,
                               Type genericType,
                               Annotation[] annotations,
                               MediaType mediaType) {
        //return type.getSuperclass() == Entity.class;
        return true;
    }


    @Override
    public void writeTo(E e,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {

        entityStream.write(toJSON(e).getBytes("UTF-8"));
    }


    @SuppressWarnings("unchecked")
    private E parse(InputStream json, Class<E> type) throws Exception {
        ObjectifyJacksonModule ojm = new ObjectifyJacksonModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(ojm);

        return (E) mapper.readValue(json, type);
    }

    private String toJSON(E entity) {
        String json = null;
        ObjectifyJacksonModule ojm = new ObjectifyJacksonModule();
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(ojm);

        try {
            json = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }

        logger.info("Serializing '" + json + "'.");
        return json;
    }
}
