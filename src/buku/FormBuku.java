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
        cmb_kategori = new javax.swing.JComboBox<>();
        cmb_rak = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_judul = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_pengarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_penerbit = new javax.swing.JTextField();
        cmb_tahun = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_halaman = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        date_masuk = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_keterangan = new javax.swing.JTextArea();
        btn_hapus = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_buku = new javax.swing.JTable();
        btn_simpan = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_isbn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmb_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb_rak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Kategori");

        jLabel2.setText("Rak");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Form Buku");

        jLabel4.setText("Judul");

        jLabel5.setText("Pengarang");

        jLabel6.setText("Penerbit");

        cmb_tahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Tahun");

        jLabel8.setText("Halaman");

        jLabel9.setText("Jumlah");

        jLabel10.setText("Tanggal Masuk");

        date_masuk.setDateFormatString("dd-MM-yyyy");

        jLabel11.setText("Katerangan");

        txt_keterangan.setColumns(20);
        txt_keterangan.setRows(5);
        jScrollPane1.setViewportView(txt_keterangan);

        btn_hapus.setText("Hapus");
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

        btn_laporan.setText("Laporan");
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
        tbl_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bukuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_buku);

        btn_simpan.setText("Simpan");
        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_simpanMousePressed(evt);
            }
        });

        jLabel13.setText("ID");

        jLabel12.setText("ISBN");

        jLabel14.setText("Cari Buku");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_pengarang)
                                                .addComponent(txt_penerbit)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(cmb_tahun, 0, 178, Short.MAX_VALUE)
                                                        .addComponent(cmb_kategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txt_halaman))
                                                    .addGap(50, 50, 50)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel9)
                                                        .addComponent(jLabel10))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGap(18, 18, 18)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(cmb_rak, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(date_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addComponent(txt_id)
                                                .addComponent(txt_judul, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txt_isbn)))))
                                .addGap(0, 11, Short.MAX_VALUE)))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_judul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel6)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_pengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txt_penerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txt_isbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_rak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel10))
                    .addComponent(date_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
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

    private void btn_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMousePressed
        utils.bukaLaporan("laporan_buku", bukuController.con);
    }//GEN-LAST:event_btn_laporanMousePressed

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
