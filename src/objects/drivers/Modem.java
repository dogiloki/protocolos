package objects.drivers;

import enums.DriverType;

/**
 *
 * @author dogi_
 */

public class Modem extends Driver{
    
    public Modem(){
        super.setFields();
        Modem.count++;
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.MODEM;
        this.src_icon="/assets/drivers/modem.png";
        this.name=this.type.toString();
        this.host=this.name;
    }
    
}
