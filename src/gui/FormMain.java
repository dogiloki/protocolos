package gui;

import enums.EtherType;
import enums.PackageType;
import gui.panels.PanelDrivers;
import gui.panels.PanelScenery;
import enums.ToolType;
import gui.panels.PanelConfig;
import gui.panels.PanelConnectors;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import multitaks.directory.ModelDirectory;
import objects.drivers.Driver;
import protocols.PackageEther;
import protocols.mail.Mail;
import protocols.mail.UserPackaging;

/**
 *
 * @author dogi_
 */

public final class FormMain extends javax.swing.JFrame{
    
    public PanelDrivers drivers;
    public PanelScenery scenery;
    private final ModelDirectory model=new ModelDirectory();
    private Map<String,PackageEther> number_package_sending=new HashMap<>();
    private Map<String,PackageEther> number_package_receiving=new HashMap<>();
    private Map<String,PackageEther> number_package_sent=new HashMap<>();
    
    public FormMain() {
        initComponents();
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.scenery=new PanelScenery();
        this.drivers=new PanelDrivers();
        // Obtener datos del escenario
        this.model.run(this.scenery.scenery,"temp_escenary.json");
        this.setPanel(this.panel_drivers,this.drivers);
        this.setPanel(this.panel_scenery,this.scenery);
    }
    
    public void setPanel(JPanel panel1, JPanel panel2){
        panel2.setVisible(true);
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.removeAll();
        panel1.add(panel2);
        panel1.updateUI();
    }
    
    public void resizablePanel(JPanel panel1, JPanel panel2){
        panel2.setBounds(0,0,panel1.getWidth(),panel1.getHeight());
        panel1.updateUI();
    }
    
    public void selectDriver(MouseEvent evt){
        this.scenery.selection.drivers.clear();
        this.scenery.getDriver(evt.getX(),evt.getY(),(driver)->{
            this.scenery.selection.x=evt.getX();
            this.scenery.selection.y=evt.getY();
            this.scenery.selection.off_set_x=evt.getX()-driver.x;
            this.scenery.selection.off_set_y=evt.getY()-driver.y;
            // Selección de dispositivo
            this.scenery.selection.driver_prev=this.scenery.selection.driver;
            this.scenery.selection.driver=driver;
            Driver driver1=this.scenery.selection.driver_prev;
            Driver driver2=this.scenery.selection.driver;
            // Selección de varios dispositivos
            this.scenery.selection.drivers.add(driver);
            if(evt.getButton()==3){
                // Mostrar conectores
                this.scenery.connectors=new PanelConnectors(this.scenery);
                this.scenery.connectors.setBounds(driver.x,driver.y,50,150);
                this.scenery.add(this.scenery.connectors);
                this.scenery.selection.tool=ToolType.CONNECT;
            }else{
                if(this.scenery.selection.tool==ToolType.DEFAULT){
                    this.scenery.selection.tool=ToolType.MOVE;
                }
            }
            // Mostrar contenido del dispositivo seleccionado
            this.setPanel(this.panel_entries,new PanelConnectors(this.scenery));
            this.setPanel(this.panel_config,new PanelConfig(this.scenery));
            this.getTransporte(driver);
            this.getLog(driver);
            this.box_package_log.setText("");
            if(driver.client_smtp==null){
                this.panel_protocols.setEnabled(rootPaneCheckingEnabled);
            }else{
                this.box_smtp_host.setText(driver.client_smtp.server_smtp);
            }
        });
        this.panel_scenery.updateUI();
    }
    
    public void getLog(Driver driver){
        this.box_log.setLineWrap(true);
        this.box_log.setText(driver.getLog());
    }
    
    public void getTransporte(Driver driver){
        this.getSendingPackages(driver);
        this.getReceivingPackages(driver);
        this.getSentPackages(driver);
    }
    
    public void getSendingPackages(Driver driver){
        DefaultTableModel model_sending=new DefaultTableModel();
        model_sending.addColumn("Número");
        model_sending.addColumn("Origen");
        model_sending.addColumn("Destino");
        model_sending.addColumn("Tipo");
        driver.sending_packages.forEach((connecot,list_packages)->{
            list_packages.forEach((pack)->{
                String[] data={
                    String.valueOf(pack.header.sequence_number),
                    pack.header.source_driver,
                    pack.header.destination_driver,
                    pack.header.type.toString()
                };
                model_sending.addRow(data);
                this.number_package_sending.put(data[0],pack);
            });
        });
        this.table_packages_sending.setModel(model_sending);
    }
    
