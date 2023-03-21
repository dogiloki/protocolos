package objects.drivers;

import objects.driver.Driver;
import enums.DriverType;

/**
 *
 * @author dogi_
 */

public class PC extends Driver{
    
    public PC(){
        this.type=DriverType.PC;
        this.src_icon="/assets/drivers/PC.png";
    }
    
}
