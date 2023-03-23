package objects.drivers;

import enums.DriverType;
import java.util.HashMap;
import java.util.Map;
import objects.drivers.MobilePhone;
import objects.drivers.Modem;
import objects.drivers.PC;
import objects.drivers.Printer;
import objects.drivers.Router;
import objects.drivers.Server;
import objects.drivers.Switch;

/**
 *
 * @author dogiloki
 */

public class DriverList{
    
    public static Map<DriverType,Class<?>> drivers(){
        return new DriverList().drivers;
    }
    
    public Map<DriverType,Class<?>> drivers=new HashMap<>();
    
    public DriverList(){
        this.drivers.put(DriverType.MOBILE_PHONE,MobilePhone.class);
        this.drivers.put(DriverType.MODEM,Modem.class);
        this.drivers.put(DriverType.PC,PC.class);
        this.drivers.put(DriverType.PRINTER,Printer.class);
        this.drivers.put(DriverType.ROUTER,Router.class);
        this.drivers.put(DriverType.SERVER,Server.class);
        this.drivers.put(DriverType.SWITCH,Switch.class);
    }
    
    public Class<?> get(DriverType type){
        return this.drivers.get(type);
    }
    
}