    public void getReceivingPackages(Driver driver){
        DefaultTableModel model_receiving=new DefaultTableModel();
        model_receiving.addColumn("Número");
        model_receiving.addColumn("Origen");
        model_receiving.addColumn("Destino");
        model_receiving.addColumn("Tipo");
        driver.receiving_packages.forEach((connecot,list_packages)->{
            list_packages.forEach((pack)->{
                String[] data={
                    String.valueOf(pack.header.sequence_number),
                    pack.header.source_driver,
                    pack.header.destination_driver,
                    pack.header.type.toString()
                };
                model_receiving.addRow(data);
                this.number_package_receiving.put(data[0],pack);
            });
        });
        this.table_packages_receiving.setModel(model_receiving);
    }
    
    public void getSentPackages(Driver driver){
        DefaultTableModel model_receiving=new DefaultTableModel();
        model_receiving.addColumn("Número");
        model_receiving.addColumn("Origen");
        model_receiving.addColumn("Destino");
        model_receiving.addColumn("Tipo");
        driver.sent_packages.forEach((connecot,list_packages)->{
            list_packages.forEach((pack)->{
                String[] data={
                    String.valueOf(pack.header.sequence_number),
                    pack.header.source_driver,
                    pack.header.destination_driver,
                    pack.header.type.toString()
                };
                model_receiving.addRow(data);
                this.number_package_sent.put(data[0],pack);
            });
        });
        this.table_packages_sent.setModel(model_receiving);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_scenery = new javax.swing.JPanel();
        panel_drivers = new javax.swing.JPanel();
        panel_protocols = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        btn_package = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_packages_sending = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_packages_sent = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_packages_receiving = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        box_package_log = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btn_dhcp = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btn_create_mail = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        box_smtp_host = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        box_smtp_email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        box_smtp_password = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        box_smtp_email_destination = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        box_smtp_subject = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        box_smtp_body = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        box_pop_host = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        box_pop_email = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        box_pop_password = new javax.swing.JTextField();
        btn_smtp = new javax.swing.JButton();
        btn_pop = new javax.swing.JButton();
        panel_connectors = new javax.swing.JPanel();
        panel_entries = new javax.swing.JPanel();
        panel_config = new javax.swing.JPanel();
        btn_sumulator = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        box_log = new javax.swing.JTextArea();
        btn_log_remove = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btn_delete = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        panel_scenery.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_scenery.setMaximumSize(new java.awt.Dimension(700, 700));
        panel_scenery.setMinimumSize(new java.awt.Dimension(0, 0));
        panel_scenery.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel_sceneryMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                panel_sceneryMouseMoved(evt);
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
            .addGap(0, 62, Short.MAX_VALUE)
        );
        panel_driversLayout.setVerticalGroup(
            panel_driversLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btn_package.setText("Crear paquete");
        btn_package.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_packageActionPerformed(evt);
            }
        });

        table_packages_sending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_packages_sending.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_packages_sendingMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_packages_sending);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Paquetes enviandose", jPanel1);

        table_packages_sent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_packages_sent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_packages_sentMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(table_packages_sent);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Paquetes enviados", jPanel8);

        table_packages_receiving.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_packages_receiving.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_packages_receivingMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(table_packages_receiving);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Paquetes recibidos", jPanel5);

        box_package_log.setColumns(20);
        box_package_log.setRows(5);
        jScrollPane4.setViewportView(box_package_log);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_package, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_package)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
        );

        panel_protocols.addTab("Transporte", jPanel4);

        btn_dhcp.setText("DCHP");
        btn_dhcp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dhcpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_dhcp, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_dhcp)
                .addContainerGap(329, Short.MAX_VALUE))
        );

        panel_protocols.addTab("Red", jPanel3);

        btn_create_mail.setText("Tabla de emails");
        btn_create_mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_mailActionPerformed(evt);
            }
        });

        jLabel1.setText("Servidor de correo (IPv4 pública)");

        jLabel2.setText("Correo eléctronico");

        jLabel3.setText("Contraseña");

        jLabel4.setText("Correo electrónico de destino");

        jLabel5.setText("Asunto");

        jLabel6.setText("Cuerpo");

        box_smtp_body.setColumns(20);
        box_smtp_body.setRows(5);
        jScrollPane5.setViewportView(box_smtp_body);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(box_smtp_host)
                    .addComponent(box_smtp_email)
                    .addComponent(box_smtp_password)
                    .addComponent(box_smtp_email_destination)
                    .addComponent(box_smtp_subject)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_smtp_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_smtp_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_smtp_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_smtp_email_destination, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_smtp_subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("SMTP (enviar correos)", jPanel6);

        jLabel7.setText("Servidor de correo (IPv4 pública)");

        jLabel8.setText("Correo eléctronico");

        jLabel9.setText("Contraseña");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(box_pop_host)
                    .addComponent(box_pop_email)
                    .addComponent(box_pop_password)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 147, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_pop_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_pop_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box_pop_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("POP (recibir correos)", jPanel7);

        btn_smtp.setText("SMTP");
        btn_smtp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_smtpActionPerformed(evt);
            }
        });

        btn_pop.setText("POP");
        btn_pop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_popActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_create_mail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_smtp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pop)
                .addContainerGap())
            .addComponent(jTabbedPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_create_mail)
                    .addComponent(btn_smtp)
                    .addComponent(btn_pop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        panel_protocols.addTab("Vínculo de datos", jPanel2);

        javax.swing.GroupLayout panel_entriesLayout = new javax.swing.GroupLayout(panel_entries);
        panel_entries.setLayout(panel_entriesLayout);
        panel_entriesLayout.setHorizontalGroup(
            panel_entriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        panel_entriesLayout.setVerticalGroup(
            panel_entriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_connectorsLayout = new javax.swing.GroupLayout(panel_connectors);
        panel_connectors.setLayout(panel_connectorsLayout);
        panel_connectorsLayout.setHorizontalGroup(
            panel_connectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_connectorsLayout.createSequentialGroup()
                .addComponent(panel_entries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 255, Short.MAX_VALUE))
        );
        panel_connectorsLayout.setVerticalGroup(
            panel_connectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_entries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel_protocols.addTab("Conectores", panel_connectors);

        javax.swing.GroupLayout panel_configLayout = new javax.swing.GroupLayout(panel_config);
        panel_config.setLayout(panel_configLayout);
        panel_configLayout.setHorizontalGroup(
            panel_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
        );
        panel_configLayout.setVerticalGroup(
            panel_configLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );

        panel_protocols.addTab("Configuración", panel_config);

        btn_sumulator.setText("Simulación");
        btn_sumulator.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btn_sumulatorItemStateChanged(evt);
            }
        });

        box_log.setColumns(20);
        box_log.setRows(5);
        jScrollPane3.setViewportView(box_log);

        btn_log_remove.setText("Limpiar");
        btn_log_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_log_removeActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem6.setText("Guardar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Opciones");

        btn_delete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        btn_delete.setText("Eliminar dispositivo");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jMenu3.add(btn_delete);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_protocols)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_log_remove)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_scenery, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_drivers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_sumulator)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btn_sumulator)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_scenery, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addComponent(panel_drivers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_protocols, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_log_remove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel_sceneryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMousePressed
        if(this.scenery.connectors!=null){
            this.scenery.remove(this.scenery.connectors);
            this.scenery.connectors.updateUI();
            this.scenery.connectors=null;
        }
        if(this.drivers.selection.driver!=null){
            try{
                Driver driver=(Driver)this.drivers.selection.driver.getDeclaredConstructor().newInstance();
                driver.x=evt.getX();
                driver.y=evt.getY();
                this.scenery.addDriver(driver);
                this.drivers.selection.driver=null;
                this.drivers.reset();
                this.panel_scenery.updateUI();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        this.selectDriver(evt);
    }//GEN-LAST:event_panel_sceneryMousePressed

    private void panel_sceneryMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMouseDragged
        if(this.scenery.selection.tool!=ToolType.MOVE){
            return;
        }
        Driver driver=this.scenery.selection.driver;
        driver.x=evt.getX()-this.scenery.selection.off_set_x;
        driver.y=evt.getY()-this.scenery.selection.off_set_y;
        this.panel_scenery.updateUI();
    }//GEN-LAST:event_panel_sceneryMouseDragged

    private void panel_sceneryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMouseReleased
        if(this.scenery.selection.tool==ToolType.MOVE){
            this.scenery.selection.tool=ToolType.DEFAULT;
        }
    }//GEN-LAST:event_panel_sceneryMouseReleased

    private void panel_sceneryMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_sceneryMouseMoved
        this.scenery.selection.mouse_x=evt.getX();
        this.scenery.selection.mouse_y=evt.getY();
    }//GEN-LAST:event_panel_sceneryMouseMoved

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        this.resizablePanel(this.panel_drivers,this.drivers);
    }//GEN-LAST:event_formComponentResized

    private void btn_sumulatorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btn_sumulatorItemStateChanged
        if(evt.getStateChange()==ItemEvent.SELECTED){
            this.scenery.start(this);
        }else{
            this.scenery.stop();
        }
    }//GEN-LAST:event_btn_sumulatorItemStateChanged

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        this.scenery.removeDriver();
        this.panel_scenery.updateUI();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_packageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_packageActionPerformed
        Driver driver=this.scenery.selection.driver;
        if(driver==null){
            return;
        }
        new DialogPackage(this,true,this.scenery).setVisible(true);
        this.getSendingPackages(driver);
    }//GEN-LAST:event_btn_packageActionPerformed

    private void btn_dhcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dhcpActionPerformed
        Driver driver=this.scenery.selection.driver;
        if(driver==null || driver.dhcp==null){
            return;
        }
        driver.dhcp.ip();
        this.getLog(driver);
    }//GEN-LAST:event_btn_dhcpActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.model.save();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void btn_log_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_log_removeActionPerformed
        Driver driver=this.scenery.selection.driver;
        if(driver==null){
            return;
        }
        driver.setLog("");
        this.getLog(driver);
    }//GEN-LAST:event_btn_log_removeActionPerformed

    private void table_packages_receivingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_packages_receivingMousePressed
        int row=this.table_packages_receiving.getSelectedRow();
        PackageEther pack=this.number_package_receiving.get(this.table_packages_receiving.getValueAt(row,0));
        this.box_package_log.setText(new String(pack.data.get(0).data,StandardCharsets.UTF_8));
    }//GEN-LAST:event_table_packages_receivingMousePressed

    private void table_packages_sendingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_packages_sendingMousePressed
        int row=this.table_packages_sending.getSelectedRow();
        PackageEther pack=this.number_package_sending.get(this.table_packages_sending.getValueAt(row,0));
        this.box_package_log.setText(new String(pack.data.get(0).data,StandardCharsets.UTF_8));
    }//GEN-LAST:event_table_packages_sendingMousePressed

    private void btn_create_mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_create_mailActionPerformed
        new DialogMail(this,true,this.scenery).setVisible(true);
    }//GEN-LAST:event_btn_create_mailActionPerformed

    private void btn_smtpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_smtpActionPerformed
        Driver driver=this.scenery.selection.driver;
        if(driver==null || driver.client_smtp==null){
            return;
        }
        Mail mail=new Mail();
        mail.mail_sender=this.box_smtp_email.getText();
        mail.mail_recipient=this.box_smtp_email_destination.getText();
        mail.subject=this.box_smtp_subject.getText();
        mail.body=this.box_smtp_body.getText();
        driver.client_smtp.connect(this.box_smtp_host.getText());
        driver.client_smtp.addMail(this.box_smtp_password.getText(),mail);
        driver.createPackage(PackageType.SMTP,EtherType.TCP,driver.client_smtp.server_smtp,driver.client_smtp.getMail());
    }//GEN-LAST:event_btn_smtpActionPerformed

    private void table_packages_sentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_packages_sentMousePressed
        int row=this.table_packages_sent.getSelectedRow();
        PackageEther pack=this.number_package_sent.get(this.table_packages_sent.getValueAt(row,0));
        this.box_package_log.setText(new String(pack.data.get(0).data,StandardCharsets.UTF_8));
    }//GEN-LAST:event_table_packages_sentMousePressed

    private void btn_popActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_popActionPerformed
        Driver driver=this.scenery.selection.driver;
        if(driver==null || driver.client_smtp==null){
            return;
        }
        UserPackaging user_pack=new UserPackaging(this.box_pop_email.getText(),this.box_pop_password.getText());
        driver.client_pop.connect(this.box_pop_host.getText());
        driver.client_pop.addUser(this.box_pop_email.getText(),this.box_pop_password.getText());
        driver.createPackage(PackageType.POP,EtherType.TCP,driver.client_pop.server_pop,user_pack);
    }//GEN-LAST:event_btn_popActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea box_log;
    private javax.swing.JTextArea box_package_log;
    private javax.swing.JTextField box_pop_email;
    private javax.swing.JTextField box_pop_host;
    private javax.swing.JTextField box_pop_password;
    private javax.swing.JTextArea box_smtp_body;
    private javax.swing.JTextField box_smtp_email;
    private javax.swing.JTextField box_smtp_email_destination;
    private javax.swing.JTextField box_smtp_host;
    private javax.swing.JTextField box_smtp_password;
    private javax.swing.JTextField box_smtp_subject;
    private javax.swing.JButton btn_create_mail;
    private javax.swing.JMenuItem btn_delete;
    private javax.swing.JButton btn_dhcp;
    private javax.swing.JButton btn_log_remove;
    private javax.swing.JButton btn_package;
    private javax.swing.JButton btn_pop;
    private javax.swing.JButton btn_smtp;
    private javax.swing.JToggleButton btn_sumulator;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel panel_config;
    private javax.swing.JPanel panel_connectors;
    private javax.swing.JPanel panel_drivers;
    private javax.swing.JPanel panel_entries;
    private javax.swing.JTabbedPane panel_protocols;
    public javax.swing.JPanel panel_scenery;
    private javax.swing.JTable table_packages_receiving;
    private javax.swing.JTable table_packages_sending;
    private javax.swing.JTable table_packages_sent;
    // End of variables declaration//GEN-END:variables
}
