package objects.wires;

import enums.EntryType;
import enums.WireType;
import java.awt.Color;
import objects.wire.connectors.RJ45;

/**
 *
 * @author dogi_
 */

public class Eternet extends Wire{
    
    public Eternet(){
        this.type_wire=WireType.ETERNET;
        this.connector1=new RJ45(EntryType.MALE,null);
        this.connector2=new RJ45(EntryType.MALE,null);
        this.color=Color.ORANGE.getRGB();
    }
    
}
