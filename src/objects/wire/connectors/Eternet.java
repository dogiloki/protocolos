package objects.wire.connectors;

import enums.ConnectorType;
import enums.EntryType;
import java.awt.Color;

/**
 *
 * @author dogiloki
 */
public class Eternet extends Connector{
    
    public Eternet(EntryType type_entry){
        this.type_entry=type_entry;
        this.type_connector=ConnectorType.ETERNET;
        this.src_icon="/assets/connectors/eternet_"+(type_entry==EntryType.MALE?"male.png":"female.png");
        this.color=Color.ORANGE.getRGB();
    }
    
}
