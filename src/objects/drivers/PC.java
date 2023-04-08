package objects.drivers;

import enums.DriverType;
import enums.EntryType;
import objects.wire.connectors.Eternet;
import objects.wire.connectors.RS_232;
import objects.wire.connectors.USB;

/**
 *
 * @author dogi_
 */

public class PC extends Driver{
    
    public PC(){
        PC.count++;
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.PC;
        this.src_icon="/assets/drivers/PC.png";
        this.name=this.type.toString();
        this.host=this.name;
    }
    
    @Override
    public void setConnectors(){
        this.connectors.add(new Eternet(EntryType.FEMALE));
        this.connectors.add(new RS_232(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
    }
    
}
