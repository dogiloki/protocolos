package objects.drivers;

import enums.DriverType;
import enums.EntryType;
import objects.wire.connectors.RJ45;

/**
 *
 * @author dogi_
 */

public class Server extends Driver{
    
    public Server(){
        super.setFields();
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.SERVER;
        this.src_icon="/assets/drivers/server.png";
    }
    
    @Override
    public void setConnectors(){
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
    }
    
}
