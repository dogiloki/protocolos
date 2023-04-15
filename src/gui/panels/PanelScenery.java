package gui.panels;

import enums.EtherType;
import enums.PackageType;
import gui.FormMain;
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
import multitaks.Function;
import objects.drivers.Driver;
import objects.scenery.Count;
import objects.scenery.Scenery;
import objects.scenery.ScenerySelection;
import objects.wire.connectors.Connector;
import protocols.PackageEther;
import protocols.mail.MailPackaging;
import protocols.mail.UserPackaging;
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
                g.setColor(Color.BLACK);
                g.drawRect(driver.x-5,driver.y-5,driver.width+10,driver.height+10);
            }
            Icon icon=new ImageIcon(new ImageIcon(this.getClass().getResource(driver.src_icon)).getImage().getScaledInstance(driver.width,driver.height,Image.SCALE_DEFAULT));
            icon.paintIcon(this,g,driver.x,driver.y);
            // Renderizar paquetes
            driver.sending_packages.forEach((connetor,list_send_package)->{
                g.setColor(Color.ORANGE);
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
    
    public int time_sleep=10;
    public int px_speed=1;
    public int px_total=20;
    public FormMain frame;
    private boolean stop=true;
    
    public void start(FormMain frame){
        this.frame=frame;
        this.stop=false;
        new Thread(this).start();
    }
    
    public void stop(){
        this.stop=true;
    }
    
    @Override
    public void run(){
        out:while(!this.stop){
            for(int count=0; count<this.px_total; count+=this.px_speed){
                if(this.stop){
                    break out;
                }
                for(Driver driver:this.scenery.drivers){
                    if(this.stop){
                        break out;
                    }
                    try{
                        // Cambiar valores del paquete para GUI
                        for(Map.Entry<Connector,List<protocols.PackageEther>> entry:driver.sending_packages.entrySet()){
                            if(this.stop){
                                break out;
                            }
                            Connector connector=entry.getKey();
                            List<PackageEther> send_packages=entry.getValue();
                            if(send_packages==null || send_packages.isEmpty()){
                                continue;
                            }
                            PackageEther send_package=send_packages.get(0);
                            Driver driver_source=null;
                            Driver driver1=null;
                            Driver driver2=null;
                            Driver server_driver=Function.assign(driver.getDriverDHCP(),driver);
                            if(send_package.header.type==EtherType.IPv4){
                                driver_source=server_driver.getDriverMac(send_package.header.source_driver);
                                driver1=driver_source;
                                driver2=server_driver.getDriverMac(send_package.header.destination_driver);    
                            }else
                            if(send_package.header.type==EtherType.TCP){    
                                driver_source=DNS.get(send_package.header.source_driver);
                                driver1=driver_source;
                                driver2=DNS.get(send_package.header.destination_driver);
                            }
                            if(driver1==null || driver2==null){
                                driver.addSentPackage(connector,send_package);
                                driver.sending_packages.get(connector).remove(send_package);
                                driver_source.addLog("[package "+send_package.header.sequence_number+"] El destino es inaccesible");
                                continue;
                            }
                            if(driver.dhcp==null){
                                driver2=driver1.getDriverDHCP();
                            }else{
                                if(driver.getDriverDHCP()==driver2.getDriverDHCP()){
                                    driver1=driver2.getDriverDHCP();
                                }else{
                                    driver1=driver;
                                    driver2=driver2.getDriverDHCP();
                                }
                            }
                            if(driver1==null || driver2==null){
                                driver.addSentPackage(connector,send_package);
                                driver.sending_packages.get(connector).remove(send_package);
                                driver_source.addLog("[package "+send_package.header.sequence_number+"] El destino es inaccesible");
                                continue;
                            }
                            //Driver server_driver=driver1.getDriverDHCP();
                            int index_x=driver1.x+(driver1.width/2);
                            int index_y=driver1.y+(driver1.height/2);
                            int end_x=driver2.x+(driver2.width/2);
                            int end_y=driver2.y+(driver2.height/2);
                            int distance_x=end_x-index_x;
                            int distance_y=end_y-index_y;
                            int x=(int)((distance_x/(double)this.px_total)*send_package.count);
                            int y=(int)((distance_y/(double)this.px_total)*send_package.count);
                            send_package.x=index_x+x;
                            send_package.y=index_y+y;
                            send_package.count+=this.px_speed;
                            if(Function.isRange(send_package.x, driver2.x,driver2.x+driver2.width) && Function.isRange(send_package.y, driver2.y,driver2.y+driver2.height)){
                                driver2.addLog("Comprobando dispositivo de destino...");
                                if(driver2.dhcp==null){
                                    driver2.addReceivingPackage(driver2.getConnector(connector.type_connector),send_package);
                                    switch(send_package.package_type){
                                        case SMTP:{
                                            driver2.addLog("Comprobando servidor SMTP...");
                                            if(driver2.server_smtp==null){
                                                driver_source.addLog("No se encontró el servidor SMTP");
                                            }else{
                                                MailPackaging mail_pack=(MailPackaging)send_package.object;
                                                String message;
                                                driver2.addLog("Comprobando credenciales...");
                                                if(driver2.server_smtp.auth(mail_pack.mail_address,mail_pack.password)){
                                                    if(driver2.server_smtp.exists(mail_pack.mail.mail_recipient)){
                                                        message="Correo enviado con éxito";
                                                        driver2.addLog("Correo enviado con éxito");
                                                        driver2.server_smtp.addMail(mail_pack.mail);
                                                    }else{
                                                        message="El correo de destino no existe (no entregado)";
                                                        driver2.addLog("El correo de destino no existe (no entregado)");
                                                    }
                                                }else{
                                                    message="No se pudo auteticar el correo";
                                                    driver2.addLog("No se pudo auteticar el correo");
                                                }
                                                driver2.addLog("Enviado paquete de repuesta");
                                                driver2.createPackage(send_package.header.sequence_number,PackageType.NORMAL,EtherType.TCP,driver_source.ipv4_public,message);
                                            }
                                            break;
                                        }
                                        case POP:{
                                            driver2.addLog("Comprobando servidor POP...");
                                            if(driver2.server_pop==null){
                                                driver_source.addLog("No se encontró el servidor POP");
                                            }else{
                                                UserPackaging user_pack=(UserPackaging)send_package.object;
                                                Object message;
                                                driver2.addLog("Comprobando credenciales...");
                                                if(driver2.server_pop.auth(user_pack.address,user_pack.password)){
                                                    message=driver2.server_pop.getMailsRecipient(user_pack.address);
                                                }else{
                                                    message="No se pudo auteticar el correo";
                                                    driver2.addLog("No se pudo auteticar el correo");
                                                }
                                                driver2.addLog("Enviado paquete de repuesta");
                                                driver2.createPackage(send_package.header.sequence_number,PackageType.NORMAL,EtherType.TCP,driver_source.ipv4_public,message);
                                            }
                                            break;
                                        }
                                    }
                                    driver_source.addLog("Comprobando protocolo del paquete...");
                                    if(send_package.header.type==EtherType.IPv4){
                                        driver_source.addLog("[package "+send_package.header.sequence_number+"] Llegó a su destino "+driver2.mac);
                                    }else{
                                        driver_source.addLog("[package "+send_package.header.sequence_number+"] Llegó a su destino "+driver2.ipv4_public);
                                    }
                                }else{
                                    driver_source.addLog("Direcionado paquete");
                                    driver2.addSendingPackage(driver2.getConnector(connector.type_connector),send_package);
                                    driver_source.addLog("Comprobando protocolo del paquete...");
                                    if(send_package.header.type==EtherType.IPv4){
                                        driver_source.addLog("[package "+send_package.header.sequence_number+"] Paso por "+driver2.mac);
                                    }else{
                                        driver_source.addLog("[package "+send_package.header.sequence_number+"] Paso por "+driver2.ipv4_public);
                                    }
                                }
                                send_package.count=0;
                                driver.addSentPackage(connector,send_package);
                                driver.sending_packages.get(connector).remove(send_package);
                            }
                        }
                        Thread.sleep(this.time_sleep);
                        this.frame.panel_scenery.updateUI();
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
        //driver.name=driver.type.toString()+count;
        driver.host=driver.type.toString()+count;
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
        DNS.put(driver.ipv4_public,null);
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
