package objects.scenery;

import com.dogiloki.multitaks.ObjectId;
import com.google.gson.annotations.Expose;

/**
 *
 * @author dogi_
 */

public class Entity{
    
    @Expose
    public String id=ObjectId.generate();
    @Expose
    public int x;
    @Expose
    public int y;
    @Expose
    public int width;
    @Expose
    public int height;
    @Expose
    public String src_icon;
    @Expose
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
