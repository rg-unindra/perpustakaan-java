/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buku;

import java.util.Calendar;
import kategori.*;
import rak.*;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import utils.Utils;

/**
 *
 * @author Farhan Fadila
 */
public class FormBuku extends javax.swing.JFrame {
    private final BukuController bukuController = new BukuController();
    private final KategoriController kategoriController = new KategoriController();
    private final RakController rakController = new RakController();
    private final Utils utils = new Utils();
    private DefaultTableModel model;
    
    private final Map<Integer, Integer> rakMap = new HashMap<>();
    private final Map<Integer, Integer> kategoriMap = new HashMap<>();
    
    /**
     * Creates new form FormBuku
     */
    public FormBuku() {
        initComponents();
        initColumnTabel();
        initRowTabel();
        initComboBoxKategori();
        initComboBoxRak();
        initComboBoxTahun();
        listenSearch();
        txt_id.setEditable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);   
    }
    
     
     private void initColumnTabel() {
        model = new DefaultTableModel ();
        
        tbl_buku.setModel(model);
        model.addColumn("ID");
        model.addColumn("Judul");
        model.addColumn("ISBN");
        model.addColumn("Rak");
    }
        
    private void initRowTabel() {
       model.getDataVector().removeAllElements();
       model.fireTableDataChanged();
       try {
            List<Buku> data = bukuController.data();
            
            
            for(int i = 0; i < data.size(); i++) {
               Object[] obj = new Object[4];
               Buku item = data.get(i);
               Rak rak = rakController.detail(item.id_rak);
               obj[0] = item.id;
               obj[1] = item.judul;
               obj[2] = item.isbn;
               obj[3] = rak.nama;
               model.addRow(obj); 
            } 
           
       } catch(Exception ex) {
           System.out.println("initRowTabel ERROR" + ex);
       }
    }
    
    
     private void listenSearch() {
        txt_search.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
               onSearch();
            }
            public void removeUpdate(DocumentEvent e) {
                onSearch();
            }
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    
    private void onSearch() {
        try {
            String query = txt_search.getText();
            
            if(query.isEmpty()) {
                initRowTabel();
                return;
            }
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            
           
            List<Buku> data = bukuController.search(query);
            
            for(int i = 0; i < data.size(); i++) {
               Object[] obj = new Object[4];
               Buku item = data.get(i);
               Rak rak = rakController.detail(item.id_rak);
               obj[0] = item.id;
               obj[1] = item.judul;
               obj[2] = item.isbn;
               obj[3] = rak.nama;
               model.addRow(obj); 
            } 
        } catch(Exception ex) {
            System.out.println("onSearch ex" + ex);
        }
    }
    
    void initComboBoxKategori() {
        final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        List<Kategori> kategori = kategoriController.data();
        
        for (int i = 0; i < kategori.size(); i++) {
            Kategori item = kategori.get(i);
            model.addElement(item.nama);
            kategoriMap.putIfAbsent(i, item.id);
        }
        
        cmb_kategori.setModel(model);
    }
    
    void initComboBoxRak() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        List<Rak> rak = rakController.data();
        
        for (int i = 0; i < rak.size(); i++) {
            Rak item = rak.get(i);
            model.addElement(item.nama);
            rakMap.putIfAbsent(i, item.id);
        }
        cmb_rak.setModel(model);
    }
    
    
     void initComboBoxTahun() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        
        int[] years = new int[currentYear - 1949];
        
        for (int i = currentYear, j = 0; i >= 1950; i--, j++) {
            years[j] = i;
        }
        
        for (int i = 0; i < years.length; i++) {
            model.addElement(Integer.toString(years[i]));
        }
        cmb_tahun.setModel(model);
    }
     
     
    private void resetForm() {
        txt_id.setText("");
        txt_judul.setText("");
        txt_isbn.setText("");
        txt_penerbit.setText("");
        txt_pengarang.setText("");
        txt_keterangan.setText("");
        txt_jumlah.setText("");
        txt_halaman.setText("");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        btn_hapus = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        cmb_tahun = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_halaman = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        date_masuk = new com.toedter.calendar.JDateChooser();
        cmb_kategori = new javax.swing.JComboBox<>();
        cmb_rak = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_keterangan = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        txt_isbn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_penerbit = new javax.swing.JTextField();
        txt_pengarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_judul = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_buku = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 768));

        btn_hapus.setBackground(new java.awt.Color(255, 0, 51));
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_hapus.setBorderPainted(false);
        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_hapusMousePressed(evt);
            }
        });
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_laporan.setBackground(new java.awt.Color(204, 204, 0));
        btn_laporan.setForeground(new java.awt.Color(255, 255, 255));
        btn_laporan.setText("Laporan");
        btn_laporan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_laporan.setBorderPainted(false);
        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_laporanMousePressed(evt);
            }
        });
        btn_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laporanActionPerformed(evt);
            }
        });

        cmb_tahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Tahun");

        jLabel8.setText("Halaman");

        jLabel9.setText("Jumlah");

        jLabel10.setText("Tanggal Masuk");

        date_masuk.setDateFormatString("dd-MM-yyyy");

        cmb_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_kategori.setBorder(null);

        cmb_rak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Kategori");

        jLabel2.setText("Rak");

        txt_keterangan.setColumns(20);
        txt_keterangan.setRows(5);
        jScrollPane1.setViewportView(txt_keterangan);

        jLabel11.setText("Katerangan");

        jLabel12.setText("ISBN");

        jLabel6.setLabelFor(txt_penerbit);
        jLabel6.setText("Penerbit");

        jLabel5.setText("Pengarang");

        jLabel4.setText("Judul");

        jLabel13.setText("ID");

        tbl_buku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_buku.setGridColor(new java.awt.Color(0, 0, 0));
        tbl_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bukuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_buku);

        jLabel14.setText("Cari Buku");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Form Buku");

        btn_simpan.setBackground(new java.awt.Color(51, 153, 255));
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("Simpan");
        btn_simpan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_simpan.setBorderPainted(false);
        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_simpanMousePressed(evt);
            }
        });
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel12))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_penerbit, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_pengarang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_judul, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_id, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_isbn, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(33, 33, 33)
                                        .addComponent(txt_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(34, 34, 34)
                                        .addComponent(cmb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmb_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(25, 25, 25)
                                        .addComponent(date_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel2))
                                        .addGap(63, 63, 63)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmb_rak, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_jumlah)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(69, 69, 69)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel3)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_judul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_pengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_penerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel12)
                            .addComponent(txt_isbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cmb_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmb_rak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(date_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        try {
            int id = Integer.parseInt(txt_id.getText());

            Buku item = bukuController.detail(id);

            int dialogResult = utils.errorDialog(this, "Kamu yakin ingin menghapus data " + item.judul + "?");

            if(dialogResult == JOptionPane.YES_OPTION) {
                bukuController.hapus(id);
                resetForm();
                initRowTabel();
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btn_hapusMousePressed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_laporanActionPerformed

    private void tbl_bukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bukuMouseClicked
        try {
            int index =  tbl_buku.getSelectedRow();
            TableModel mdl = tbl_buku.getModel();
            int id = Integer.parseInt(mdl.getValueAt(index, 0).toString());
            Buku item =  bukuController.detail(id);
            

            txt_id.setText(String.valueOf(id));
            txt_judul.setText(item.judul);
            txt_pengarang.setText(item.pengarang);
            txt_penerbit.setText(item.penerbit);
            txt_isbn.setText(item.isbn);
            txt_keterangan.setText(item.keterangan);
            txt_halaman.setText(String.valueOf(item.halaman));
            txt_jumlah.setText(String.valueOf(item.jumlah));
            cmb_tahun.setSelectedItem(String.valueOf(item.tahun));
            cmb_rak.setSelectedItem(String.valueOf(rakMap.containsValue(item.id_rak)));
            cmb_kategori.setSelectedItem(String.valueOf(kategoriMap.containsValue(item.id_kategori)));
            date_masuk.setDate(utils.toDate(item.tanggal_masuk));
        } catch(Exception ex) {}
    }//GEN-LAST:event_tbl_bukuMouseClicked

    private void btn_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMousePressed
        utils.bukaLaporan("laporan_buku", bukuController.con);
    }//GEN-LAST:event_btn_laporanMousePressed

    private void btn_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseClicked

    }//GEN-LAST:event_btn_simpanMouseClicked

    private void btn_simpanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMousePressed
       try {
            String idString = txt_id.getText();
            boolean isCreate = idString.isEmpty();

            String judul = txt_judul.getText();
            String pengarang = txt_pengarang.getText();
            String penerbit = txt_penerbit.getText();
            String isbn = txt_isbn.getText();
            String keterangan = txt_keterangan.getText();
            String halaman = txt_halaman.getText();
            String jumlah = txt_jumlah.getText();
            Date date = date_masuk.getDate();
            int tahun = Integer.parseInt(cmb_tahun.getSelectedItem().toString());
            int idRak = rakMap.get(cmb_rak.getSelectedIndex());
            int idKategori = kategoriMap.get(cmb_kategori.getSelectedIndex());
            
         
            
            if(judul.isEmpty()) {
                utils.errorDialog(this, "Nama harus di isi");
                return;
            }
            if(pengarang.isEmpty()) {
                utils.errorDialog(this, "Pengarang harus di isi");
                return;
            }
            if(penerbit.isEmpty()) {
                utils.errorDialog(this, "Penerbit harus di isi");
                return;
            }
            if(isbn.isEmpty()) {
                utils.errorDialog(this, "ISBN harus di isi");
                return;
            }
            if(keterangan.isEmpty()) {
                utils.errorDialog(this, "Keterangan harus di isi");
                return;
            }
            if(halaman.isEmpty()) {
                utils.errorDialog(this, "Halaman harus di isi");
                return;
            }
            if(jumlah.isEmpty()) {
                utils.errorDialog(this, "Jumlah harus di isi");
                return;
            }

            boolean berhasil = false;

            if(isCreate) {
                berhasil = bukuController.tambah(idKategori, idRak, judul, keterangan, isbn, penerbit, pengarang, tahun, Integer.parseInt(halaman), Integer.parseInt(jumlah), utils.toEpoch(date));
            } else {
                berhasil = bukuController.edit(Integer.parseInt(idString),  idKategori, idRak, judul, keterangan, isbn, penerbit, pengarang, tahun, Integer.parseInt(halaman), Integer.parseInt(jumlah), utils.toEpoch(date));
            }

            if(!berhasil) {
                throw new Exception("Gagal menyimpan data");
            }

            utils.successDialog(this, "Data berhasil disimpan!");

            initRowTabel();
            resetForm();
        } catch(Exception ex) {
            utils.errorDialog(this, "Maaf gagal menyimpan data, error: " + ex);
        }
    }//GEN-LAST:event_btn_simpanMousePressed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) { 
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cmb_kategori;
    private javax.swing.JComboBox<String> cmb_rak;
    private javax.swing.JComboBox<String> cmb_tahun;
    private com.toedter.calendar.JDateChooser date_masuk;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_buku;
    private javax.swing.JTextField txt_halaman;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_isbn;
    private javax.swing.JTextField txt_judul;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextArea txt_keterangan;
    private javax.swing.JTextField txt_penerbit;
    private javax.swing.JTextField txt_pengarang;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
