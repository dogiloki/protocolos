package gui.panels;

import gui.panels.PanelScenery;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import multitaks.Function;
import objects.wire.connectors.Connector;
import objects.wire.connectors.ConnectorSelection;

/**
 *
 * @author dogiloki
 */

public final class PanelConnectors extends javax.swing.JPanel{

    public List<Connector> connectors=new ArrayList<>();
    public ConnectorSelection selection=new ConnectorSelection();
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
            btn.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent me){}
                @Override
                public void mousePressed(MouseEvent me){
                    if(btn_selection!=null){
                        if(btn_selection==btn){
                            return;
                        }
                        btn_selection.setBackground(null);
                        btn_selection.setOpaque(false);
                    }
                    selection.connector=connector;
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
            .addGap(0, 398, Short.MAX_VALUE)
        );
        panel_connectorsLayout.setVerticalGroup(
            panel_connectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        panel_connectors_scroll.setViewportView(panel_connectors);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_connectors_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_connectors_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
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
