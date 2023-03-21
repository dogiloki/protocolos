package objects.driver;

import enums.DriverType;
import java.util.HashMap;
import java.util.Map;
import objects.drivers.PC;

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
        this.drivers.put(DriverType.PC,PC.class);
        this.drivers.put(DriverType.MOBILE_PHONE,PC.class);
        this.drivers.put(DriverType.MODEM,PC.class);
        this.drivers.put(DriverType.PRINTER,PC.class);
        this.drivers.put(DriverType.ROUTER,PC.class);
        this.drivers.put(DriverType.SERVER,PC.class);
        this.drivers.put(DriverType.SWITCH,PC.class);
    }
    
    public Class<?> get(DriverType type){
        return this.drivers.get(type);
    }
    
}
