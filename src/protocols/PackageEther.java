package protocols;

import enums.EtherType;
import enums.PackageType;
import java.util.List;
import java.util.ArrayList;
import multitaks.annotations.relation.Relation;
import multitaks.enums.RelationType;
import objects.scenery.Entity;

/**
 *
 * @author dogi_
 */

public class PackageEther extends Entity implements Cloneable{
    
    public Header header;
    @Relation(type=RelationType.OneToMany)
    public List<Trama> data=new ArrayList<>();
    public byte[] trailer;
    public Object object;
    public PackageType package_type;
    
    public PackageEther(int sequence_number,PackageType package_type, EtherType type, String source_driver, String destination_driver, Object data){
        
        this.package_type=package_type;
        
        Header header=new Header();
        header.sequence_number=sequence_number;
        header.type=type;
        header.source_driver=source_driver;
        header.destination_driver=destination_driver;
        this.header=header;
        
        Trama trama=new Trama();
        trama.data=data.toString().getBytes();
        trama.lenght=trama.data.length;
        this.data.add(trama);
        this.object=data;
        
        this.x=-100;
        this.y=-100;
        this.width=10;
        this.height=10;
        
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    
}
