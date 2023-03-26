package objects.scenery;

import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Entity{

    @Key(value="x")
    public int x;
    @Key(value="y")
    public int y;
    @Key(value="width")
    public int width;
    @Key(value="height")
    public int height;
    @Key(value="src_icon")
    public String src_icon;
    
    public Entity(){
        this.width=50;
        this.height=50;
    }
    
}
