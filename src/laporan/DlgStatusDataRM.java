/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */

package laporan;

import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import fungsi.batasInput;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariCaraBayar;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgCariBangsal;
import simrskhanza.DlgCariCaraBayar1;

/**
 *
 * @author perpustakaan
 */
public final class DlgStatusDataRM extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private DlgCariCaraBayar penjab=new DlgCariCaraBayar(null,false);
    private DlgCariCaraBayar1 penjab1=new DlgCariCaraBayar1(null,false);
    private DlgCariBangsal bangsal=new DlgCariBangsal(null,false);
    private int i=0,adasoapiralan=0,tidakadasoapiralan=0,adasoapiranap=0,tidakadasoapiranap=0,adaresumeralan=0,tidakadaresumeralan=0,
            adaresumeranap=0,tidakadaresumeranap=0,adatriaseigd=0,tidakadatriaseigd=0,adaaskepigd=0,tidakadaaskepigd=0,adaicd10=0,tidakadaicd10=0,
            adaicd9=0,tidakadaicd9=0,adaLaboratorium=0,tidakadaLaboratorium=0,adaRadiologi=0,tidakadaRadiologi=0,adasuratkematian=0,tidakadasuratkematian=0;  
    private String soapiralan="",soapiranap="",resumeralan="",resumeranap="",pilihan="",triaseigd="",askepigd="",icd10="",icd9="",Laboratorium="",Radiologi="",order="",kamar="",suratkematian="",dokter="";
    private StringBuilder htmlContent;
