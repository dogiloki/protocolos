package objects.scenery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;
import objects.drivers.Driver;
import objects.wire.Wire;
import objects.wire.connectors.Connector;

/**
 *
 * @author dogiloki
 */

@Directory(type=DirectoryType.JSON)
public class Scenery{
    
    @Key(value="drivers",type=FieldType.LIST)
    public List<Driver> drivers=new ArrayList<>();
    @Key(value="wires",type=FieldType.LIST)
    public List<Wire> wires=new ArrayList<>();
    
    // Almacenar dispositivo al que pertence un conector
    public Map<String,Driver> connector_wire=new HashMap<>();
    
    // Almacenar dispositivo con ID
    public Map<String,Connector> connectors=new HashMap<>();
    
    public Scenery(){
        
    }
    
    public void setConnectors(){
        this.drivers.forEach((driver)->{
            driver.connectors.forEach((connector)->{
                this.connector_wire.put(connector.id,driver);
                this.connectors.put(connector.id,connector);
            });
        });
    }
    
}
