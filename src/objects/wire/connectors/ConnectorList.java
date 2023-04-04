package objects.wire.connectors;

import enums.ConnectorType;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dogi_
 */

public class ConnectorList{
    
    public static Map<ConnectorType,Class<?>> drivers(){
        return new ConnectorList().connectors;
    }
    
    public Map<ConnectorType,Class<?>> connectors=new HashMap<>();
    
    public ConnectorList(){
        this.connectors.put(ConnectorType.ETERNET,Eternet.class);
        this.connectors.put(ConnectorType.RS_232,RS_232.class);
        this.connectors.put(ConnectorType.USB,USB.class);
    }
    
    public Class<?> get(ConnectorType type){
        return this.connectors.get(type);
    }
}
