package objects.wires;

import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;
import objects.wire.connectors.Connector;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Connection{
    
    @Expose
    public Connector connector_male;
    @Expose
    public Connector connector_female;
    
    public Connection(){
        
    }
    
}
