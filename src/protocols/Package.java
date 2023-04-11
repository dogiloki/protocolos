package protocols;

import enums.EtherType;
import java.util.List;
import java.util.ArrayList;
import multitaks.annotations.relation.Relation;
import multitaks.enums.RelationType;
import objects.drivers.Driver;
import objects.scenery.Entity;

/**
 *
 * @author dogi_
 */

public class Package extends Entity{
    
    public Header header;
    @Relation(type=RelationType.OneToMany)
    public List<Trama> data=new ArrayList<>();
    public byte[] trailer;
    
    public Package(EtherType type, Driver source_driver, Driver destination_driver, String data){
        
        Header header=new Header();
        header.type=type;
        header.source_driver=source_driver;
        header.destination_driver=destination_driver;
        this.header=header;
        
        Trama trama=new Trama();
        trama.data=data.getBytes();
        trama.lenght=trama.data.length;
        
        this.x=-100;
        this.y=-100;
        this.width=10;
        this.height=10;
    }
    
}