//    private Object kdbangsal;
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgStatusDataRM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","Tanggal","Dokter Dituju","Nomer RM","Pasien","Poliklinik","SOAPI Ralan","Resume Ralan","Triase IGD","Askep IGD","ICD 10","ICD 9","Laboratorium","Radiologi","Surat Kematian","order"
        }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal.setModel(tabMode);

        tbBangsal.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16 ; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(150);   
            }else if(i==5){
                column.setPreferredWidth(133);
            
            }else if(i==6){
                column.setPreferredWidth(75);
            }else if(i==7){
                column.setPreferredWidth(80);
            }else if(i==8){
                column.setPreferredWidth(64);
            }else if(i==9){
                column.setPreferredWidth(54);
            }else if(i==10){
                column.setPreferredWidth(54);
            }
            else if(i==11){
                column.setPreferredWidth(90);
            }
            else if(i==12){
                column.setPreferredWidth(90);
            }
            else if(i==13){
               
               column.setPreferredWidth(90);
            }
            else if(i==14){
              
               column.setPreferredWidth(100);
            }
             else if(i==15){
                column.setMinWidth(0);
               column.setMaxWidth(0);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new Object[]{
            "No.Rawat","Tanggal","DPJP","Nomer RM","Pasien","Kamar","SOAPI Ralan","SOAPI Ranap","Resume Ralan","Resume Ranap","Triase IGD","Askep IGD","ICD 10","ICD 9","Laboratorium","Radiologi","Surat Kematian","order"
        }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal1.setModel(tabMode2);

        tbBangsal1.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbBangsal1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 18; i++) {
            TableColumn column = tbBangsal1.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(150);   
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(75);
            }else if(i==7){
                column.setPreferredWidth(78);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(74);
            }else if(i==10){
                column.setPreferredWidth(64);
            }else if(i==11){
                column.setPreferredWidth(54);
            }else if(i==12){
                column.setPreferredWidth(54);
            }
            else if(i==13){
                column.setPreferredWidth(90);
            }
            else if(i==14){
                column.setPreferredWidth(90);
            }
            else if(i==15){
                column.setPreferredWidth(100);
            }
            else if(i==16){
               
               column.setPreferredWidth(100);
            }
             else if(i==17){
               column.setMinWidth(0);
               column.setMaxWidth(0);
            }
        }
        
        TCari.setDocument(new batasInput((int)90).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
//                        tampil();
                        if(TabStatus.getSelectedIndex()==0){
                            tampil();
                        }else if(TabStatus.getSelectedIndex()==1){
                            tampil2();
                        }

                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
//                        tampil();
                        if(TabStatus.getSelectedIndex()==0){
                            tampil();
                        }else if(TabStatus.getSelectedIndex()==1){
                            tampil2();
                        }
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
//                        tampil();
                        if(TabStatus.getSelectedIndex()==0){
                            tampil();
                        }else if(TabStatus.getSelectedIndex()==1){
                            tampil2();
                        }
                    }
                }
            });
        }  
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){
                    kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                    nmpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                }      
                kdpoli.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {poli.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });   
        
         
        
        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penjab.getTable().getSelectedRow()!= -1){
                    kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(),1).toString());
                    nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(),2).toString());
                }      
                kdpenjab.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {penjab.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });   
        
        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    penjab.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        penjab1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penjab1.getTable().getSelectedRow()!= -1){
                    kdpenjab1.setText(penjab1.getTable().getValueAt(penjab1.getTable().getSelectedRow(),1).toString());
                    nmpenjab1.setText(penjab1.getTable().getValueAt(penjab1.getTable().getSelectedRow(),2).toString());
                }      
                kdpenjab1.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {penjab1.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });   
        
        penjab1.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    penjab1.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
         bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(bangsal.getTable().getSelectedRow()!= -1){
                    kdbangsal.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(),0).toString());
                    nmkamar.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(),1).toString());
                }      
                kdbangsal.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {bangsal.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });   
        
        bangsal.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    bangsal.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        ChkInput.setSelected(false);
        ChkInput1.setSelected(false);
        isForm();
        isForm2();
    }    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        kdpoli = new widget.TextBox();
        kdpenjab = new widget.TextBox();
        kdbangsal = new widget.TextBox();
        jLabel19 = new widget.Label();
        Status1 = new widget.ComboBox();
        jLabel18 = new widget.Label();
        Status = new widget.ComboBox();
        kdpenjab1 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        TabStatus = new javax.swing.JTabbedPane();
        internalFrame9 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.panelisi();
        label17 = new widget.Label();
        nmpoli = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        label19 = new widget.Label();
        nmpenjab = new widget.TextBox();
        BtnSeek3 = new widget.Button();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        internalFrame8 = new widget.InternalFrame();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput1 = new widget.CekBox();
        FormInput1 = new widget.panelisi();
        label22 = new widget.Label();
        nmpenjab1 = new widget.TextBox();
        BtnSeek6 = new widget.Button();
        label23 = new widget.Label();
        nmkamar = new widget.TextBox();
        BtnSeek7 = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbBangsal1 = new widget.Table();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N
        TKd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKdActionPerformed(evt);
            }
        });

        kdpoli.setEditable(false);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(75, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });

        kdpenjab.setEditable(false);
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.setPreferredSize(new java.awt.Dimension(75, 23));
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });

        kdbangsal.setEditable(false);
        kdbangsal.setName("kdbangsal"); // NOI18N
        kdbangsal.setPreferredSize(new java.awt.Dimension(75, 23));
        kdbangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbangsalKeyPressed(evt);
            }
        });

        jLabel19.setText("Status :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(75, 23));

        Status1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Ralan", "Ranap" }));
        Status1.setLightWeightPopupEnabled(false);
        Status1.setName("Status1"); // NOI18N
        Status1.setPreferredSize(new java.awt.Dimension(92, 23));

        jLabel18.setText("Status :");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(75, 23));

        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Ralan", "Ranap" }));
        Status.setLightWeightPopupEnabled(false);
        Status.setName("Status"); // NOI18N
        Status.setPreferredSize(new java.awt.Dimension(92, 23));
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        kdpenjab1.setEditable(false);
        kdpenjab1.setName("kdpenjab1"); // NOI18N
        kdpenjab1.setPreferredSize(new java.awt.Dimension(75, 23));
        kdpenjab1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjab1KeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Status Data Rekam Medis Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass5.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(155, 23));
        TCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCariActionPerformed(evt);
            }
        });
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnAll);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel7);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnKeluar);

        jPanel3.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabStatus.setBackground(new java.awt.Color(255, 255, 253));
        TabStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabStatus.setForeground(new java.awt.Color(50, 50, 50));
        TabStatus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabStatus.setName("TabStatus"); // NOI18N
        TabStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabStatusMouseClicked(evt);
            }
        });

        internalFrame9.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 65));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Filter Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 104));
        FormInput.setLayout(null);

        label17.setText("Asal Poli :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label17);
        label17.setBounds(6, 10, 60, 23);

        nmpoli.setEditable(false);
        nmpoli.setName("nmpoli"); // NOI18N
        nmpoli.setPreferredSize(new java.awt.Dimension(190, 23));
        FormInput.add(nmpoli);
        nmpoli.setBounds(71, 10, 190, 23);

        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek2);
        BtnSeek2.setBounds(266, 10, 28, 23);

        label19.setText("Cara Bayar :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(label19);
        label19.setBounds(299, 10, 100, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setName("nmpenjab"); // NOI18N
        nmpenjab.setPreferredSize(new java.awt.Dimension(190, 23));
        nmpenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmpenjabActionPerformed(evt);
            }
        });
        FormInput.add(nmpenjab);
        nmpenjab.setBounds(404, 10, 190, 23);

        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek3.setMnemonic('3');
        BtnSeek3.setToolTipText("Alt+3");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        BtnSeek3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek3KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek3);
        BtnSeek3.setBounds(600, 10, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame9.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setName("tbBangsal"); // NOI18N
        tbBangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsalMouseClicked(evt);
            }
        });
        tbBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBangsal);

        internalFrame9.add(Scroll, java.awt.BorderLayout.CENTER);

        TabStatus.addTab("Rawat Jalan", internalFrame9);

        internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput1.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 65));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setMnemonic('M');
        ChkInput1.setText(".: Filter Data");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setFocusable(false);
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_END);

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(100, 104));
        FormInput1.setLayout(null);

        label22.setText("Cara Bayar :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput1.add(label22);
        label22.setBounds(299, 10, 100, 23);

        nmpenjab1.setEditable(false);
        nmpenjab1.setName("nmpenjab1"); // NOI18N
        nmpenjab1.setPreferredSize(new java.awt.Dimension(190, 23));
        FormInput1.add(nmpenjab1);
        nmpenjab1.setBounds(404, 10, 190, 23);

        BtnSeek6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek6.setMnemonic('3');
        BtnSeek6.setToolTipText("Alt+3");
        BtnSeek6.setName("BtnSeek6"); // NOI18N
        BtnSeek6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek6ActionPerformed(evt);
            }
        });
        BtnSeek6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek6KeyPressed(evt);
            }
        });
        FormInput1.add(BtnSeek6);
        BtnSeek6.setBounds(600, 10, 28, 23);

        label23.setText("Kamar  :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput1.add(label23);
        label23.setBounds(-30, 10, 100, 23);

        nmkamar.setEditable(false);
        nmkamar.setName("nmkamar"); // NOI18N
        nmkamar.setPreferredSize(new java.awt.Dimension(190, 23));
        nmkamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmkamarActionPerformed(evt);
            }
        });
        FormInput1.add(nmkamar);
        nmkamar.setBounds(80, 10, 190, 23);

        BtnSeek7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek7.setMnemonic('3');
        BtnSeek7.setToolTipText("Alt+3");
        BtnSeek7.setName("BtnSeek7"); // NOI18N
        BtnSeek7.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek7ActionPerformed(evt);
            }
        });
        BtnSeek7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek7KeyPressed(evt);
            }
        });
        FormInput1.add(BtnSeek7);
        BtnSeek7.setBounds(270, 10, 28, 23);

        PanelInput1.add(FormInput1, java.awt.BorderLayout.CENTER);

        internalFrame8.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbBangsal1.setName("tbBangsal1"); // NOI18N
        tbBangsal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsal1MouseClicked(evt);
            }
        });
        tbBangsal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsal1KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbBangsal1);

        internalFrame8.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabStatus.addTab("Rawat Inap", internalFrame8);

        internalFrame1.add(TabStatus, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        switch (TabStatus.getSelectedIndex()) {
            case 0:
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnPrint.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {            
                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"+                    
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                );
                bg.close();

                File f;            
                BufferedWriter bw; 

                pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih laporan..!","Pilihan Cetak",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Laporan 1 (HTML)","Laporan 2 (WPS)","Laporan 3 (CSV)"},"Laporan 1 (HTML)");
                switch (pilihan) {
                    case "Laporan 1 (HTML)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "<tr class='isi'>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tanggal</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Dokter Dituju</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Nomer RM</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Pasien</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>Poliklinik</td>"+
//                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='43px'>Status</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ralan</td>"+
//                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ranap</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ralan</td>"+
//                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ranap</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Triase IGD</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Askep IGD</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 10</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 9</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Laboratorium</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Radiologi</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Surat Kematian</td>"+
                                    
                                "</tr>"
                            ); 
                            for(i=0;i<tabMode.getRowCount();i++){  
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,0)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,1)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,2)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,3)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,4)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,5)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,6)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,7)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,8)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,9)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,10)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,11)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,12)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,13)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,14)+"</td>"+
//                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,15)+"</td>"+
                                    "</tr>"
                                ); 
                            }            

                            f = new File("StatusDataRM.html");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write("<html>"+
                                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                                        "<body>"+
                                            "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                "<tr class='isi2'>"+
                                                    "<td valign='top' align='center'>"+
                                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                                        "<font size='2' face='Tahoma'>REKAP STATUS DATA RM PERIODE "+Tgl1.getSelectedItem()+" s.d. "+Tgl2.getSelectedItem()+"<br><br></font>"+        
                                                    "</td>"+
                                               "</tr>"+
                                            "</table>"+
                                            "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                htmlContent.toString()+
                                            "</table>"+
                                        "</body>"+                   
                                     "</html>"
                            );

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 2 (WPS)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "<tr class='isi'>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tanggal</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Dokter Dituju</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Nomer RM</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Pasien</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>Poliklinik</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>Kamar</td>"+   
//                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='43px'>Status</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ralan</td>"+
//                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ranap</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ralan</td>"+
//                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ranap</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Triase IGD</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Askep IGD</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 10</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 9</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Radiologi</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Surat Kematian</td>"+
                                        
                                "</tr>"
                            ); 
                            for(i=0;i<tabMode.getRowCount();i++){  
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,0)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,1)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,2)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,3)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,4)+"</td>"+
                                        "<td valign='top'>"+tabMode.getValueAt(i,5)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,6)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,7)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,8)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,9)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,10)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,11)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,12)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,13)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,14)+"</td>"+
