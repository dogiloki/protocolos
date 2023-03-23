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
    }
    
}
