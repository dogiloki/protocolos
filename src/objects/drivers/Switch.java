package objects.drivers;

import enums.DriverType;

/**
 *
 * @author dogi_
 */

public class Switch extends Driver{
    public Switch(){
        Switch.count++;
        this.setFields();
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.SWITCH;
        this.src_icon="/assets/drivers/switch.png";
        this.name=this.type.toString();
        this.host=this.name;
    }
    
}
