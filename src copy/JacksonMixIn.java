import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.Key;

/**
 * Copyright 2012: ux1.tv
 *
 * @author Joe Ernst
 *         Date: 1/22/13
 */
public interface JacksonMixIn {
    @JsonIgnore
    <V> Key<V> getRoot();
}
