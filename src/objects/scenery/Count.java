package objects.scenery;

import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;
import enums.DriverType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Count{
    
    @Expose
    public DriverType type;
    @Expose
    public int value;
    
    public Count(){
        
    }
    
    public Count(DriverType type, Integer value){
        this.type=type;
        this.value=value;
    }
    
}
