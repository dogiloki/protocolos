package protocols;

import java.util.List;
import objects.drivers.Driver;

/**
 *
 * @author dogi_
 */

public class DHCP{
    
    public Driver server_driver;
    
    public DHCP(Driver server_driver){
        this.server_driver=server_driver;
    }
    
    public static DHCP aim(Driver server_driver){
        return new DHCP(server_driver);
    }
    
    public void all(List<Driver> child_drivers){
        this.IPv4(child_drivers);
    }
    
    // IPv4
    public void IPv4(List<Driver> child_drivers){
        String ip=this.server_driver.ipv4;
        String[] bits=ip.replaceAll(" ","").split("\\.");
        int index=0;
        while(index<child_drivers.size()){
            Driver driver=child_drivers.get(index);
            for(int a=2; a<255; a++){
                String new_ip=bits[0]+"."+bits[1]+"."+bits[2]+"."+a;
                boolean exists=false;
                for(Driver check_driver:child_drivers){
                    if(check_driver.ipv4==null || check_driver.dhcp || new_ip==ip){
                        break;
                    }
                    if(new_ip.equals(check_driver.ipv4.replaceAll(" ",""))){
                        exists=true;
                        break;
                    }
                }
                if(!exists){
                    driver.ipv4=new_ip;
                    index++;
                    break;
                }
            }
        }
    }
    
    // IPv6 - 2001:0db8:85a3:0000:0000:8a2e:0370:7334 (si hay todos 0 se puede abreviar con: 2001:db8:85a3::8a2e:370:7334)
    
    
}
