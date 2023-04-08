package objects.drivers;

import enums.DriverType;
import multitaks.annotations.directory.Key;

/**
 *
 * @author dogi_
 */

public class Server extends Driver{
    
    public Server(){
        Server.count++;
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.SERVER;
        this.src_icon="/assets/drivers/server.png";
        this.name=this.type.toString();
        this.host=this.name;
    }
    
}
