package protocols;

import java.util.Random;

/**
 *
 * @author dogi_
 */

public class SequenceNumber{
    
    private final Random random=new Random();
    private int sequence_number;
    
    public SequenceNumber(){
        this.sequence_number=random.nextInt(1000);
    }
    
    public synchronized int getNextSequenceNumber(){
        this.sequence_number++;
        return this.sequence_number;
    } 
    
}
