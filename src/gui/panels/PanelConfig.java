package gui.panels;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import multitaks.Function;
import multitaks.annotations.directory.Key;
import objects.drivers.Driver;
import objects.drivers.anotations.Config;
import services.DNS;

/**
 *
 * @author dogiloki
 */

public class PanelConfig extends javax.swing.JPanel{

    public PanelScenery scenery;
    private Map<Field,JTextField>list_text=new HashMap<>();
    private Driver driver;
    
    public PanelConfig(PanelScenery scenery){
        initComponents();
        this.scenery=scenery;
        this.driver=this.scenery.selection.driver;
    }
    
    public void reset(){
        this.setConfig();
    }
    
    public void setConfig(){
        this.panel_config.removeAll();
        this.panel_config.updateUI();
        int x=10, y=10;
        int width_total=this.panel_config_scroll.getWidth()-20-x, height_total=y;
        int width=width_total, height=50;
        Field[] fields=this.driver.getClass().getFields();
        for(Field field:fields){
            Config annot_config=field.getAnnotation(Config.class);
            Key annot_key=field.getAnnotation(Key.class);
            if(annot_config instanceof Config && annot_key instanceof Key){
                Object value=null;
                try{
                    value=field.get(this.driver);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                JPanel panel=new JPanel();
                panel.setLayout(null);
                panel.setBounds(x,y,width,height);
                
                JLabel label=new JLabel(annot_config.label());
                label.setBounds(0,0,width,15);
                panel.add(label);
                switch(annot_config.box()){
                    case TEXT:{
                        JTextField text=new JTextField((String)value);
                        text.setBounds(0,15,width,25);
                        text.addFocusListener(new FocusListener(){
                            @Override
                            public void focusGained(FocusEvent evt){}
                            
                            @Override
                            public void focusLost(FocusEvent evt){
                                save();
                            }
                        });
                        panel.add(text);
                        this.list_text.put(field,text);
                        break;
                    }
                }
                y+=height;
                height_total=y;
                
                this.panel_config.add(panel);
            }
        }
        this.panel_config.updateUI();
        this.panel_config.setPreferredSize(Function.createDimencion(width_total,height_total));
    }
    
    private void save(){
        for(Map.Entry<Field,JTextField> entry:this.list_text.entrySet()){
            Field field=entry.getKey();
            JTextField text=entry.getValue();
            try{
                field.set(this.driver,text.getText());
                Config annot_config=field.getAnnotation(Config.class);
                if(annot_config.id().equals("ipv4_public")){
                    DNS.put(text.getText(),this.driver);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_config_scroll = new javax.swing.JScrollPane();
        panel_config = new javax.swing.JPanel();

        panel_config_scroll.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panel_config_scrollComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panel_configLayout = new javax.swing.GroupLayout(panel_config);
        panel_config.setLayout(panel_configLayout);
        panel_configLayout.setHorizontalGroup(
            panel_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );
        panel_configLayout.setVerticalGroup(
            panel_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
        );

        panel_config_scroll.setViewportView(panel_config);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_config_scroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_config_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void panel_config_scrollComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_config_scrollComponentResized
        this.setConfig();
    }//GEN-LAST:event_panel_config_scrollComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel_config;
    private javax.swing.JScrollPane panel_config_scroll;
    // End of variables declaration//GEN-END:variables
}
