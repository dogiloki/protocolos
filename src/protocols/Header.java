package protocols;

import enums.EtherType;

/**
 *
 * @author dogi_
 */

public class Header{
    
    public String preamble;
    public String source_driver;
    public String destination_driver;
    public EtherType type;
    
    public Header(){
        
    }
    
}
