package gui;

import gui.panels.PanelScenery;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import objects.drivers.Driver;
import protocols.mail.MailUser;
/**
 *
 * @author dogi_
 */

public class DialogMail extends javax.swing.JDialog {
    
    public PanelScenery scenery;
    public Driver driver;
    
    public DialogMail(java.awt.Frame parent, boolean modal, PanelScenery scenery) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.scenery=scenery;
        if(scenery.selection.driver==null){
           return; 
        }
        this.driver=scenery.selection.driver;
        if(this.driver.mail_server==null){
            return;
        }
        this.setTitle(this.driver.ipv4_public);
        this.getMailUsers();
    }
    
    public void getMailUsers(){
        DefaultTableModel model_mail=new DefaultTableModel();
        model_mail.addColumn("Correo electrónico");
        model_mail.addColumn("Contraseña");
        this.driver.mail_server.getUsers().forEach((address,mail_user)->{
            String[] data={
                address,
                mail_user.password
            };
            model_mail.addRow(data);
        });
        this.table_mails.setModel(model_mail);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        table_mails = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table_mails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(table_mails);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogMail dialog = new DialogMail(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_mails;
    // End of variables declaration//GEN-END:variables
}
