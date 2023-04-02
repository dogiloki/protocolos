package objects.wire;

import objects.wire.connectors.Connector;

/**
 *
 * @author dogi_
 */

public class Wire{
    
    private Connector connector1;
    private Connector connector2;
    
    public Wire(){
        
    }
    
    public void addConection1(Connector connector){
        this.connector1=connector;
    }
    
    public void addConection2(Connector connector){
        this.connector2=connector;
    }
    
}
