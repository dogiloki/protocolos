package objects.wire;

import java.util.UUID;
import multitaks.annotations.directory.Key;
import objects.scenery.Line;

/**
 *
 * @author dogi_
 */

public class Wire extends Line{
    
    @Key(value="id_connector1")
    public String id_connector1=UUID.randomUUID().toString();
    @Key(value="id_connector2")
    public String id_connector2=UUID.randomUUID().toString();
    
    public Wire(){
        
    }
}
