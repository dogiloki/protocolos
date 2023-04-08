package gui;

import gui.panels.PanelDrivers;
import gui.panels.PanelScenery;
import enums.ToolType;
import gui.panels.PanelConnectors;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import multitaks.directory.ModelDirectory;
import objects.drivers.Driver;
import protocols.DHCP;

/**
 *
 * @author dogi_
 */

public final class FormMain extends javax.swing.JFrame{
    
    public PanelDrivers drivers;
    public PanelScenery scenery;
    private final ModelDirectory model=new ModelDirectory();
    
    public FormMain() {
        initComponents();
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.scenery=new PanelScenery();
        this.drivers=new PanelDrivers();
        // Obtener datos del escenario
        this.model.run(this.scenery.scenery,"temp_escenary.json");
        this.scenery.scenery.setConnectors();
        this.setPanel(this.panel_drivers,this.drivers);
        this.setPanel(this.panel_scenery,this.scenery);
    }
    
    public void setPanel(JPanel panel1, JPanel panel2){
        panel2.setVisible(true);
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.removeAll();
        panel1.add(panel2);
        panel1.updateUI();
    }
    
    public void resizablePanel(JPanel panel1, JPanel panel2){
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.updateUI();
    }
    
    public void selectDriver(MouseEvent evt){
        this.scenery.getDriver(evt.getX(),evt.getY(),(driver)->{
            this.scenery.selection.x=evt.getX();
            this.scenery.selection.y=evt.getY();
            this.scenery.selection.off_set_x=evt.getX()-driver.x;
            this.scenery.selection.off_set_y=evt.getY()-driver.y;
            // Selección de dispositivo
            this.scenery.selection.driver_prev=this.scenery.selection.driver;
            this.scenery.selection.driver=driver;
            // Selección de varios dispositivos
            this.scenery.selection.drivers.clear();
            this.scenery.selection.drivers.add(driver);
            if(this.scenery.selection.tool==ToolType.CONNECT){
                this.scenery.connectors=new PanelConnectors(this.scenery);
                this.scenery.connectors.setBounds(driver.x,driver.y,50,150);
                this.scenery.add(this.scenery.connectors);
            }else
            if(evt.getButton()==3){
                new DialogDriver(this,true,this.scenery).setVisible(true);
            }else{
                if(this.scenery.selection.tool==ToolType.DEFAULT){
                    this.scenery.selection.tool=ToolType.MOVE;
                }
            }
        });
        this.panel_scenery.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_protocols = new javax.swing.JPanel();
        btn_dhcp = new javax.swing.JButton();
        panel_scenery = new javax.swing.JPanel();
        panel_drivers = new javax.swing.JPanel();
        panel_tools = new javax.swing.JPanel();
        btn_scenery_start = new javax.swing.JButton();
        btn_remove = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_connect = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        btn_dhcp.setText("DHCP");
        btn_dhcp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dhcpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_protocolsLayout = new javax.swing.GroupLayout(panel_protocols);
        panel_protocols.setLayout(panel_protocolsLayout);
        panel_protocolsLayout.setHorizontalGroup(
            panel_protocolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_dhcp, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        panel_protocolsLayout.setVerticalGroup(
            panel_protocolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_protocolsLayout.createSequentialGroup()
                .addComponent(btn_dhcp)
                .addGap(0, 360, Short.MAX_VALUE))
        );

        panel_scenery.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_scenery.setMaximumSize(new java.awt.Dimension(700, 700));
        panel_scenery.setMinimumSize(new java.awt.Dimension(0, 0));
        panel_scenery.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel_sceneryMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panel_sceneryMouseMoved(evt);
            }
        });
        panel_scenery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_sceneryMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panel_sceneryMouseReleased(evt);
            }
        });
        panel_scenery.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panel_driversLayout = new javax.swing.GroupLayout(panel_drivers);
        panel_drivers.setLayout(panel_driversLayout);
        panel_driversLayout.setHorizontalGroup(
            panel_driversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        panel_driversLayout.setVerticalGroup(
            panel_driversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btn_scenery_start.setText("Simular");

        btn_remove.setText("Eliminar");
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });

        btn_save.setText("Guardar");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_connect.setText("Modo conectar");
        btn_connect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btn_connectItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panel_toolsLayout = new javax.swing.GroupLayout(panel_tools);
        panel_tools.setLayout(panel_toolsLayout);
        panel_toolsLayout.setHorizontalGroup(
            panel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_toolsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_scenery_start)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_remove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_connect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_save)
                .addContainerGap(511, Short.MAX_VALUE))
        );
        panel_toolsLayout.setVerticalGroup(
            panel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_toolsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_scenery_start)
                    .addComponent(btn_remove)
                    .addComponent(btn_save)
                    .addComponent(btn_connect))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_tools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_protocols, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_scenery, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_drivers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_tools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_drivers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_protocols, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_scenery, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel_sceneryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMousePressed
        if(this.scenery.connectors!=null){
            this.scenery.remove(this.scenery.connectors);
            this.scenery.connectors.updateUI();
            this.scenery.connectors=null;
        }
        if(this.drivers.selection.driver!=null){
            try{
                Driver driver=(Driver)this.drivers.selection.driver.getDeclaredConstructor().newInstance();
                driver.x=evt.getX();
                driver.y=evt.getY();
                this.scenery.addDriver(driver);
                this.drivers.selection.driver=null;
                this.drivers.reset();
                this.panel_scenery.updateUI();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        this.selectDriver(evt);
    }//GEN-LAST:event_panel_sceneryMousePressed

    private void panel_sceneryMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMouseDragged
        if(this.scenery.selection.tool!=ToolType.MOVE){
            return;
        }
        Driver driver=this.scenery.selection.driver;
        driver.x=evt.getX()-this.scenery.selection.off_set_x;
        driver.y=evt.getY()-this.scenery.selection.off_set_y;
        this.panel_scenery.updateUI();
    }//GEN-LAST:event_panel_sceneryMouseDragged

    private void panel_sceneryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMouseReleased
        if(this.scenery.selection.tool==ToolType.MOVE){
            this.scenery.selection.tool=ToolType.DEFAULT;
        }
    }//GEN-LAST:event_panel_sceneryMouseReleased

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        this.scenery.removeDriver();
        this.panel_scenery.updateUI();
    }//GEN-LAST:event_btn_removeActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        this.model.save();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void panel_sceneryMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMouseMoved
        this.scenery.selection.mouse_x=evt.getX();
        this.scenery.selection.mouse_y=evt.getY();
    }//GEN-LAST:event_panel_sceneryMouseMoved

    private void btn_connectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btn_connectItemStateChanged
        if(evt.getStateChange()==ItemEvent.SELECTED){
            this.scenery.selection.tool=ToolType.CONNECT;
        }else{
            this.scenery.selection.tool=ToolType.DEFAULT;
        }
        this.scenery.selection.connetor_prev=null;
        this.scenery.selection.connetor=null;
    }//GEN-LAST:event_btn_connectItemStateChanged

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        this.resizablePanel(this.panel_drivers,this.drivers);
    }//GEN-LAST:event_formComponentResized

    private void btn_dhcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dhcpActionPerformed
        Driver driver=this.scenery.selection.driver;
        if(!driver.dhcp){
            return;
        }
        DHCP.aim(driver).all(this.scenery.scenery.listDrivers(driver));
    }//GEN-LAST:event_btn_dhcpActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_connect;
    private javax.swing.JButton btn_dhcp;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_scenery_start;
    private javax.swing.JPanel panel_drivers;
    private javax.swing.JPanel panel_protocols;
    private javax.swing.JPanel panel_scenery;
    private javax.swing.JPanel panel_tools;
    // End of variables declaration//GEN-END:variables
}
