package objects.drivers;

import enums.DriverType;
import enums.EntryType;
import objects.wire.connectors.Eternet;
import objects.wire.connectors.USB;

/**
 *
 * @author dogi_
 */

public class Router extends Driver{
    
    public static int count=-1;
    
    public Router(){
        super.setFields();
        Router.count++;
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.ROUTER;
        this.src_icon="/assets/drivers/router.png";
        this.name=this.type.toString();
        this.host=this.name;
    }
    
    @Override
    public void setConnectors(){
        this.connectors.add(new Eternet(EntryType.FEMALE));
        this.connectors.add(new Eternet(EntryType.FEMALE));
        this.connectors.add(new Eternet(EntryType.FEMALE));
        this.connectors.add(new Eternet(EntryType.FEMALE));
        this.connectors.add(new Eternet(EntryType.FEMALE));
        this.connectors.add(new Eternet(EntryType.FEMALE));
        this.connectors.add(new USB(EntryType.FEMALE));
    }
    
}
