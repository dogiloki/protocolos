package protocols;

import enums.EtherType;
import objects.drivers.Driver;

/**
 *
 * @author dogi_
 */

public class Header{
    
    public String preamble;
    public Driver source_driver;
    public Driver destination_driver;
    public EtherType type;
    
    public Header(){
        
    }
    
}
