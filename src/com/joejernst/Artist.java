package com.joejernst;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;

import java.util.List;

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
public class Artist {
    /**
     * We'll use the Search Engine Friendly Name as the Id, since that's what we'll use to build the URL for this Artist.
     */
    @Id
    private String sefName;

    @Ignore
    private Key<Artist> key;

    private String name;

    private String description;

    @Load
    List<Album> albums;


    public String getSefName() {
        return sefName;
    }

    public void setSefName(String sefName) {
        this.sefName = sefName;
    }

    public Key<Artist> getKey() {
        return key;
    }

    public void setKey(Key<Artist> key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
