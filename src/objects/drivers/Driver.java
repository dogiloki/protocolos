package objects.drivers;

import enums.DriverType;
import java.util.ArrayList;
import java.util.List;
import objects.connectors.Connector;
import objects.scenery.Entity;

/**
 *
 * @author dogiloki
 */

public class Driver extends Entity{
    
    public DriverType type;
    public List<Connector> connectors=new ArrayList<>();
    
    public Driver(){
        
    }
    
}
