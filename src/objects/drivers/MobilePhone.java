package objects.drivers;

import enums.DriverType;

/**
 *
 * @author dogi_
 */

public class MobilePhone extends Driver{
    
    public MobilePhone(){
        MobilePhone.count++;
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.MOBILE_PHONE;
        this.src_icon="/assets/drivers/mobile_phone.png";
        this.name=this.type.toString();
        this.host=this.name;
    }
    
}
