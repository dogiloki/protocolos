package gui;

import enums.ToolType;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import objects.driver.Driver;
import objects.scenery.Entity;

/**
 *
 * @author dogi_
 */

public class FormMain extends javax.swing.JFrame {
    
    //public PanelScenery scenery;
    public PanelDrivers drivers;
    public PanelScenery scenery;
    
    public FormMain() {
        initComponents();
        this.scenery=new PanelScenery();
        this.drivers=new PanelDrivers();
        this.setPanel(this.panel_drivers,this.drivers);
        this.setPanel(this.panel_scenery,this.scenery);
    }
    
    public void setPanel(JPanel panel1, JPanel panel2){
        panel2.setVisible(true);
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.removeAll();
        panel1.add(panel2,BorderLayout.CENTER);
        panel1.revalidate();
        panel1.repaint();
        panel1.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_protocols = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        panel_scenery = new javax.swing.JPanel();
        panel_drivers = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Eternet");

        javax.swing.GroupLayout panel_protocolsLayout = new javax.swing.GroupLayout(panel_protocols);
        panel_protocols.setLayout(panel_protocolsLayout);
        panel_protocolsLayout.setHorizontalGroup(
            panel_protocolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        panel_protocolsLayout.setVerticalGroup(
            panel_protocolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_protocolsLayout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 405, Short.MAX_VALUE))
        );

        panel_scenery.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_scenery.setMaximumSize(new java.awt.Dimension(700, 700));
        panel_scenery.setMinimumSize(new java.awt.Dimension(0, 0));
        panel_scenery.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel_sceneryMouseDragged(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_protocols, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_scenery, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_drivers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_protocols, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_scenery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_drivers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel_sceneryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMousePressed
        if(this.drivers.selection.driver!=null){
            try{
                Driver driver=(Driver)this.drivers.selection.driver.getDeclaredConstructor().newInstance();
                driver.x=evt.getX();
                driver.y=evt.getY();
                this.scenery.addDriver(driver);
                this.drivers.selection.driver=null;
                this.panel_scenery.updateUI();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        this.scenery.getDriver(evt.getX(),evt.getY(),(driver)->{
            this.scenery.selection.x=evt.getX();
            this.scenery.selection.y=evt.getY();
            this.scenery.selection.driver=driver;
            this.scenery.selection.tool=ToolType.MOVE;
        });
    }//GEN-LAST:event_panel_sceneryMousePressed

    private void panel_sceneryMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMouseDragged
        if(this.scenery.selection.tool!=ToolType.MOVE){
            return;
        }
        Driver driver=this.scenery.selection.driver;
        driver.x=evt.getX();
        driver.y=evt.getY();
        this.panel_scenery.updateUI();
    }//GEN-LAST:event_panel_sceneryMouseDragged

    private void panel_sceneryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMouseReleased
        if(this.scenery.selection.tool==ToolType.MOVE){
            this.scenery.selection.tool=ToolType.DEFAULT;
        }
    }//GEN-LAST:event_panel_sceneryMouseReleased

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panel_drivers;
    private javax.swing.JPanel panel_protocols;
    private javax.swing.JPanel panel_scenery;
    // End of variables declaration//GEN-END:variables
}
