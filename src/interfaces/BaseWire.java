package interfaces;

import objects.wire.connectors.Connector;
import objects.wires.Connection;

/**
 *
 * @author dogi_
 */

public interface BaseWire{
    
    public boolean setConnection1(Connector connector_female);
    public boolean setConnection2(Connector connector_female);
    public Connection getConnection1();
    public Connection getConnection2();
    
}
