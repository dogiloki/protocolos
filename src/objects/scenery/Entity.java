package objects.scenery;

import java.util.UUID;
import multitaks.annotations.directory.Directory;
import multitaks.annotations.directory.Key;
import multitaks.enums.DirectoryType;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Entity{
    
    @Key(value="id")
    public String id=UUID.randomUUID().toString();
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
    @Key(value="log")
    public String log="";
    
    public int count;
    
    public Entity(){
        this.width=50;
        this.height=50;
        this.count=0;
    }
    
    public void setLog(String log){
        this.log=log+"\n\n";
    }
    
    public void addLog(String log){
        this.log+=log+"\n\n";
    }
    
    public String getLog(){
        return this.log;
    }
    
}
