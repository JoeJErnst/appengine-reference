
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright 2012: ux1.tv
 *
 * @author Joe Ernst
 *         Date: 1/4/13
 */
public class Application extends javax.ws.rs.core.Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(ArtistResource.class);
        return classes;
    }
}
