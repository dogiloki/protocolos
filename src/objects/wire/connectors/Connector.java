package objects.wire.connectors;

import enums.ConnectorType;
import enums.EntryType;
import multitaks.annotations.directory.Key;
import multitaks.enums.FieldType;
import objects.drivers.Driver;
import objects.scenery.Entity;

/**
 *
 * @author dogiloki
 */

public class Connector extends Entity{
    
    @Key(value="type_entry",type=FieldType.ENUM)
    public EntryType type_entry;
    
    @Key(value="type_connector",type=FieldType.ENUM)
    public ConnectorType type_connector;
    
    @Key(value="connected")
    public boolean connected=false;
    
    public Driver driver;
    
    public Connector(){
        
    }
    
    public Connector(Driver driver){
        this.driver=driver;
    }
    
}
