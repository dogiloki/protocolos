package objects.drivers;

import enums.DriverType;
import java.util.ArrayList;
import java.util.List;
import multitaks.annotations.directory.Key;
import objects.wire.connectors.Connector;
import objects.drivers.anotations.Config;
import objects.drivers.enums.BoxType;
import objects.scenery.Entity;

/**
 *
 * @author dogiloki
 */

public class Driver extends Entity{
    
    @Config(label="Nombre",box=BoxType.TEXT) @Key(value="name")
    public String name;
    
    @Config(label="IPv4",box=BoxType.TEXT) @Key(value="ipv4")
    public String ipv4;
    
    @Config(label="IPv6",box=BoxType.TEXT) @Key(value="ipv6")
    public String ipv6;
    
    @Config(label="MAC",box=BoxType.TEXT) @Key(value="MAC")
    public String mac;
    
    public DriverType type;
    public List<Connector> connectors=new ArrayList<>();
    
    public Driver(){
        
    }
    
}
