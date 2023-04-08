package objects.wire.connectors;

import enums.ConnectorType;
import enums.EntryType;
import objects.drivers.Driver;

/**
 *
 * @author dogiloki
 */
public class RJ45 extends Connector{
    
    public RJ45(EntryType type_entry, Driver driver){
        this.driver=driver;
        this.type_entry=type_entry;
        this.type_connector=ConnectorType.RJ45;
        this.src_icon="/assets/connectors/rj45_"+(type_entry==EntryType.MALE?"male.png":"female.png");
    }
    
}
