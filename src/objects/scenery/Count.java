package objects.scenery;

import enums.DriverType;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Count{
    
    @Key(value="type",type=FieldType.ENUM)
    public DriverType type;
    @Key(value="value")
    public int value;
    
    public Count(){
        
    }
    
    public Count(DriverType type, Integer value){
        this.type=type;
        this.value=value;
    }
    
}