//                                        "<td valign='top' align='center'>"+tabMode.getValueAt(i,15)+"</td>"+
                                    "</tr>"
                                ); 
                            }            

                            f = new File("StatusDataRM.wps");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write("<html>"+
                                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                                        "<body>"+
                                            "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                "<tr class='isi2'>"+
                                                    "<td valign='top' align='center'>"+
                                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                                        "<font size='2' face='Tahoma'>REKAP STATUS DATA RM PERIODE "+Tgl1.getSelectedItem()+" s.d. "+Tgl2.getSelectedItem()+"<br><br></font>"+        
                                                    "</td>"+
                                               "</tr>"+
                                            "</table>"+
                                            "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                htmlContent.toString()+
                                            "</table>"+
                                        "</body>"+                   
                                     "</html>"
                            );

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 3 (CSV)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "\"No.Rawat\";\"Tanggal\";\"Dokter Dituju\";\"Nomer RM\";\"Pasien\";\"Poliklinik\";\"Status\";\"SOAPI Ralan\";\"SOAPI Ranap\";\"Resume Ralan\";\"Resume Ranap\";\"Triase IGD\";\"Askep IGD\";\"ICD 10\";\"ICD 9\"\n"
                            ); 
                            for(i=0;i<tabMode.getRowCount();i++){  
                                htmlContent.append(                             
                                    "\""+tabMode.getValueAt(i,0)+"\";\""+tabMode.getValueAt(i,1)+"\";\""+tabMode.getValueAt(i,2)+"\";\""+tabMode.getValueAt(i,3)+"\";\""+tabMode.getValueAt(i,4)+"\";"+
                                    "\""+tabMode.getValueAt(i,5)+"\";\""+tabMode.getValueAt(i,6)+"\";\""+tabMode.getValueAt(i,7)+"\";\""+tabMode.getValueAt(i,8)+"\";\""+tabMode.getValueAt(i,9)+"\";"+
                                    "\""+tabMode.getValueAt(i,10)+"\";\""+tabMode.getValueAt(i,11)+"\";\""+tabMode.getValueAt(i,12)+"\";\""+tabMode.getValueAt(i,13)+"\";\""+tabMode.getValueAt(i,14)+"\"\n"
                                ); 
                            }            

                            f = new File("StatusDataRM.csv");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write(htmlContent.toString());

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break; 
                }                 
            } catch (Exception e) {
            }     
            this.setCursor(Cursor.getDefaultCursor());
        }break;
        
        case 1 :
                if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnPrint.requestFocus();
        }else if(tabMode2.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {            
                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"+                    
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                );
                bg.close();

                File f;            
                BufferedWriter bw; 

                pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih laporan..!","Pilihan Cetak",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Laporan 1 (HTML)","Laporan 2 (WPS)","Laporan 3 (CSV)"},"Laporan 1 (HTML)");
                switch (pilihan) {
                    case "Laporan 1 (HTML)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "<tr class='isi'>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tanggal</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Dokter DPJP</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Nomer RM</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Pasien</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>Kamar</td>"+
//                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='43px'>Status</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ralan</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ranap</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ralan</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ranap</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Triase IGD</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Askep IGD</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 10</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 9</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Laboratorium</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Radiologi</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Surat Kematian</td>"+
                                    
                                "</tr>"
                            ); 
                            for(i=0;i<tabMode2.getRowCount();i++){  
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,0)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,1)+"</td>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,2)+"</td>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,3)+"</td>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,4)+"</td>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,5)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,6)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,7)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,8)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,9)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,10)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,11)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,12)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,13)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,14)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,15)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,16)+"</td>"+
//                                      "<td valign='top' align='center'>"+tabMode2.getValueAt(i,17)+"</td>"+
                                    "</tr>"
                                ); 
                            }            

                            f = new File("StatusDataRM.html");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write("<html>"+
                                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                                        "<body>"+
                                            "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                "<tr class='isi2'>"+
                                                    "<td valign='top' align='center'>"+
                                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                                        "<font size='2' face='Tahoma'>REKAP STATUS DATA RM PERIODE "+Tgl1.getSelectedItem()+" s.d. "+Tgl2.getSelectedItem()+"<br><br></font>"+        
                                                    "</td>"+
                                               "</tr>"+
                                            "</table>"+
                                            "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                htmlContent.toString()+
                                            "</table>"+
                                        "</body>"+                   
                                     "</html>"
                            );

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 2 (WPS)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "<tr class='isi'>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tanggal</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Dokter Dituju</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Nomer RM</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Pasien</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>Poliklinik</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>Kamar</td>"+   
//                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='43px'>Status</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ralan</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>SOAPI Ranap</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ralan</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Resume Ranap</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Triase IGD</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Askep IGD</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 10</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>ICD 9</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Radiologi</td>"+
                                    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Surat Kematian</td>"+
                                        
                                "</tr>"
                            ); 
                            for(i=0;i<tabMode2.getRowCount();i++){  
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,0)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,1)+"</td>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,2)+"</td>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,3)+"</td>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,4)+"</td>"+
                                        "<td valign='top'>"+tabMode2.getValueAt(i,5)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,6)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,7)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,8)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,9)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,10)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,11)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,12)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,13)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,14)+"</td>"+
                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,15)+"</td>"+
                                         "<td valign='top' align='center'>"+tabMode2.getValueAt(i,16)+"</td>"+
