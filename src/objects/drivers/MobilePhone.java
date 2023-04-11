package objects.drivers;

import enums.DriverType;

/**
 *
 * @author dogi_
 */

public class MobilePhone extends Driver{
    
    public MobilePhone(){
        super.setFields();
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.MOBILE_PHONE;
        this.src_icon="/assets/drivers/mobile_phone.png";
    }
    
}
