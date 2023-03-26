package objects.connectors;

import enums.ConnectorType;
import enums.EntryType;

/**
 *
 * @author dogiloki
 */

public class RS_232 extends Connector{
    
    public RS_232(EntryType type_entry){
        this.type_entry=type_entry;
        this.type_connector=ConnectorType.RS_232;
        this.src_icon="/assets/connectors/rs-232_"+(type_entry==EntryType.MALE?"male.png":"female.png");
    }
    
}
