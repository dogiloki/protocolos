package objects.drivers;

import enums.DriverType;
import java.util.List;
import multitaks.annotations.directory.Key;
import multitaks.enums.FieldType;
import objects.wire.connectors.Connector;
import objects.drivers.anotations.Config;
import objects.drivers.enums.BoxType;
import objects.scenery.Entity;
import interfaces.BaseDriver;
import java.util.ArrayList;

/**
 *
 * @author dogiloki
 */

public class Driver extends Entity implements BaseDriver{
    
    public static int count=-1;
    
    @Config(label="Nombre",box=BoxType.TEXT) @Key(value="name")
    public String name;
    
    @Config(label="Host",box=BoxType.TEXT) @Key(value="host")
    public String host;
    
    @Config(label="IPv4",box=BoxType.TEXT) @Key(value="ipv4")
    public String ipv4;
    
    @Config(label="IPv6",box=BoxType.TEXT) @Key(value="ipv6")
    public String ipv6;
    
    @Config(label="MAC",box=BoxType.TEXT) @Key(value="MAC")
    public String mac;
    
    @Key(value="connectors",type=FieldType.LIST)
    public List<Connector> connectors=new ArrayList<>();
    
    @Key(value="type",type=FieldType.ENUM)
    public DriverType type;
    
    public boolean status=true;
    
    public Driver(boolean increase){
        if(increase){
            Driver.count++;
        }
        this.setConnectors();
    }
    
    public Driver(){
        Driver.count++;
        this.setConnectors();
    }

    @Override
    public void on(){
        this.status=true;
    }

    @Override
    public void off(){
        this.status=false;
    }

    @Override
    public void setFields(){
        
    }

    @Override
    public void setConnectors(){
        
    }
    
}
