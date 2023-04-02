package objects.wire.connectors;

import objects.wire.connectors.Connector;
import enums.ConnectorType;
import enums.EntryType;

/**
 *
 * @author dogiloki
 */

public class USB extends Connector{
    
    public USB(EntryType type_entry){
        this.type_entry=type_entry;
        this.type_connector=ConnectorType.USB;
        this.src_icon="/assets/connectors/usb_"+(type_entry==EntryType.MALE?"male.png":"female.png");
    }
    
}
