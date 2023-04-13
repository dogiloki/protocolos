package protocols;

import java.util.Random;

/**
 *
 * @author dogi_
 */

public class SequenceNumber{
    
    private final Random random=new Random();
    private int sequence_number=random.nextInt(100);
    
    public synchronized int getNextSequenceNumber(){
        return (this.sequence_number+1%100);
    } 
    
}
