package objects.connectors;

import enums.ConnectorType;
import enums.EntryType;

/**
 *
 * @author dogiloki
 */
public class RJ45 extends Connector{
    
    public RJ45(EntryType type_entry){
        this.type_entry=type_entry;
        this.type_connector=ConnectorType.RJ45;
        this.src_icon="/assets/connectors/rj45_"+(type_entry==EntryType.MALE?"male.png":"female.png");
    }
    
}
