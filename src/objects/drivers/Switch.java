package objects.drivers;

import enums.DriverType;
import enums.EntryType;
import objects.wire.connectors.RJ45;

/**
 *
 * @author dogi_
 */

public class Switch extends Driver{
    public Switch(){
        super.setFields();
        Switch.count++;
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.SWITCH;
        this.src_icon="/assets/drivers/switch.png";
        this.name=this.type.toString();
        this.host=this.name;
    }
    
    @Override
    public void setConnectors(){
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
        this.connectors.add(new RJ45(EntryType.FEMALE,this));
    }
    
}
