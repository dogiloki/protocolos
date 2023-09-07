package objects.scenery;

import com.dogiloki.multitaks.ObjectId;
import com.dogiloki.multitaks.directory.annotations.Directory;
import com.dogiloki.multitaks.directory.enums.DirectoryType;
import com.google.gson.annotations.Expose;

/**
 *
 * @author dogi_
 */

@Directory(type=DirectoryType.JSON)
public class Line{
    
    @Expose
    public String id=ObjectId.generate();
    @Expose
    public int index_x;
    @Expose
    public int index_y;
    @Expose
    public int end_x;
    @Expose
    public int end_y;
    
    public void Line(){
        
    }
    
}
