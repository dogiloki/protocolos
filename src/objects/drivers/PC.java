package objects.drivers;

import objects.driver.Driver;
import enums.DriverType;
import objects.scenery.Entity;

/**
 *
 * @author dogi_
 */

public class PC extends Driver{
    
    public PC(){
        this.type=DriverType.PC;
        this.width=50;
        this.height=50;
        this.src_icon="/assets/drivers/PC.png";
    }
    
}
