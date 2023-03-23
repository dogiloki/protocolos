package objects.connectors;

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
    }
    
}
