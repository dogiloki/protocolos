package objects.wire.connectors;

import com.google.gson.annotations.Expose;
import enums.ConnectorType;
import enums.EntryType;
import objects.drivers.Driver;
import objects.scenery.Entity;

/**
 *
 * @author dogiloki
 */

public class Connector extends Entity{
    
    @Expose
    public EntryType type_entry;
    
    @Expose
    public ConnectorType type_connector;
    
    @Expose
    public boolean connected=false;
    
    public Driver driver;
    
    public Connector(){
        
    }
    
    public Connector(Driver driver){
        this.driver=driver;
    }
    
}
