package objects.drivers;

import enums.DriverType;
import enums.EntryType;
import multitaks.annotations.directory.Directory;
import multitaks.enums.DirectoryType;
import objects.connectors.RJ45;
import objects.connectors.RS_232;
import objects.connectors.USB;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class PC extends Driver{
    
    public PC(){
        this.type=DriverType.PC;
        this.src_icon="/assets/drivers/PC.png";
        this.setConnectors();
    }
    
    private void setConnectors(){
        this.connectors.add(new RJ45(EntryType.FEMALE));
        this.connectors.add(new RS_232(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
    }
    
}
