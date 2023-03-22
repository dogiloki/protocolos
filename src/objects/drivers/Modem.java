package objects.drivers;

import enums.DriverType;
import objects.driver.Driver;

/**
 *
 * @author dogi_
 */

public class Modem extends Driver{
    
    public Modem(){
        this.type=DriverType.MODEM;
        this.src_icon="/assets/drivers/modem.png";
    }
    
}
