
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.util.jackson.ObjectifyJacksonModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class Sandbox {

    Logger logger = Logger.getAnonymousLogger();


    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void playAround() {
        String json = null;
        ObjectifyJacksonModule ojm = new ObjectifyJacksonModule();
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(ojm);

        Key key = Key.create(Artist.class, "the-beatles");

        logger.info("Web Safe String = " + key.getString());
        logger.info("toString() = " + key.toString());

        try {
            json = mapper.writeValueAsString(key);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }

        logger.info("Serializing '" + json + "'.");
    }
}
