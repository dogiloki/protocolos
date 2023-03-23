package objects.scenery;

import enums.ToolType;
import objects.drivers.Driver;

/**
 *
 * @author dogiloki
 */

public class ScenerySelection{
    
    public int x;
    public int y;
    public int off_set_x;
    public int off_set_y;
    public Driver driver;
    public ToolType tool=ToolType.DEFAULT;
    
    public ScenerySelection(){
        
    }
    
}
