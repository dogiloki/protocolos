package objects.drivers;

import enums.DriverType;

/**
 *
 * @author dogi_
 */

public class Server extends Driver{
    
    public Server(){
        super.setFields();
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.SERVER;
        this.src_icon="/assets/drivers/server.png";
    }
    
}
