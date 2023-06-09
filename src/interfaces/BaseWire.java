package interfaces;

import objects.drivers.Driver;
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
    public boolean send(Driver driver1, Driver driver2);
    
}
