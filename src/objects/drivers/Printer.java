package objects.drivers;

import enums.DriverType;

/**
 *
 * @author dogi_
 */

public class Printer extends Driver{
    
    public Printer(){
        Printer.count++;
    }
    
    @Override
    public void setFields(){
        this.type=DriverType.PRINTER;
        this.src_icon="/assets/drivers/printer.png";
        this.name=this.type.toString();
        this.host=this.name;
    }
    
}
