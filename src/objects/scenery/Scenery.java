package objects.scenery;

import java.util.ArrayList;
import java.util.List;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;
import objects.drivers.Driver;

/**
 *
 * @author dogiloki
 */

@Directory(type=DirectoryType.JSON)
public class Scenery{
    
    @Key(value="drivers",type=FieldType.LIST)
    public List<Driver> drivers=new ArrayList<>();
    
    public Scenery(){
        
    }
    
}
