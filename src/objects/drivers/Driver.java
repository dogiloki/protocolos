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
import multitaks.annotations.directory.Execute;
import protocols.DHCP;

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
    
    @Config(label="Máscara de subred",box=BoxType.TEXT) @Key(value="subnet_mask")
    public String subnet_mask;
    
    @Config(label="MAC",box=BoxType.TEXT) @Key(value="MAC")
    public String mac;
    
    @Key(value="connectors",type=FieldType.LIST)
    public List<Connector> connectors=new ArrayList<>();
    
    @Key(value="type",type=FieldType.ENUM)
    public DriverType type;
    
    // Protocolos
    @Key(value="dhcp")
    public boolean dhcp=false;
    
    public boolean status=true;
    
    public Driver(boolean increase){
        if(increase){
            Driver.count++;
        }
        this.setConnectors();
        this.setFields();
    }
    
    public Driver(){
        Driver.count++;
        this.setConnectors();
        this.setFields();
    }

    @Override
    public void on(){
        this.status=true;
    }

    @Override
    public void off(){
        this.status=false;
    }

    @Override @Execute
    public void setFields(){
        if(this.type==null){
            return;
        }
        switch(this.type){
            case ROUTER:{
                if(this.ipv4==null){
                    this.ipv4="192.168."+Router.count+".1";
                }
                this.dhcp=true;
                break;
            }
        }
    }

    @Override
    public void setConnectors(){
        
    }
    
}
