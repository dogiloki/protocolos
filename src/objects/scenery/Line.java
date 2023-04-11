package objects.scenery;

import java.util.UUID;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Line{
    
    @Key(value="id")
    public String id=UUID.randomUUID().toString();
    @Key(value="index_x")
    public int index_x;
    @Key(value="index_y")
    public int index_y;
    @Key(value="end_x")
    public int end_x;
    @Key(value="end_y")
    public int end_y;
    
    public void Line(){
        
    }
    
}
