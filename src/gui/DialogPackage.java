package gui;

import enums.EtherType;
import gui.panels.PanelScenery;
import objects.drivers.Driver;

/**
 *
 * @author dogiloki
 */

public class DialogPackage extends javax.swing.JDialog{
    
    public PanelScenery scenery;
    public Driver driver;

    public DialogPackage(java.awt.Frame parent, boolean modal, PanelScenery scenery) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.scenery=scenery;
        this.driver=this.scenery.selection.driver;
        for(EtherType value:EtherType.values()){
            this.box_type_ether.addItem(value.toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        box_source_mac = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        box_destination = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        box_type_ether = new javax.swing.JComboBox<>();
        btn_accept = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        box_message = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Dirección del emisor");

        box_source_mac.setEnabled(false);

        jLabel2.setText("Dirección de destinatario");

        jLabel3.setText("Tipo de protocolo");

        box_type_ether.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                box_type_etherItemStateChanged(evt);
            }
        });

        btn_accept.setText("Aceptar");
        btn_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_acceptActionPerformed(evt);
            }
        });

        jLabel4.setText("Mensaje");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(box_source_mac)
                    .addComponent(box_destination)
                    .addComponent(box_type_ether, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_accept))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 227, Short.MAX_VALUE))
                    .addComponent(box_message))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_source_mac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_destination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_type_ether, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_message, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_accept)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_acceptActionPerformed
        EtherType type=EtherType.valueOf(this.box_type_ether.getSelectedItem().toString());
        this.driver.createPackage(type,this.box_destination.getText(),this.box_message.getText());
        dispose();
    }//GEN-LAST:event_btn_acceptActionPerformed

    private void box_type_etherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_box_type_etherItemStateChanged
        EtherType type=EtherType.valueOf(this.box_type_ether.getSelectedItem().toString());
        String source;
        switch(type){
            case IPv4: source=this.driver.mac; break;
            case TCP: source=this.driver.ipv4_public; break;
            default: source="";
        }
        this.box_source_mac.setText(source);
    }//GEN-LAST:event_box_type_etherItemStateChanged

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogPackage dialog = new DialogPackage(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JTextField box_destination;
    private javax.swing.JTextField box_message;
    private javax.swing.JTextField box_source_mac;
    private javax.swing.JComboBox<String> box_type_ether;
    private javax.swing.JButton btn_accept;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
