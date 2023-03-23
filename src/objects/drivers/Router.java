package objects.drivers;

import enums.DriverType;

/**
 *
 * @author dogi_
 */

public class Router extends Driver{
    
    public Router(){
        this.type=DriverType.ROUTER;
        this.src_icon="/assets/drivers/router.png";
    }
    
}
