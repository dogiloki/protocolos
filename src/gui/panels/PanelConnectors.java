package gui.panels;

import enums.ToolType;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import multitaks.Function;
import objects.drivers.Driver;
import objects.wires.Wire;
import objects.wire.connectors.Connector;
import objects.wires.Connection;
import objects.wires.Eternet;

/**
 *
 * @author dogiloki
 */

public final class PanelConnectors extends javax.swing.JPanel{

    public List<Connector> connectors=new ArrayList<>();
    //public ConnectorSelection selection=new ConnectorSelection();
    public JLabel btn_selection=null;
    public PanelScenery scenery;
    
    public PanelConnectors(PanelScenery scenery){
        initComponents();
        this.setBounds(0,0,200,200);
        this.scenery=scenery;
        this.connectors=scenery.selection.driver.connectors;
    }
    
    public void reset(){
        this.setConnectors();
    }
    
    private void setConnectors(){
        this.panel_connectors.removeAll();
        this.panel_connectors.updateUI();
        int width_total=this.panel_connectors_scroll.getWidth()-20, height_total=0;
        int width=width_total, height=width;
        int x=0, y=0;
        for(Connector connector:this.connectors){
            
            JLabel btn=new JLabel();
            btn.setBounds(x,y,width,height);
            btn.setBackground(Color.WHITE);
            btn.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(connector.src_icon)).getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
            btn.setToolTipText(connector.type_connector.toString());
            if(connector.connected){
                btn.setEnabled(false);
            }
            btn.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent me){}
                @Override
                public void mousePressed(MouseEvent me){
                    if(connector.connected){
                        int op=JOptionPane.showConfirmDialog(null,"Connector ocupado. ¿Desconectar?","Advertencia",JOptionPane.WARNING_MESSAGE);
                        if(op==0){
                            // Eliminación conexión (cable)
                            connector.connected=false;
                            for(Wire wire:scenery.scenery.wires){
                                Connection connection1=wire.getConnection1();
                                Connection connection2=wire.getConnection1();
                                if(connection1.connector_female==connector || connection2.connector_female==connector){
                                    Driver driver1=wire.getDriver1();
                                    Driver driver2=wire.getDriver2();
                                    driver1.drivers.remove(driver2);
                                    driver2.drivers.remove(driver1);
                                    scenery.scenery.wires.remove(wire);
                                    break;
                                }
                            }
                            scenery.selection.connetor_prev=null;
                            scenery.selection.connetor=null;
                            scenery.selection.tool=ToolType.DEFAULT;
                        }
                        return;
                    }
                    if(btn_selection!=null){
                        if(btn_selection==btn){
                            return;
                        }
                        btn_selection.setBackground(null);
                        btn_selection.setOpaque(false);
                    }
                    // Selección de conector
                    scenery.selection.connetor_prev=scenery.selection.connetor;
                    scenery.selection.connetor=connector;
                    if(scenery.selection.connetor_prev!=null && scenery.selection.connetor!=null && scenery.selection.tool==ToolType.CONNECT){
                        Connector connect1=scenery.selection.connetor_prev;
                        Connector connect2=scenery.selection.connetor;
                        // Crear cable para conexión
                        Wire wire=new Wire();
                        switch(connect1.type_connector){
                            case RJ45:{
                                wire=new Eternet();
                            }
                        }
                        boolean done_connection1=wire.setConnection1(connect1);
                        boolean done_connection2=wire.setConnection2(connect2);
                        if(done_connection1 && done_connection2){
                            Driver driver1=wire.getDriver1();
                            Driver driver2=wire.getDriver2();
                            connect1.connected=true;
                            connect2.connected=true;
                            driver1.drivers.add(driver2);
                            driver2.drivers.add(driver1);
                            scenery.scenery.wires.add(wire);
                        }else{
                            JOptionPane.showMessageDialog(null,"Conectores no compatibles","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        scenery.selection.connetor_prev=null;
                        scenery.selection.connetor=null;
                        scenery.selection.tool=ToolType.DEFAULT;
                    }
                    // Selección del panel en la GUI
                    btn_selection=btn;
                    btn_selection.setBackground(Color.decode("#b2cff0"));
                    btn_selection.setOpaque(true);
                }
                @Override
                public void mouseReleased(MouseEvent me){}
                @Override
                public void mouseEntered(MouseEvent me){
                    if(btn_selection==null){
                        btn.setBackground(new Color(200,200,200));
                        btn.setOpaque(true);
                    }
                }
                @Override
                public void mouseExited(MouseEvent me){
                    if(btn_selection==null){
                        btn.setBackground(null);
                        btn.setOpaque(false);
                    }
                }
            });
            y+=height+10;
            height_total=y;
            
            this.panel_connectors.add(btn);
        }
        this.panel_connectors.updateUI();
        this.panel_connectors.setPreferredSize(Function.createDimencion(width_total,height_total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_connectors_scroll = new javax.swing.JScrollPane();
        panel_connectors = new javax.swing.JPanel();

        panel_connectors_scroll.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panel_connectors_scrollComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panel_connectorsLayout = new javax.swing.GroupLayout(panel_connectors);
        panel_connectors.setLayout(panel_connectorsLayout);
        panel_connectorsLayout.setHorizontalGroup(
            panel_connectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        panel_connectorsLayout.setVerticalGroup(
            panel_connectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        panel_connectors_scroll.setViewportView(panel_connectors);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_connectors_scroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_connectors_scroll)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void panel_connectors_scrollComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_connectors_scrollComponentResized
        this.setConnectors();
    }//GEN-LAST:event_panel_connectors_scrollComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel_connectors;
    private javax.swing.JScrollPane panel_connectors_scroll;
    // End of variables declaration//GEN-END:variables
}
