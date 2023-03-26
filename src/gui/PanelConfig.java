package gui;

import java.lang.reflect.Field;

/**
 *
 * @author dogiloki
 */

public class PanelConfig extends javax.swing.JPanel{

    public PanelScenery scenery;
    
    public PanelConfig(PanelScenery scenery){
        initComponents();
        this.scenery=scenery;
        Field[] fields=this.scenery.selection.driver.getClass().getDeclaredFields();
        for(Field field:fields){
            System.out.println(field.getName());
        }
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
