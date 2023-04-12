package objects.scenery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Execute;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;
import multitaks.enums.FieldType;
import objects.drivers.Driver;
import objects.wires.Wire;
import objects.wire.connectors.Connector;
import objects.wires.Connection;
import services.DNS;

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
    @Key(value="counts",type=FieldType.LIST)
    public List<Count> counts=new ArrayList<>();
    
    // Almacenar conector con ID
    //public Map<String,Connector> connectors=new HashMap<>();
    
    public Scenery(){
        
    }
    
    // Almacenar dispositivo al que pertence un conector
    public Map<String,Driver> connector_driver=new HashMap<>();
    public Map<String,Connector> connectors=new HashMap<>();
    
    @Execute
    public void setConnections(){
        this.drivers.forEach((driver)->{
            DNS.put(driver.ipv4_public,driver);
            driver.connectors.forEach((connector)->{
                this.connector_driver.put(connector.id,driver);
                this.connectors.put(connector.id,connector);
            });
        });
        this.drivers.forEach((driver)->{
            driver.drivers=this.listDrivers(driver);
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
            connector.driver=root_driver;
            if(connector.connected){
                this.wires.forEach((wire)->{
                    Connection connection1=wire.connection1;
                    Connection connection2=wire.connection2;
                    Connector connector1=connection1.connector_female;
                    Connector connector2=connection2.connector_female;
                    if(connector.id.equals(connector1.id) || connector.id.equals(connector2.id)){
                        Driver driver1=this.connector_driver.get(connector1.id);
                        Driver driver2=this.connector_driver.get(connector2.id);
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
                    wire.connection1.connector_female=this.connectors.get(connector1.id);
                    wire.connection2.connector_female=this.connectors.get(connector2.id);
                });
            }
        });
        return drivers;
    }
    
}
