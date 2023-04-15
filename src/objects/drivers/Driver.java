package objects.drivers;

import enums.ConnectorType;
import enums.DriverType;
import enums.EtherType;
import enums.PackageType;
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
import protocols.PackageEther;
import protocols.SequenceNumber;
import protocols.mail.MailServer;
import protocols.mail.pop.ClientPOP;
import protocols.mail.pop.ServerPOP;
import protocols.mail.smtp.ClientSMTP;
import protocols.mail.smtp.ServerSMTP;

/**
 *
 * @author dogiloki
 */

public class Driver extends Entity implements BaseDriver{
    
    @Key(value="count")
    public static int count=0;
    
    //@Config(label="Nombre",box=BoxType.TEXT) @Key(value="name")
    //public String name;
    
    @Config(label="Host",box=BoxType.TEXT) @Key(value="host")
    public String host;
    
    @Config(label="IPv4",box=BoxType.TEXT) @Key(value="ipv4")
    public String ipv4;
    
    @Config(label="IPv6",box=BoxType.TEXT) @Key(value="ipv6")
    public String ipv6;
    
    @Config(label="Máscara de subred",box=BoxType.TEXT) @Key(value="subnet_mask")
    public String subnet_mask;
    
    @Config(label="Servidor DHCP",box=BoxType.TEXT) @Key(value="server_dhcp")
    public String server_dhcp;
    
    @Config(id="ipv4_public",label="IPv4 pública",box=BoxType.TEXT) @Key(value="ipv4_public")
    public String ipv4_public;
    
    @Config(label="MAC",box=BoxType.TEXT) @Key(value="MAC")
    public String mac;
    
    @Key(value="connectors",type=FieldType.LIST)
    public List<Connector> connectors=new ArrayList<>();
    
    @Key(value="type",type=FieldType.ENUM)
    public DriverType type;
    
    // Dispositivos a los que esta conectado
    public List<Driver> drivers=new ArrayList<>();
    
    public SequenceNumber sequence_number=new SequenceNumber();
    // Identificar si el dispositivo esta envíando un paquete
    public Map<Connector,List<PackageEther>> sending_packages=new HashMap<>();
    // Identificar paquetes enviados por el dipositivo
    public Map<Connector,List<PackageEther>> sent_packages=new HashMap<>();
    // Identificar paquetes recibidos por el dipositivo
    public Map<Connector,List<PackageEther>> receiving_packages=new HashMap<>();
    
    // Servicios
    
    public DHCP dhcp;
    public ClientSMTP client_smtp;
    public ServerSMTP server_smtp;
    public MailServer mail_server;
    public ClientPOP client_pop;
    public ServerPOP server_pop;
    
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
            for(int b=0; b<3; b++){
                int num=(int)(Math.random()*9);
                ip+=String.valueOf(num);
            }
            ip+=".";
        }
        return ip.substring(0,ip.length()-1).toUpperCase();
    }
    
    public Driver getDriverDHCP(){
        if(this.server_dhcp.equals(this.ipv4)){
            return this;
        }
        for(Driver driver:this.drivers){
            if(driver.ipv4.equals(this.server_dhcp)){
                return driver;
            }
        }
        return null;
    }
    
    public Driver getDriverMac(String mac){
        for(Driver driver:this.drivers){
            if(driver.mac.equals(mac)){
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
    
    public void addSendingPackage(Connector connector, PackageEther pack){
        List<PackageEther> packs=this.sending_packages.get(connector);
        if(packs==null){
            packs=new ArrayList<>();
        }
        packs.add(pack);
        this.sending_packages.put(connector,packs);
        this.addLog("Un paquete enviandose...");
    }
    
    public void addReceivingPackage(Connector connector, PackageEther pack){
        List<PackageEther> packs=this.receiving_packages.get(connector);
        if(packs==null){
            packs=new ArrayList<>();
        }
        packs.add(pack);
        this.receiving_packages.put(connector,packs);
        this.addLog("Un paquete recibido");
    }
    
    public void addSentPackage(Connector connector, PackageEther pack){
        List<PackageEther> packs=this.sent_packages.get(connector);
        if(packs==null){
            packs=new ArrayList<>();
        }
        packs.add(pack);
        this.sent_packages.put(connector,packs);
        this.addLog("Un paquete enviado");
    }
    
    public PackageEther createPackage(int sequence_number, PackageType package_type, EtherType type_ether, String driver_destination, Object message){
        return this._createPackage(sequence_number,package_type,type_ether,driver_destination,message);
    }
    public PackageEther createPackage(PackageType package_type, EtherType type_ether, String driver_destination, Object message){
        return this._createPackage(null,package_type,type_ether,driver_destination,message);
    }
    private PackageEther _createPackage(Integer sequence_number, PackageType package_type, EtherType type_ether, String driver_destination, Object message){
        String driver_source;
        if(sequence_number==null){
            sequence_number=this.sequence_number.getNextSequenceNumber();
        }
        switch(type_ether){
            case IPv4: driver_source=this.mac; break;
            case TCP: driver_source=this.ipv4_public; break;
            default: driver_source=null; break;
        }
        PackageEther pack=new PackageEther(sequence_number,package_type,type_ether,driver_source,driver_destination,message);
        this.addSendingPackage(this.getConnector(ConnectorType.RJ45),pack);
        return pack;
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
                this.server_smtp=new ServerSMTP();
                this.server_pop=new ServerPOP();
                this.mail_server=new MailServer();
                this.server_smtp.connect(this.mail_server);
                this.server_pop.connect(mail_server);
                break;
            }
            default:{
                this.client_smtp=new ClientSMTP();
                this.client_pop=new ClientPOP();
                break;
            }
        }
    }

    @Override
    public void setConnectors(){
        
    }
    
}
