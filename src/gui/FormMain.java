package gui;

import enums.ConnectorType;
import enums.EtherType;
import gui.panels.PanelDrivers;
import gui.panels.PanelScenery;
import enums.ToolType;
import gui.panels.PanelConnectors;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import multitaks.directory.ModelDirectory;
import objects.drivers.Driver;

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
        this.scenery.selection.drivers.clear();
        this.scenery.getDriver(evt.getX(),evt.getY(),(driver)->{
            this.scenery.selection.x=evt.getX();
            this.scenery.selection.y=evt.getY();
            this.scenery.selection.off_set_x=evt.getX()-driver.x;
            this.scenery.selection.off_set_y=evt.getY()-driver.y;
            // Selección de dispositivo
            this.scenery.selection.driver_prev=this.scenery.selection.driver;
            this.scenery.selection.driver=driver;
            Driver driver1=this.scenery.selection.driver_prev;
            Driver driver2=this.scenery.selection.driver;
            // Selección de varios dispositivos
            this.scenery.selection.drivers.add(driver);
            if(this.scenery.selection.tool==ToolType.CONNECT){
                // Conectar
                this.scenery.connectors=new PanelConnectors(this.scenery);
                this.scenery.connectors.setBounds(driver.x,driver.y,50,150);
                this.scenery.add(this.scenery.connectors);
            }else
            if(this.scenery.selection.tool==ToolType.SEND && driver1!=null){
                // Asignar envio de paquete
                if(driver1.getDriverDHCP()==null){
                    JOptionPane.showMessageDialog(null,"El dispositivo no esta en una red","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    protocols.Package pack=new protocols.Package(EtherType.IPv4,driver1,driver2,"Hola mundo");
                    driver1.addSendingPackage(driver1.getConnector(ConnectorType.RJ45),pack);
                }
                this.scenery.selection.tool=ToolType.DEFAULT;
                this.scenery.selection.driver_prev=null;
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

        panel_scenery = new javax.swing.JPanel();
        panel_drivers = new javax.swing.JPanel();
        panel_tools = new javax.swing.JPanel();
        btn_remove = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_connect = new javax.swing.JToggleButton();
        btn_sumulator = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        panel_protocols = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btn_package = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

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

        btn_sumulator.setText("Simulación");
        btn_sumulator.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btn_sumulatorItemStateChanged(evt);
            }
        });

        jButton2.setText("Temp");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_toolsLayout = new javax.swing.GroupLayout(panel_tools);
        panel_tools.setLayout(panel_toolsLayout);
        panel_toolsLayout.setHorizontalGroup(
            panel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_toolsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_sumulator)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_remove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_connect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_save)
                .addContainerGap())
        );
        panel_toolsLayout.setVerticalGroup(
            panel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_toolsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_remove)
                    .addComponent(btn_save)
                    .addComponent(btn_connect)
                    .addComponent(btn_sumulator)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_package.setText("Paquete");
        btn_package.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_packageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_package, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_package)
                .addGap(0, 311, Short.MAX_VALUE))
        );

        panel_protocols.addTab("Red física", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );

        panel_protocols.addTab("Vínculo de datos", jPanel2);

        jButton1.setText("Asignar IP's");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 311, Short.MAX_VALUE))
        );

        panel_protocols.addTab("Internet", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );

        panel_protocols.addTab("Transporte", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );

        panel_protocols.addTab("Aplicación", jPanel5);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Capa TCP/IP");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        jMenuItem1.setText("Aplicación");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, 0));
        jMenuItem2.setText("Transporte");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, 0));
        jMenuItem3.setText("Internet");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, 0));
        jMenuItem4.setText("Vínculo de datos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 0));
        jMenuItem5.setText("Red física");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_tools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_protocols, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(panel_scenery, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_protocols))
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.panel_protocols.setSelectedIndex(4);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.panel_protocols.setSelectedIndex(3);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.panel_protocols.setSelectedIndex(2);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.panel_protocols.setSelectedIndex(1);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        this.panel_protocols.setSelectedIndex(0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Driver driver=this.scenery.selection.driver;
        if(driver==null || driver.dhcp==null){
            return;
        }
        driver.dhcp.ip();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_packageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_packageActionPerformed
        this.scenery.selection.driver_prev=null;
        this.scenery.selection.driver=null;
        this.scenery.selection.tool=ToolType.SEND;
    }//GEN-LAST:event_btn_packageActionPerformed

    private void btn_sumulatorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btn_sumulatorItemStateChanged
        if(evt.getStateChange()==ItemEvent.SELECTED){
            this.scenery.start(this.panel_scenery);
        }else{
            this.scenery.stop();
        }
    }//GEN-LAST:event_btn_sumulatorItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Driver driver=this.scenery.selection.driver;
        if(driver==null){
            return;
        }
        System.out.println("Driver: "+driver);
        System.out.println("Envios: "+driver.sending_packages);
        System.out.println("Recibidos: "+driver.receiving_packages);
        System.out.println("\n");
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_connect;
    private javax.swing.JButton btn_package;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_save;
    private javax.swing.JToggleButton btn_sumulator;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel panel_drivers;
    private javax.swing.JTabbedPane panel_protocols;
    private javax.swing.JPanel panel_scenery;
    private javax.swing.JPanel panel_tools;
    // End of variables declaration//GEN-END:variables
}
