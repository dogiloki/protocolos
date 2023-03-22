package objects.drivers;

import enums.DriverType;
import objects.driver.Driver;

/**
 *
 * @author dogi_
 */

public class Server extends Driver{
    
    public Server(){
        this.type=DriverType.SERVER;
        this.src_icon="/assets/drivers/server.png";
    }
    
}
