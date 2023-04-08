package objects.wire.connectors;

import enums.ConnectorType;
import enums.EntryType;
import objects.drivers.Driver;

/**
 *
 * @author dogiloki
 */

public class USB extends Connector{
    
    public USB(EntryType type_entry, Driver driver){
        this.driver=driver;
        this.type_entry=type_entry;
        this.type_connector=ConnectorType.USB;
        this.src_icon="/assets/connectors/usb_"+(type_entry==EntryType.MALE?"male.png":"female.png");
    }
    
}
