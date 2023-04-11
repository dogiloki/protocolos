package objects.drivers;

import enums.DriverType;
import enums.EntryType;
import objects.wire.connectors.RJ45;
import objects.wire.connectors.USB;

/**
 *
 * @author dogi_
 */

public class Router extends Driver{
    
    public Router(){
        super.setFields();
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.ROUTER;
        this.src_icon="/assets/drivers/router.png";
    }
    
    @Override
    public void setConnectors(){
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new USB(EntryType.FEMALE,this));
    }
    
}
