package objects.wires;

import enums.EntryType;
import enums.WireType;
import interfaces.BaseWire;
import multitaks.annotations.directory.Key;
import multitaks.enums.FieldType;
import objects.drivers.Driver;
import objects.scenery.Line;
import objects.wire.connectors.Connector;

/**
 *
 * @author dogi_
 */

public class Wire extends Line implements BaseWire{
    
    @Key(value="type_wire",type=FieldType.ENUM)
    public WireType type_wire;
    @Key(value="connector1",type=FieldType.CLASS)
    public Connector connector1;
    @Key(value="connector2",type=FieldType.CLASS)
    public Connector connector2;
    @Key(value="connection1",type=FieldType.CLASS)
    public Connection connection1;
    @Key(value="connection2",type=FieldType.CLASS)
    public Connection connection2;
    @Key(value="color")
    public int color;
    
    public Wire(){
        
    }
    
    public Driver getDriver1(){
        if(this.connection1==null || this.connection1.connector_female==null){
            return null;
        }
        return this.connection1.connector_female.driver;
    }
    
    public Driver getDriver2(){
        if(this.connection2==null || this.connection2.connector_female==null){
            return null;
        }
        return this.connection2.connector_female.driver;
    }
    
    @Override
    public boolean setConnection1(Connector connector_female){
        if(this.connector1==null){
            return false;
        }
        if(connector_female.type_entry!=EntryType.FEMALE && this.connector1.type_entry==EntryType.MALE){
            return false;
        }else
        if(connector_female.type_connector!=this.connector1.type_connector){
            return false;
        }
        Connection connection=new Connection();
        connection.connector_male=this.connector1;
        connection.connector_female=connector_female;
        this.connection1=connection;
        return true;
    }
    
    @Override
    public boolean setConnection2(Connector connector_female){
        if(this.connector2==null){
            return false;
        }
        if(connector_female.type_entry!=EntryType.FEMALE && this.connector2.type_entry==EntryType.MALE){
            return false;
        }else
        if(connector_female.type_connector!=this.connector2.type_connector){
            return false;
        }
        Connection connection=new Connection();
        connection.connector_male=this.connector2;
        connection.connector_female=connector_female;
        this.connection2=connection;
        return true;
    }

    @Override
    public Connection getConnection1(){
        return this.connection1;
    }

    @Override
    public Connection getConnection2(){
        return this.connection2;
    }

    @Override
    public boolean send(Driver driver1, Driver driver2){
        return false;
    }
    
    
    
}
