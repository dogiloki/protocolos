package objects.wire.connectors;

import enums.ConnectorType;
import enums.EntryType;
import java.awt.Color;
import java.util.UUID;
import multitaks.annotations.directory.Key;
import multitaks.enums.FieldType;
import objects.scenery.Entity;

/**
 *
 * @author dogiloki
 */

public class Connector extends Entity{
    
    @Key(value="id")
    public String id=UUID.randomUUID().toString();
    
    @Key(value="type_entry",type=FieldType.ENUM)
    public EntryType type_entry;
    
    @Key(value="type_connector",type=FieldType.ENUM)
    public ConnectorType type_connector;
    
    @Key(value="connected")
    public boolean connected=false;
    
    @Key(value="Color")
    public int color;
    
    public Connector(){
        
    }
    
}
