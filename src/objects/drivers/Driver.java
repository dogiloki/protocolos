package objects.drivers;

import enums.ConnectorType;
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
import java.util.HashMap;
import java.util.Map;
import multitaks.Function;
import multitaks.annotations.directory.Execute;
import protocols.DHCP;

/**
 *
 * @author dogiloki
 */

public class Driver extends Entity implements BaseDriver{
    
    @Key(value="count")
    public static int count=0;
    
    @Config(label="Nombre",box=BoxType.TEXT) @Key(value="name")
    public String name;
    
    @Config(label="Host",box=BoxType.TEXT) @Key(value="host")
    public String host;
    
    @Config(label="IPv4",box=BoxType.TEXT) @Key(value="ipv4")
    public String ipv4;
    
    @Config(label="IPv6",box=BoxType.TEXT) @Key(value="ipv6")
    public String ipv6;
    
    @Config(label="IPv4 pública",box=BoxType.TEXT) @Key(value="ipv4_public")
    public String ipv4_public;
    
    @Config(label="Máscara de subred",box=BoxType.TEXT) @Key(value="subnet_mask")
    public String subnet_mask;
    
    @Config(label="Servidor DHCP",box=BoxType.TEXT) @Key(value="server_dhcp")
    public String server_dhcp;
    
    @Config(label="MAC",box=BoxType.TEXT) @Key(value="MAC")
    public String mac;
    
    @Key(value="connectors",type=FieldType.LIST)
    public List<Connector> connectors=new ArrayList<>();
    
    @Key(value="type",type=FieldType.ENUM)
    public DriverType type;
    
    // Dispositivos a los que esta conectado
    public List<Driver> drivers=new ArrayList<>();
    
    // Identificar si el dispositivo esta envíando un paquete
    public Map<Connector,List<protocols.Package>> sending_packages=new HashMap<>();
    // Identificar si el dispositivo esta reciviendo un paquete
    public Map<Connector,List<protocols.Package>> receiving_packages=new HashMap<>();
    
    // Protocolos
    
    public DHCP dhcp;
    
    public boolean status=true;
    
    public Driver(boolean increase){
        this.setConnectors();
        this.setFields();
    }
    
    public Driver(){
        this.setConnectors();
        this.setFields();
    }
    
    public String generateMac(){
        String mac="";
        for(int a=0; a<6; a++){
            for(int b=0; b<2; b++){
                int num=(int)(Math.random()*(15-1)+1);
                String str=(num<10)?String.valueOf(num):Integer.toHexString(num);
                mac+=str;
            }
            mac+=":";
        }
        return mac.substring(0,mac.length()-1).toUpperCase();
    }
    
    public String generateIPv4Public(){
        String ip="";
        for(int a=0; a<4; a++){
            for(int b=0; b<2; b++){
                int num=(int)(Math.random()*9);
                mac+=String.valueOf(num);
            }
            mac+=".";
        }
        return mac.substring(0,mac.length()-1).toUpperCase();
    }
    
    public Driver getDriverDHCP(){
        for(Driver driver:this.drivers){
            if(driver.ipv4.equals(this.server_dhcp)){
                return driver;
            }
        }
        return null;
    }
    
    public Connector getConnector(ConnectorType type_connector){
        for(Connector connector:this.connectors){
            if(connector.connected && connector.type_connector==type_connector){
                return connector;
            }
        }
        return null;
    }
    
    public void addSendingPackage(Connector connector, protocols.Package pack){
        List<protocols.Package> packs=this.sending_packages.get(connector);
        if(packs==null){
            packs=new ArrayList<>();
        }
        packs.add(pack);
        this.sending_packages.put(connector,packs);
    }
    
    public void addReceivingPackage(Connector connector, protocols.Package pack){
        List<protocols.Package> packs=this.receiving_packages.get(connector);
        if(packs==null){
            packs=new ArrayList<>();
        }
        packs.add(pack);
        this.receiving_packages.put(connector,packs);
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
        this.mac=Function.assign(this.mac,this.generateMac());
        this.ipv4_public=Function.assign(this.ipv4_public,this.generateIPv4Public());
        if(this.type==null){
            return;
        }
        switch(this.type){
            case ROUTER:{
                this.dhcp=DHCP.aim(this);
                break;
            }
            case SERVER:{
                
                break;
            }
        }
    }

    @Override
    public void setConnectors(){
        
    }
    
}
