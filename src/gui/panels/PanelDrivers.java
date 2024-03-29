package gui.panels;

import com.dogiloki.multitaks.Function;
import enums.DriverType;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import objects.drivers.Driver;
import objects.drivers.DriverList;
import objects.drivers.DriverSelection;

/**
 *
 * @author dogiloki
 */

public final class PanelDrivers extends javax.swing.JPanel{

    public Map<DriverType,Class<?>> drivers=DriverList.drivers();
    public DriverSelection selection=new DriverSelection();
    public JLabel btn_selection=null;
    
    public PanelDrivers(){
        initComponents();
    }
    
    public void reset(){
        this.setDrivers();
    }
    
    private void setDrivers(){
        this.panel_drivers.removeAll();
        this.panel_drivers.updateUI();
        int width_total=this.panel_drivers_scroll.getWidth()-20, height_total=0;
        int width=width_total, height=width;
        int x=0, y=0;
        for(Map.Entry<DriverType,Class<?>> entry:this.drivers.entrySet()){
            DriverType type=entry.getKey();
            Class<?> class_type=entry.getValue();
            Driver driver=null;
            try{
                driver=(Driver)class_type.getDeclaredConstructor().newInstance();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            if(driver==null){
                continue;
            }
            
            JLabel btn=new JLabel();
            btn.setBounds(x,y,width,height);
            btn.setBackground(Color.WHITE);
            btn.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(driver.src_icon)).getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT)));
            btn.setToolTipText(type.toString());
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
                    selection.driver=class_type;
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
            
            this.panel_drivers.add(btn);
        }
        this.panel_drivers.updateUI();
        this.panel_drivers.setPreferredSize(Function.createDimencion(width_total,height_total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_drivers_scroll = new javax.swing.JScrollPane();
        panel_drivers = new javax.swing.JPanel();

        panel_drivers_scroll.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panel_drivers_scrollComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panel_driversLayout = new javax.swing.GroupLayout(panel_drivers);
        panel_drivers.setLayout(panel_driversLayout);
        panel_driversLayout.setHorizontalGroup(
            panel_driversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        panel_driversLayout.setVerticalGroup(
            panel_driversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        panel_drivers_scroll.setViewportView(panel_drivers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_drivers_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_drivers_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void panel_drivers_scrollComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_drivers_scrollComponentResized
        this.setDrivers();
    }//GEN-LAST:event_panel_drivers_scrollComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel_drivers;
    private javax.swing.JScrollPane panel_drivers_scroll;
    // End of variables declaration//GEN-END:variables
}
