package gui;

import javax.swing.JPanel;

/**
 *
 * @author dogi_
 */

public class DialogDriver extends javax.swing.JDialog{

    public PanelScenery scenery;
    public PanelConnectors connectors;
    public PanelConfig config;
    
    public DialogDriver(java.awt.Frame parent, boolean modal, PanelScenery scenery){
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.scenery=scenery;
        this.connectors=new PanelConnectors(this.scenery);
        this.config=new PanelConfig(this.scenery);
        this.setPanel(this.panel_entries,this.connectors);
        this.setPanel(this.panel_config,this.config);
    }
    
    public void setPanel(JPanel panel1, JPanel panel2){
        panel2.setVisible(true);
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.removeAll();
        panel1.add(panel2);
        panel1.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_connectors = new javax.swing.JPanel();
        panel_entries = new javax.swing.JPanel();
        panel_config = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout panel_entriesLayout = new javax.swing.GroupLayout(panel_entries);
        panel_entries.setLayout(panel_entriesLayout);
        panel_entriesLayout.setHorizontalGroup(
            panel_entriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        panel_entriesLayout.setVerticalGroup(
            panel_entriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_connectorsLayout = new javax.swing.GroupLayout(panel_connectors);
        panel_connectors.setLayout(panel_connectorsLayout);
        panel_connectorsLayout.setHorizontalGroup(
            panel_connectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_connectorsLayout.createSequentialGroup()
                .addComponent(panel_entries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 455, Short.MAX_VALUE))
        );
        panel_connectorsLayout.setVerticalGroup(
            panel_connectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_entries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Conectores", panel_connectors);

        javax.swing.GroupLayout panel_configLayout = new javax.swing.GroupLayout(panel_config);
        panel_config.setLayout(panel_configLayout);
        panel_configLayout.setHorizontalGroup(
            panel_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );
        panel_configLayout.setVerticalGroup(
            panel_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Configuración", panel_config);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                DialogDriver dialog = new DialogDriver(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel_config;
    private javax.swing.JPanel panel_connectors;
    private javax.swing.JPanel panel_entries;
    // End of variables declaration//GEN-END:variables
}
