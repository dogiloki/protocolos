package services;

import java.util.HashMap;
import java.util.Map;
import objects.drivers.Driver;

/**
 *
 * @author dogiloki
 */

public class DNS{
    
    private static DNS instance=null;
    
    private Map<String,Driver> drives=new HashMap<>();
    
    private DNS(){
        
    }
    
    public static DNS singletong(){
        if(DNS.instance==null){
            DNS.instance=new DNS();
        }
        return DNS.instance;
    }
    
    public static Driver get(String ip){
        return DNS.singletong().drives.get(ip);
    }
    
    public static void put(String ip, Driver driver){
        DNS.singletong().drives.put(ip,driver);
    }
    
}