//                                        "<td valign='top' align='center'>"+tabMode2.getValueAt(i,17)+"</td>"+
                                    "</tr>"
                                ); 
                            }            

                            f = new File("StatusDataRM.wps");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write("<html>"+
                                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                                        "<body>"+
                                            "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                "<tr class='isi2'>"+
                                                    "<td valign='top' align='center'>"+
                                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                                        "<font size='2' face='Tahoma'>REKAP STATUS DATA RM PERIODE "+Tgl1.getSelectedItem()+" s.d. "+Tgl2.getSelectedItem()+"<br><br></font>"+        
                                                    "</td>"+
                                               "</tr>"+
                                            "</table>"+
                                            "<table width='1408px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                                htmlContent.toString()+
                                            "</table>"+
                                        "</body>"+                   
                                     "</html>"
                            );

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 3 (CSV)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(                             
                                "\"No.Rawat\";\"Tanggal\";\"Dokter DPJP\";\"Nomer RM\";\"Pasien\";\"Kamar\";\"Status\";\"SOAPI Ralan\";\"SOAPI Ranap\";\"Resume Ralan\";\"Resume Ranap\";\"Triase IGD\";\"Askep IGD\";\"ICD 10\";\"ICD 9\";\"Laboratorium\";\"Radiologi\";\"Surat Kematian\"\n"
                            ); 
                            for(i=0;i<tabMode2.getRowCount();i++){  
                                htmlContent.append(                             
                                    "\""+tabMode2.getValueAt(i,0)+"\";\""+tabMode2.getValueAt(i,1)+"\";\""+tabMode2.getValueAt(i,2)+"\";\""+tabMode2.getValueAt(i,3)+"\";\""+tabMode2.getValueAt(i,4)+"\";"+
                                    "\""+tabMode2.getValueAt(i,5)+"\";\""+tabMode2.getValueAt(i,6)+"\";\""+tabMode2.getValueAt(i,7)+"\";\""+tabMode2.getValueAt(i,8)+"\";\""+tabMode2.getValueAt(i,9)+"\";"+
                                    "\""+tabMode2.getValueAt(i,10)+"\";\""+tabMode2.getValueAt(i,11)+"\";\""+tabMode2.getValueAt(i,12)+"\";\""+tabMode2.getValueAt(i,13)+"\";\""+tabMode2.getValueAt(i,14)+"\";"+
                                     "\""+tabMode2.getValueAt(i,15)+"\";\""+tabMode2.getValueAt(i,16)+"\"\n"
                                ); 
                            }            

                            f = new File("StatusDataRM.csv");            
                            bw = new BufferedWriter(new FileWriter(f));            
                            bw.write(htmlContent.toString());

                            bw.close();                         
                            Desktop.getDesktop().browse(f.toURI());
                        break; 
                }                 
            } catch (Exception e) {
            }     
            this.setCursor(Cursor.getDefaultCursor());
        }
         break;
          default:
                break;  
    }
        
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            //Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TKd);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbBangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsalMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBangsalMouseClicked

    private void tbBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsalKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBangsalKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
            switch (TabStatus.getSelectedIndex()) {
            case 0:
                tampil();
                break;
            case 1:
                tampil2();
                break;}
