package objects;

import java.util.List;
import java.util.ArrayList;
import multitaks.annotations.relation.Relation;
import multitaks.enums.RelationType;
import multitaks.relation.ModelRelation;

/**
 *
 * @author dogi_
 */

public class Package extends ModelRelation{
    
    public byte[] header;
    @Relation(type=RelationType.OneToMany)
    public List<Trama> tramas=new ArrayList<>();
    public byte[] trailer;
    
}
