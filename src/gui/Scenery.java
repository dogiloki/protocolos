package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import objects.Entity;

/**
 *
 * @author dogi_
 */

public class Scenery extends javax.swing.JPanel{

    public interface Callback{
        public void execute(Entity entity);
    }
    
    public List<Entity> entities=new ArrayList<>();
    
    public Scenery(){
        initComponents();
    }
    
    @Override
    public void paint(Graphics g){
        this.entities.forEach((entity)->{
            ImageIcon icon=new ImageIcon(new ImageIcon(this.getClass().getResource(entity.src_icon)).getImage().getScaledInstance(entity.width, entity.height,Image.SCALE_DEFAULT));
            //JLabel label=new JLabel();
            //label.setBounds(entity.x,entity.y,entity.width,entity.height);
            //label.setIcon(icon);
            icon.paintIcon(this,g,entity.x,entity.y);
        });
    }
    
    public void getEntity(int x, int y, Callback action){
        this.entities.forEach((entity)->{
            if(x>=entity.x && x<=entity.width && y>=entity.y && y<=entity.height){
                action.execute(entity);
                return;
            }
        });
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
