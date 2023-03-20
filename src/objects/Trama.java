package objects;

/**
 *
 * @author dogi_
 */

public class Trama{
    
    public byte[] preamble;
    public byte[] detination_address;
    public byte[] source_address;
    public short type;
    public byte[] data;
    public int fcs;
    public byte[] interframe_grap;
    
    public Trama(){
        
    }
    
}
