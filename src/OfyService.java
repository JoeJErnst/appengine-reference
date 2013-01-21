
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Copyright 2012: ux1.tv
 *
 * @author Joe Ernst
 *         Date: 11/25/12
 */
public class OfyService {
    static {
        // Register all of our Entities here.
        factory().register(Artist.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
