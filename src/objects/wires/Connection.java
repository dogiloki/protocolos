package objects.wires;

import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;
import objects.wire.connectors.Connector;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Connection{
    
    @Key(value="connector_male",type=FieldType.CLASS)
    public Connector connector_male;
    @Key(value="connector_female",type=FieldType.CLASS)
    public Connector connector_female;
    
    public Connection(){
        
    }
    
}
