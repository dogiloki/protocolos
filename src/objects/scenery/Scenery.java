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
import objects.wires.Wire;
import objects.wire.connectors.Connector;

/**
 *
 * @author dogiloki
 */

@Directory(type=DirectoryType.JSON)
public class Scenery{
    
    public interface Callback{
        public void execute(Driver driver);
    }
    
    @Key(value="drivers",type=FieldType.LIST)
    public List<Driver> drivers=new ArrayList<>();
    @Key(value="wires",type=FieldType.LIST)
    public List<Wire> wires=new ArrayList<>();
    
    // Almacenar dispositivo al que pertence un conector
    public Map<String,Driver> connector_wire=new HashMap<>();
    
    // Almacenar conector con ID
    public Map<String,Connector> connectors=new HashMap<>();
    
    public Scenery(){
        
    }
    
    /*
    @Execute
    public void setConnections(){
        this.drivers.forEach((driver)->{
            driver.connectors.forEach((connector)->{
                this.connector_wire.put(connector.id,driver);
                this.connectors.put(connector.id,connector);
            });
        });
        this.drivers.forEach((driver)->{
            this.listDrivers(driver,(child_driver)->{
                driver.drivers.add(child_driver);
            });
        });
    }
    
    public List<Driver> listDrivers(Driver root_driver){
        return this._listDrivers(root_driver,null);
    }
    public List<Driver> listDrivers(Driver root_driver, Callback action){
        return this._listDrivers(root_driver,action);
    }
    private List<Driver> _listDrivers(Driver root_driver, Callback action){
        List<Driver> drivers=new ArrayList<>();
        root_driver.connectors.forEach((connector)->{
            if(connector.connected){
                this.wires.forEach((wire)->{
                    if(wire.id_connector1.equals(connector.id) || wire.id_connector2.equals(connector.id)){
                        Driver driver1=this.connector_wire.get(wire.id_connector1);
                        Driver driver2=this.connector_wire.get(wire.id_connector2);
                        if(driver1!=root_driver){
                            drivers.add(driver1);
                            if(action!=null){
                                action.execute(driver1);
                            }
                        }
                        if(driver2!=root_driver){
                            drivers.add(driver2);
                            if(action!=null){
                                action.execute(driver2);
                            }
                        }
                    }
                });
            }
        });
        return drivers;
    }
    */
    
}
