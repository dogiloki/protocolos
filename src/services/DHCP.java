package services;

import java.util.List;
import multitaks.Function;
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
    
    public void ip(){
        this.IPv4();
        this.IPv6();
    }
    
    private void setServerDHC(Driver driver){
        driver.server_dhcp=this.server_driver.ipv4;
    }
    
    public void IPv4(){
        if(this.server_driver.dhcp==null){
            return;
        }
        List<Driver> child_drivers=this.server_driver.drivers;
        String ip=this.server_driver.ipv4;
        this.server_driver.server_dhcp=this.server_driver.ipv4;
        this.server_driver.subnet_mask="255.255.255.0";
        String[] bits=ip.replaceAll(" ","").split("\\.");
        int index=0;
        while(index<child_drivers.size()){
            Driver driver=child_drivers.get(index);
            driver.subnet_mask="255.255.255.0";
            for(int a=2; a<255; a++){
                String new_ip=bits[0]+"."+bits[1]+"."+bits[2]+"."+a;
                boolean exists=false;
                for(Driver check_driver:child_drivers){
                    if(check_driver.ipv4==null || check_driver.dhcp!=null || new_ip.equals(ip)){
                        break;
                    }
                    if(new_ip.equals(check_driver.ipv4.replaceAll(" ",""))){
                        exists=true;
                        break;
                    }
                }
                if(!exists){
                    driver.ipv4=new_ip;
                    driver.addLog("Recibió IPv4: "+new_ip);
                    this.server_driver.addLog("Asigno IPv4: "+new_ip+" a MAC: "+driver.mac);
                    this.setServerDHC(driver);
                    index++;
                    break;
                }
            }
        }
    }
    
    public void IPv6(){
        if(this.server_driver.dhcp==null){
            return;
        }
        List<Driver> child_drivers=this.server_driver.drivers;
        this.server_driver.ipv6=Function.assign(this.server_driver.ipv6,this.generateIPv6());
        String ip=this.server_driver.ipv6;
        this.server_driver.server_dhcp=this.server_driver.ipv4;
        this.server_driver.subnet_mask="255.255.255.0";
        int index=0;
        while(index<child_drivers.size()){
            Driver driver=child_drivers.get(index);
            driver.subnet_mask="255.255.255.0";
            String new_ip=generateIPv6();
            boolean exists=false;
            for(Driver check_driver:child_drivers){
                if(check_driver.ipv6==null || check_driver.dhcp!=null || new_ip.equals(ip)){
                    break;
                }
                if(new_ip.equals(check_driver.ipv6.replaceAll(" ",""))){
                    exists=true;
                    break;
                }
            }
            if(!exists){
                driver.ipv6=new_ip;
                driver.addLog("Recibió IPv6: "+new_ip);
                this.server_driver.addLog("Asigno IPv6: "+new_ip+" a MAC: "+driver.mac);
                this.setServerDHC(driver);
                index++;
            }
        }
    }
    private String generateIPv6(){
        String ip="";
        for(int a=0; a<8; a++){
            for(int b=0; b<4; b++){
                int num=(int)(Math.random()*(15-1)+1);
                String str=(num<10)?String.valueOf(num):Integer.toHexString(num);
                ip+=str;
            }
            ip+=":";
        }
        return ip.substring(0,ip.length()-1);
    }
    
}