//       tampil();
//        if(TabStatus.getSelectedIndex()==0){
//            tampil();
//        }else if(TabStatus.getSelectedIndex()==1){
//            tampil2();
//        }
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            Valid.pindah(evt, TKd, BtnPrint);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        
//        tampil();
                TCari.setText("");
                kdpoli.setText("");
                nmpoli.setText("");
                kdpenjab.setText("");
                nmpenjab.setText("");
                kdpenjab1.setText("");
                nmpenjab1.setText("");
                kdbangsal.setText("");
                nmkamar.setText("");
                Status.setSelectedIndex(0);
                if(TabStatus.getSelectedIndex()==0){
                   tampil();
                }else if(TabStatus.getSelectedIndex()==1){
                   tampil2();
                        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
           
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select poliklinik.nm_poli from poliklinik where poliklinik.kd_poli=?", nmpoli,kdpoli.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Sequel.cariIsi("select poliklinik.nm_poli from poliklinik where poliklinik.kd_poli=?", nmpoli,kdpoli.getText());
            BtnAll.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Sequel.cariIsi("select poliklinik.nm_poli from poliklinik where poliklinik.kd_poli=?", nmpoli,kdpoli.getText());
            Tgl2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeek2ActionPerformed(null);
        }
    }//GEN-LAST:event_kdpoliKeyPressed
   
    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        poli.isCek();
        poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setAlwaysOnTop(false);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek2KeyPressed

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab,kdpenjab.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab,kdpenjab.getText());
            BtnAll.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab,kdpenjab.getText());
            Tgl2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeek3ActionPerformed(null);
        }
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setAlwaysOnTop(false);
        penjab.setVisible(true);
    }//GEN-LAST:event_BtnSeek3ActionPerformed

    private void BtnSeek3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek3KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek3KeyPressed

        
        
    private void kdbangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbangsalKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmkamar,kdbangsal.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmkamar,kdbangsal.getText());
            BtnAll.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Sequel.cariIsi("select bangsal.nm_bangsal from bangsal where bangsal.kd_bangsal=?", nmkamar,kdbangsal.getText());
            Tgl2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeek7ActionPerformed(null);
        }
    }//GEN-LAST:event_kdbangsalKeyPressed

    private void tbBangsal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsal1MouseClicked
        // TODO add your handling code here:
         if(tabMode2.getRowCount()!=0){
            try {
                getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbBangsal1MouseClicked

    private void tbBangsal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsal1KeyPressed
        // TODO add your handling code here:
        if(tabMode2.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbBangsal1KeyPressed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        // TODO add your handling code here:
        isForm2();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void BtnSeek6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek6ActionPerformed
        // TODO add your handling code here:
        penjab1.isCek();
        penjab1.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        penjab1.setLocationRelativeTo(internalFrame1);
        penjab1.setAlwaysOnTop(false);
        penjab1.setVisible(true);
    }//GEN-LAST:event_BtnSeek6ActionPerformed

    private void BtnSeek6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek6KeyPressed

    private void BtnSeek7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek7ActionPerformed
        // TODO add your handling code here:
        bangsal.isCek();
        bangsal.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setAlwaysOnTop(false);
        bangsal.setVisible(true);
    }//GEN-LAST:event_BtnSeek7ActionPerformed

    private void BtnSeek7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek7KeyPressed

    private void TabStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabStatusMouseClicked
    switch (TabStatus.getSelectedIndex()) {
            case 0:
                tampil();
                break;
            case 1:
                tampil2();
                break;}
    }//GEN-LAST:event_TabStatusMouseClicked

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed

    private void kdpenjab1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjab1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab1,kdpenjab1.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab1,kdpenjab1.getText());
            BtnAll.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab1,kdpenjab1.getText());
            Tgl2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeek6ActionPerformed(null);
        }
    }//GEN-LAST:event_kdpenjab1KeyPressed

    private void TKdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKdActionPerformed

    private void TCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCariActionPerformed

    private void nmkamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmkamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmkamarActionPerformed

    private void nmpenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmpenjabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpenjabActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgStatusDataRM dialog = new DlgStatusDataRM(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek2;
    private widget.Button BtnSeek3;
    private widget.Button BtnSeek6;
    private widget.Button BtnSeek7;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.panelisi FormInput;
    private widget.panelisi FormInput1;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ComboBox Status;
    private widget.ComboBox Status1;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private javax.swing.JTabbedPane TabStatus;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdbangsal;
    private widget.TextBox kdpenjab;
    private widget.TextBox kdpenjab1;
    private widget.TextBox kdpoli;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label22;
    private widget.Label label23;
    private widget.TextBox nmkamar;
    private widget.TextBox nmpenjab;
    private widget.TextBox nmpenjab1;
    private widget.TextBox nmpoli;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    private widget.Table tbBangsal1;
    // End of variables declaration//GEN-END:variables

    public void tampil(){        
        try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode);   
            ps=koneksi.prepareStatement(
                "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,poliklinik.nm_poli,reg_periksa.status_lanjut "+
                "from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+     
                "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli inner join penjab on reg_periksa.kd_pj=penjab.kd_pj where  "+
                "concat(reg_periksa.kd_poli,poliklinik.nm_poli) like ? and concat(reg_periksa.kd_pj,penjab.png_jawab) like ? "+
                "and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.status_lanjut like ? "+
                        
                (TCari.getText().equals("")?"":"and (reg_periksa.no_rawat like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or "+
                "pasien.nm_pasien like ? or poliklinik.nm_poli "+
                 "like ? or penjab.png_jawab like ? )order by reg_periksa.tgl_registrasi"));
            try {
                ps.setString(1,"%"+kdpoli.getText()+nmpoli.getText()+"%");
                ps.setString(2,"%"+kdpenjab.getText()+nmpenjab.getText()+"%");
                ps.setString(3,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(4,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(5,"Ralan"+Status.getSelectedItem().toString().replaceAll("Semua","")+"%");
//                ps.setString(6,"%"+kdbangsal.getText()+nmkamar.getText()+"%");
                if(!TCari.getText().trim().equals("")){
                    ps.setString(6,"%"+TCari.getText().trim()+"%");
                    ps.setString(7,"%"+TCari.getText().trim()+"%");
                    ps.setString(8,"%"+TCari.getText().trim()+"%");
                    ps.setString(9,"%"+TCari.getText().trim()+"%");
                    ps.setString(10,"%"+TCari.getText().trim()+"%");
                    ps.setString(11,"%"+TCari.getText().trim()+"%");
//                    ps.setString(13,"%"+TCari.getText().trim()+"%");
                   
                }
                    
                rs=ps.executeQuery();
                adasoapiralan=0;tidakadasoapiralan=0;adasoapiranap=0;tidakadasoapiranap=0;adaresumeralan=0;tidakadaresumeralan=0;adaresumeranap=0;tidakadaresumeranap=0;
                adatriaseigd=0;tidakadatriaseigd=0;adaaskepigd=0;tidakadaaskepigd=0;adaicd10=0;tidakadaicd10=0;adaicd9=0;tidakadaicd9=0;adaLaboratorium=0;tidakadaLaboratorium=0;adaRadiologi=0;tidakadaRadiologi=0;adasuratkematian=0;tidakadasuratkematian=0; 
                while(rs.next()){
                    soapiralan=Sequel.cariIsi("select if(count(pemeriksaan_ralan.no_rawat)>0,'Ada','Tidak Ada') from pemeriksaan_ralan where pemeriksaan_ralan.no_rawat=?",rs.getString("no_rawat"));
                    if(soapiralan.equals("Ada")){
                        adasoapiralan++;
                    }else{
                        tidakadasoapiralan++;
                    }
//                    soapiranap=Sequel.cariIsi("select if(count(pemeriksaan_ranap.no_rawat)>0,'Ada','Tidak Ada') from pemeriksaan_ranap where pemeriksaan_ranap.no_rawat=?",rs.getString("no_rawat"));
//                    if(soapiranap.equals("Ada")){
//                        adasoapiranap++;
//                    }else{
//                        tidakadasoapiranap++;
//                    }
                    resumeralan=Sequel.cariIsi("select if(count(resume_pasien.no_rawat)>0,'Ada','Tidak Ada') from resume_pasien where resume_pasien.no_rawat=?",rs.getString("no_rawat"));
                    if(resumeralan.equals("Ada")){
                        adaresumeralan++;
                    }else{
                        tidakadaresumeralan++;
                    }
//                    resumeranap=Sequel.cariIsi("select if(count(resume_pasien_ranap.no_rawat)>0,'Ada','Tidak Ada') from resume_pasien_ranap where resume_pasien_ranap.no_rawat=?",rs.getString("no_rawat"));
//                    if(resumeranap.equals("Ada")){
//                        adaresumeranap++;
//                    }else{
//                        tidakadaresumeranap++;
//                    }
                    triaseigd=Sequel.cariIsi("select if(count(data_triase_igd.no_rawat)>0,'Ada','Tidak Ada') from data_triase_igd where data_triase_igd.no_rawat=?",rs.getString("no_rawat"));
                    if(triaseigd.equals("Ada")){
                        adatriaseigd++;
                    }else{
                        tidakadatriaseigd++;
                    }
                    askepigd=Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_igd.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_igd where penilaian_awal_keperawatan_igd.no_rawat=?",rs.getString("no_rawat"));
                    if(askepigd.equals("Ada")){
                        adaaskepigd++;
                    }else{
                        tidakadaaskepigd++;
                    }
                    icd10=Sequel.cariIsi("select if(count(diagnosa_pasien.no_rawat)>0,'Ada','Tidak Ada') from diagnosa_pasien where diagnosa_pasien.no_rawat=?",rs.getString("no_rawat"));
                    if(icd10.equals("Ada")){
                        adaicd10++;
                    }else{
                        tidakadaicd10++;
                    }
                    icd9=Sequel.cariIsi("select if(count(prosedur_pasien.no_rawat)>0,'Ada','Tidak Ada') from prosedur_pasien where prosedur_pasien.no_rawat=?",rs.getString("no_rawat"));
                    if(icd9.equals("Ada")){
                        adaicd9++;
                    }else{
                        tidakadaicd9++;
                    }
                    Laboratorium=Sequel.cariIsi("select if(count(periksa_lab.no_rawat)>0,'Ada','Tidak Ada') from periksa_lab where periksa_lab.no_rawat=?",rs.getString("no_rawat"));
                    if(Laboratorium.equals("Ada")){
                        adaLaboratorium++;
                    }else{
                        tidakadaLaboratorium++;
                    }
                    Radiologi=Sequel.cariIsi("select if(count(periksa_radiologi.no_rawat)>0,'Ada','Tidak Ada') from periksa_radiologi where periksa_radiologi.no_rawat=?",rs.getString("no_rawat"));
                    if(Radiologi.equals("Ada")){
                        adaRadiologi++;
                    }else{
                        tidakadaRadiologi++;
                    }
                    suratkematian=Sequel.cariIsi("select if(count(pasien_mati.no_rkm_medis)>0,'Ada','Tidak Ada') from pasien_mati where pasien_mati.no_rkm_medis=?",rs.getString("no_rkm_medis"));
                    if(suratkematian.equals("Ada")){
                        adasuratkematian++;
                    }else{
                        tidakadasuratkematian++;
                    }
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),rs.getString("tgl_registrasi"),rs.getString("nm_dokter"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("nm_poli"),
                       soapiralan,resumeralan,triaseigd,askepigd,icd10,icd9,Laboratorium,Radiologi,suratkematian,order
                    });                    
                }
                if(tabMode.getRowCount()>0){
                    tabMode.addRow(new Object[]{
                        "","","","","Status Data Ada",":",adasoapiralan,adaresumeralan,adatriaseigd,adaaskepigd,adaicd10,adaicd9,adaLaboratorium,adaRadiologi,adasuratkematian,""
                    });
                    tabMode.addRow(new Object[]{
                        "","","","","Status Data Tidak Ada",":",tidakadasoapiralan,tidakadaresumeralan,tidakadatriaseigd,tidakadaaskepigd,tidakadaicd10,tidakadaicd9,tidakadaLaboratorium,tidakadaRadiologi,tidakadasuratkematian,""
                    });
                }   
                
            } 
            catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }       
            this.setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }

    private void getData() {
        int row=tbBangsal.getSelectedRow();
        if(row!= -1){
            TKd.setText(tabMode.getValueAt(row,0).toString());
        }
    }
    private void getData2() {
        int row=tbBangsal1.getSelectedRow();
        if(row!= -1){
            TKd.setText(tabMode2.getValueAt(row,0).toString());
        }
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,65));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    private void isForm2(){
        if(ChkInput1.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH,65));
            FormInput1.setVisible(true);      
            ChkInput1.setVisible(true);
        }else if(ChkInput1.isSelected()==false){           
            ChkInput1.setVisible(false);            
            PanelInput1.setPreferredSize(new Dimension(WIDTH,20));
            FormInput1.setVisible(false);      
            ChkInput1.setVisible(true);
        }
    }
    
    public void tampil2() {
      // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode2);   
            ps=koneksi.prepareStatement(
              "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,poliklinik.nm_poli,reg_periksa.status_lanjut, "+
                "kamar_inap.kd_kamar,bangsal.nm_bangsal "+
                "from reg_periksa inner join "+
                 "kamar_inap on reg_periksa.no_rawat=kamar_inap.no_rawat"+
                 " inner join kamar on kamar_inap.kd_kamar=kamar.kd_kamar inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal"+
                 " inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+ 
                "inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli inner join penjab on reg_periksa.kd_pj=penjab.kd_pj where  "+
                "concat(kamar_inap.kd_kamar,kamar.kd_bangsal,bangsal.nm_bangsal) like ? and concat(reg_periksa.kd_pj,penjab.png_jawab) "+  
                " like ? and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.status_lanjut like ? "+
                
                      
                (TCari.getText().equals("")?"":"and (reg_periksa.no_rawat like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or "+
                "pasien.nm_pasien like ? or bangsal.nm_bangsal "+
                 "like ? or penjab.png_jawab like ? )order by reg_periksa.tgl_registrasi"));
            try {
                ps.setString(1,"%"+kdbangsal.getText()+nmkamar.getText()+"%");
                ps.setString(2,"%"+kdpenjab1.getText()+nmpenjab1.getText()+"%");
                ps.setString(3,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(4,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(5,"Ranap"+Status1.getSelectedItem().toString().replaceAll("Semua","")+"");
//                ps.setString(6,"%"+kdbangsal.getText()+nmkamar.getText()+"%");
                if(!TCari.getText().trim().equals("")){
                    ps.setString(6,"%"+TCari.getText().trim()+"%");
                    ps.setString(7,"%"+TCari.getText().trim()+"%");
                    ps.setString(8,"%"+TCari.getText().trim()+"%");
                    ps.setString(9,"%"+TCari.getText().trim()+"%");
                    ps.setString(10,"%"+TCari.getText().trim()+"%");
                    ps.setString(11,"%"+TCari.getText().trim()+"%");
//                    ps.setString(12,"%"+TCari.getText().trim()+"%");
                   
                }
                    
                rs=ps.executeQuery();
                adasoapiralan=0;tidakadasoapiralan=0;adasoapiranap=0;tidakadasoapiranap=0;adaresumeralan=0;tidakadaresumeralan=0;adaresumeranap=0;tidakadaresumeranap=0;
                adatriaseigd=0;tidakadatriaseigd=0;adaaskepigd=0;tidakadaaskepigd=0;adaicd10=0;tidakadaicd10=0;adaicd9=0;tidakadaicd9=0;adaLaboratorium=0;tidakadaLaboratorium=0;adaRadiologi=0;tidakadaRadiologi=0;adasuratkematian=0;tidakadasuratkematian=0; 
                while(rs.next()){
                    soapiralan=Sequel.cariIsi("select if(count(pemeriksaan_ralan.no_rawat)>0,'Ada','Tidak Ada') from pemeriksaan_ralan where pemeriksaan_ralan.no_rawat=?",rs.getString("no_rawat"));
                    if(soapiralan.equals("Ada")){
                        adasoapiralan++;
                    }else{
                        tidakadasoapiralan++;
                    }
                    soapiranap=Sequel.cariIsi("select if(count(pemeriksaan_ranap.no_rawat)>0,'Ada','Tidak Ada') from pemeriksaan_ranap where pemeriksaan_ranap.no_rawat=?",rs.getString("no_rawat"));
                    if(soapiranap.equals("Ada")){
                        adasoapiranap++;
                    }else{
                        tidakadasoapiranap++;
                    }
                    resumeralan=Sequel.cariIsi("select if(count(resume_pasien.no_rawat)>0,'Ada','Tidak Ada') from resume_pasien where resume_pasien.no_rawat=?",rs.getString("no_rawat"));
                    if(resumeralan.equals("Ada")){
                        adaresumeralan++;
                    }else{
                        tidakadaresumeralan++;
                    }
                    resumeranap=Sequel.cariIsi("select if(count(resume_pasien_ranap.no_rawat)>0,'Ada','Tidak Ada') from resume_pasien_ranap where resume_pasien_ranap.no_rawat=?",rs.getString("no_rawat"));
                    if(resumeranap.equals("Ada")){
                        adaresumeranap++;
                    }else{
                        tidakadaresumeranap++;
                    }
                    triaseigd=Sequel.cariIsi("select if(count(data_triase_igd.no_rawat)>0,'Ada','Tidak Ada') from data_triase_igd where data_triase_igd.no_rawat=?",rs.getString("no_rawat"));
                    if(triaseigd.equals("Ada")){
                        adatriaseigd++;
                    }else{
                        tidakadatriaseigd++;
                    }
                    askepigd=Sequel.cariIsi("select if(count(penilaian_awal_keperawatan_igd.no_rawat)>0,'Ada','Tidak Ada') from penilaian_awal_keperawatan_igd where penilaian_awal_keperawatan_igd.no_rawat=?",rs.getString("no_rawat"));
                    if(askepigd.equals("Ada")){
                        adaaskepigd++;
                    }else{
                        tidakadaaskepigd++;
                    }
                    icd10=Sequel.cariIsi("select if(count(diagnosa_pasien.no_rawat)>0,'Ada','Tidak Ada') from diagnosa_pasien where diagnosa_pasien.no_rawat=?",rs.getString("no_rawat"));
                    if(icd10.equals("Ada")){
                        adaicd10++;
                    }else{
                        tidakadaicd10++;
                    }
                    icd9=Sequel.cariIsi("select if(count(prosedur_pasien.no_rawat)>0,'Ada','Tidak Ada') from prosedur_pasien where prosedur_pasien.no_rawat=?",rs.getString("no_rawat"));
                    if(icd9.equals("Ada")){
                        adaicd9++;
                    }else{
                        tidakadaicd9++;
                    }
                    Laboratorium=Sequel.cariIsi("select if(count(periksa_lab.no_rawat)>0,'Ada','Tidak Ada') from periksa_lab where periksa_lab.no_rawat=?",rs.getString("no_rawat"));
                    if(Laboratorium.equals("Ada")){
                        adaLaboratorium++;
                    }else{
                        tidakadaLaboratorium++;
                    }
                    Radiologi=Sequel.cariIsi("select if(count(periksa_radiologi.no_rawat)>0,'Ada','Tidak Ada') from periksa_radiologi where periksa_radiologi.no_rawat=?",rs.getString("no_rawat"));
                    if(Radiologi.equals("Ada")){
                        adaRadiologi++;
                    }else{
                        tidakadaRadiologi++;
                    }
                    suratkematian=Sequel.cariIsi("select if(count(pasien_mati.no_rkm_medis)>0,'Ada','Tidak Ada') from pasien_mati where pasien_mati.no_rkm_medis=?",rs.getString("no_rkm_medis"));
                    if(suratkematian.equals("Ada")){
                        adasuratkematian++;
                    }else{
                        tidakadasuratkematian++;
                    }
//                    order="Ranap";
//                    kamar=Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar inner join kamar_inap on bangsal.kd_bangsal=kamar.kd_bangsal "+
//                            " and kamar_inap.kd_kamar=kamar.kd_kamar where kamar_inap.no_rawat=? order by kamar_inap.tgl_masuk desc limit 1 ",rs.getString("no_rawat"));  
//
//                    if(kamar.equals("")){
//                        kamar=kamar;
//                        order="Ralan";
//                    }
                    order="Ranap";
                    dokter=Sequel.cariIsi("select nm_dokter from dokter inner join dpjp_ranap inner join reg_periksa inner join kamar_inap on dokter.kd_dokter=dpjp_ranap.kd_dokter "+
                            "and reg_periksa.kd_dokter=dpjp_ranap.kd_dokter where reg_periksa.status_lanjut like 'Ranap%' and kamar_inap.no_rawat like?  order by kamar_inap.tgl_masuk desc limit 1 ",rs.getString("no_rawat"));  

                    if(dokter.equals("")){
                        dokter=dokter;
                        order="Ralan";
                    }
                     tabMode2.addRow(new Object[]{
                        rs.getString("no_rawat"),rs.getString("tgl_registrasi"),dokter,rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("nm_bangsal"),
                       soapiralan,soapiranap,resumeralan,resumeranap,triaseigd,askepigd,icd10,icd9,Laboratorium,Radiologi,suratkematian,order
                    });                      
                }
                if(tabMode2.getRowCount()>0){
                    tabMode2.addRow(new Object[]{
                        "","","","","Status Data Ada",":",adasoapiralan,adasoapiranap,adaresumeralan,adaresumeranap,adatriaseigd,adaaskepigd,adaicd10,adaicd9,adaLaboratorium,adaRadiologi,adasuratkematian,""
                    });
                    tabMode2.addRow(new Object[]{
                        "","","","","Status Data Tidak Ada",":",tidakadasoapiralan,tidakadasoapiranap,tidakadaresumeralan,tidakadaresumeranap,tidakadatriaseigd,tidakadaaskepigd,tidakadaicd10,tidakadaicd9,tidakadaLaboratorium,tidakadaRadiologi,tidakadasuratkematian,""
                    });
                }   
                
            } 
            catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }       
            this.setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
}
