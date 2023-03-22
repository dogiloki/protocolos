package objects.drivers;

import enums.DriverType;
import objects.driver.Driver;

/**
 *
 * @author dogi_
 */

public class Printer extends Driver{
    
    public Printer(){
        this.type=DriverType.PRINTER;
        this.src_icon="/assets/drivers/printer.png";
    }
    
}
