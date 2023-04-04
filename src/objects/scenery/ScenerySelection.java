package objects.scenery;

import enums.ToolType;
import java.util.ArrayList;
import java.util.List;
import objects.drivers.Driver;
import objects.wire.connectors.Connector;

/**
 *
 * @author dogiloki
 */

public class ScenerySelection{
    
    public int x;
    public int y;
    public int off_set_x;
    public int off_set_y;
    public int mouse_x;
    public int mouse_y;
    public Driver driver_prev;
    public Driver driver;
    public List<Driver> drivers=new ArrayList<>();
    public Connector connetor_prev;
    public Connector connetor;
    public ToolType tool=ToolType.DEFAULT;
    
    public ScenerySelection(){
        
    }
    
}
