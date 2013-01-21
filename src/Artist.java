import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Copyright 2012: ux1.tv
 *
 * @author Joe Ernst
 *         Date: 1/20/13
 */
@Entity
public class Artist {
    @Id
    String name;
}
