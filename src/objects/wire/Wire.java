package objects.wire;

import java.util.UUID;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Wire{
    
    @Key(value="id_connector1")
    public String id_connector1=UUID.randomUUID().toString();
    @Key(value="id_connector2")
    public String id_connector2=UUID.randomUUID().toString();
    
    public Wire(){
        
    }
}
