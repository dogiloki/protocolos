package gui.panels;

import java.awt.Graphics;
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
        this.scenery.drivers.forEach((driver)->{
            if(this.selection.driver==driver){
                g.drawRect(driver.x-5,driver.y-5,driver.width+10,driver.height+10);
            }
            Icon icon=new ImageIcon(new ImageIcon(this.getClass().getResource(driver.src_icon)).getImage().getScaledInstance(driver.width,driver.height,Image.SCALE_DEFAULT));
            icon.paintIcon(this,g,driver.x,driver.y);
            System.out.println(driver.connectors);
        });
        if(this.connectors!=null){
            this.connectors.updateUI();
        }
    }
    
    public void addDriver(Driver driver){
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
