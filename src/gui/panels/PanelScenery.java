package gui.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import multitaks.Function;
import objects.drivers.Driver;
import objects.scenery.Count;
import objects.scenery.Scenery;
import objects.scenery.ScenerySelection;
import objects.wire.connectors.Connector;
import services.DNS;

/**
 *
 * @author dogi_
 */

public class PanelScenery extends javax.swing.JPanel implements Runnable{

    public interface deletedDriver{
        public void execute(Driver driver);
    }
    
    public interface gettingDriver{
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
            // Renderizar paquetes
            driver.sending_packages.forEach((connetor,list_send_package)->{
                g.setColor(Color.BLACK);
                list_send_package.forEach((send_package)->{
                    g.fillRect(send_package.x,send_package.y,send_package.width,send_package.height);
                });
            });
        });
        // Renderizar conexiones
        this.scenery.wires.forEach((wire)->{
            Driver driver1=wire.getDriver1();
            Driver driver2=wire.getDriver2();
            wire.index_x=driver1.x+driver1.width/2;
            wire.index_y=driver1.y+driver1.height/2;
            wire.end_x=driver2.x+driver2.width/2;
            wire.end_y=driver2.y+driver2.height/2;
            Color color=new Color(wire.color);
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
    
    public int time=0;
    public int time_speed=10;
    public int time_total=2000;
    public JPanel panel;
    private boolean stop=true;
    
    public void start(JPanel panel){
        this.panel=panel;
        this.stop=false;
        this.time=0;
        new Thread(this).start();
    }
    
    public void stop(){
        this.stop=true;
    }
    
    @Override
    public void run(){
        out:while(!this.stop){
            for(this.time=0; this.time<this.time_total; this.time+=this.time_speed){
                if(this.stop){
                    break out;
                }
                for(Driver driver:this.scenery.drivers){
                    if(this.stop){
                        break out;
                    }
                    try{
                        // Cambiar valores del paquete para GUI
                        for(Map.Entry<Connector,List<protocols.Package>> entry:driver.sending_packages.entrySet()){
                            if(this.stop){
                                break out;
                            }
                            Connector connector=entry.getKey();
                            List<protocols.Package> send_packages=entry.getValue();
                            if(send_packages==null || send_packages.isEmpty()){
                                continue;
                            }
                            protocols.Package send_package=send_packages.get(0);
                            Driver driver1=send_package.header.source_driver;
                            Driver driver2=send_package.header.destination_driver;
                            Driver server_driver=driver1.getDriverDHCP();
                            if(driver.dhcp==null){
                                driver2=driver1.getDriverDHCP();
                            }else{
                                driver1=driver1.getDriverDHCP();
                            }
                            if(driver1==null || driver2==null){
                                continue;
                            }
                            //Driver server_driver=driver1.getDriverDHCP();
                            int index_x=driver1.x+(driver1.width/2);
                            int index_y=driver1.y+(driver1.height/2);
                            int end_x=driver2.x+(driver2.width/2);
                            int end_y=driver2.y+(driver2.height/2);
                            int distance_x=end_x-index_x;
                            int distance_y=end_y-index_y;
                            int x=(int)((distance_x/(double)this.time_total)*send_package.count);
                            int y=(int)((distance_y/(double)this.time_total)*send_package.count);
                            send_package.x=index_x+x;
                            send_package.y=index_y+y;
                            send_package.count+=this.time_speed;
                            if(Function.isRange(send_package.x, driver2.x,driver2.x+driver2.width) && Function.isRange(send_package.y, driver2.y,driver2.y+driver2.height)){
                                if(driver2.dhcp==null){
                                    driver2.addReceivingPackage(server_driver.getConnector(connector.type_connector),send_package);
                                }else{
                                    driver2.addSendingPackage(server_driver.getConnector(connector.type_connector),send_package);
                                }
                                send_package.count=0;
                                driver.sending_packages.get(connector).remove(send_package);
                            }
                        }
                        Thread.sleep((int)this.time_speed);
                        this.panel.updateUI();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void addDriver(Driver driver){
        int count=0;
        int index=0;
        boolean meeting=false;
        for(Count obj_count:this.scenery.counts){
            if(obj_count.type==driver.type){
                count=obj_count.value;
                meeting=true;
                break;
            }else{
                index++;
            }
        }
        count++;
        if(meeting){
            this.scenery.counts.set(index,new Count(driver.type,count));
        }else{
            this.scenery.counts.add(new Count(driver.type,count));
        }
        switch(driver.type){
            case ROUTER:{
                driver.ipv4="192.168."+count+".1";
                break;
            }
        }
        driver.name=driver.type.toString()+count;
        driver.host=driver.name;
        this.scenery.drivers.add(driver);
        DNS.put(driver.ipv4_public,driver);
    }
    
    public void getDriver(int x, int y, gettingDriver action){
        for(Driver driver:this.scenery.drivers){
            if(Function.isRange(x,driver.x,driver.x+driver.width) && Function.isRange(y,driver.y,driver.y+driver.height)){
                action.execute(driver);
                return;
            }
        }
    }
    
    public void removeDriver(deletedDriver action){
        this._removeDriver(action);
    }
    public void removeDriver(){
        this._removeDriver(null);
    }
    private void _removeDriver(deletedDriver action){
        if(this.selection.driver==null){
            return;
        }
        Driver driver=this.selection.driver;
        if(!driver.drivers.isEmpty()){
           JOptionPane.showMessageDialog(null,"El dispositivo esta conectado","Error",JOptionPane.ERROR_MESSAGE);
           return; 
        }
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
