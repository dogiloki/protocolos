package protocols;

import enums.EtherType;
import java.util.List;
import java.util.ArrayList;
import multitaks.annotations.relation.Relation;
import multitaks.enums.RelationType;
import objects.scenery.Entity;

/**
 *
 * @author dogi_
 */

public class PackageEther extends Entity{
    
    public Header header;
    @Relation(type=RelationType.OneToMany)
    public List<Trama> data=new ArrayList<>();
    public byte[] trailer;
    
    public PackageEther(int sequence_number, EtherType type, String source_driver, String destination_driver, String data){
        
        Header header=new Header();
        header.sequence_number=sequence_number;
        header.type=type;
        header.source_driver=source_driver;
        header.destination_driver=destination_driver;
        this.header=header;
        
        Trama trama=new Trama();
        trama.data=data.getBytes();
        trama.lenght=trama.data.length;
        this.data.add(trama);
        
        this.x=-100;
        this.y=-100;
        this.width=10;
        this.height=10;
        
    }
    
}
