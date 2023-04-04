package gui.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import multitaks.Function;
import objects.drivers.Driver;
import objects.scenery.Scenery;
import objects.scenery.ScenerySelection;

/**
 *
 * @author dogi_
 */

public class PanelScenery extends javax.swing.JPanel{

    public interface Callback{
        public void execute(Driver driver);
    }
    
    public Scenery scenery=new Scenery();
    public ScenerySelection selection=new ScenerySelection();
    public PanelConnectors connectors;
    
    public PanelScenery(){
        initComponents();
    }
    
    @Override
    public void paint(Graphics g){
        // Renderizar dispositivos
        this.scenery.drivers.forEach((driver)->{
            if(this.selection.driver==driver){
                g.drawRect(driver.x-5,driver.y-5,driver.width+10,driver.height+10);
            }
            Icon icon=new ImageIcon(new ImageIcon(this.getClass().getResource(driver.src_icon)).getImage().getScaledInstance(driver.width,driver.height,Image.SCALE_DEFAULT));
            icon.paintIcon(this,g,driver.x,driver.y);
        });
        // Rederizar conexiones
        this.scenery.wires.forEach((wire)->{
            Driver driver1=this.scenery.connector_wire.get(wire.id_connector1);
            Driver driver2=this.scenery.connector_wire.get(wire.id_connector2);
            wire.index_x=driver1.x+driver1.width/2;
            wire.index_y=driver1.y+driver1.height/2;
            wire.end_x=driver2.x+driver2.width/2;
            wire.end_y=driver2.y+driver2.height/2;
            Color color=new Color(this.scenery.connectors.get(wire.id_connector1).color);
            g.setColor(color);
            Graphics2D g2d=(Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            g.drawLine(wire.index_x,wire.index_y,wire.end_x,wire.end_y);
        });
        // Renderizar lista de conectores
        if(this.connectors!=null){
            this.connectors.updateUI();
        }
    }
    
    public void addDriver(Driver driver){
        driver.connectors.forEach((connector)->{
            this.scenery.connector_wire.put(connector.id,driver);
            this.scenery.connectors.put(connector.id,connector);
        });
        this.scenery.drivers.add(driver);
    }
    
    public void getDriver(int x, int y, Callback action){
        for(Driver driver:this.scenery.drivers){
            if(Function.isRange(x,driver.x,driver.x+driver.width) && Function.isRange(y,driver.y,driver.y+driver.height)){
                action.execute(driver);
                return;
            }
        }
    }
    
    public void removeDriver(Callback action){
        this._removeDriver(action);
    }
    public void removeDriver(){
        this._removeDriver(null);
    }
    private void _removeDriver(Callback action){
        if(this.selection.driver==null){
            return;
        }
        Driver driver=this.selection.driver;
        if(action!=null){
            action.execute(driver);
        }
        this.scenery.drivers.remove(driver);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
