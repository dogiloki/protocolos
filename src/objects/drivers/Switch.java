package objects.drivers;

import enums.DriverType;
import objects.driver.Driver;

/**
 *
 * @author dogi_
 */

public class Switch extends Driver{
    
    public Switch(){
        this.type=DriverType.PC;
        this.src_icon="/assets/drivers/switch.png";
    }
    
}
