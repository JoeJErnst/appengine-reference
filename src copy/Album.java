import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

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

@Entity
public class Album {

    /**
     * Just like on the Artist entity, we'll use the album's Search Engine Friendly Name as the Id.
     */
    @Id
    private String sefName;

    private String name;

    @Parent
    private Ref<Artist> artist;

    private String description;

    public String getSefName() {
        return sefName;
    }

    public void setSefName(String sefName) {
        this.sefName = sefName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist.get();
    }

    public void setArtist(Key<Artist> key) {
        this.artist = Ref.create(key);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
