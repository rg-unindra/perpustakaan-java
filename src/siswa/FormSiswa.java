/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siswa;


import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import peminjaman.PeminjamanController;
import pengembalian.PengembalianController;
import utils.Utils;

/**
 *
 * @author Farhan Fadila
 */
public class FormSiswa extends javax.swing.JFrame {
   
    private final SiswaController siswaController = new SiswaController();
    private final PeminjamanController peminjamanController = new PeminjamanController();
    private final PengembalianController pengembalianController = new PengembalianController();
    private final Utils utils = new Utils();
    private DefaultTableModel model;
     
    /**
     * Creates new form FormKategori
     */
    public FormSiswa() {
        initComponents();
        initColumnTabel();
        initRowTabel();
        initComboBoxKelas();
        listenSearch();
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }
    
     private void initColumnTabel() {
        model = new DefaultTableModel ();
        
        tbl_siswa.setModel(model);
        model.addColumn("NO");
        model.addColumn("NISN");
        model.addColumn("Nama");
        model.addColumn("Kelas");
        model.addColumn("Telepon");
        model.addColumn("Alamat");
    }
        
    private void initRowTabel() {
       model.getDataVector().removeAllElements();
       model.fireTableDataChanged();
       try {
            List<Siswa> data = siswaController.data();
            
            
            for(int i = 0; i < data.size(); i++) {
               Object[] obj = new Object[6];
               Siswa item = data.get(i);
               obj[0] = i + 1;
               obj[1] = item.nisn;
               obj[2] = item.namaSiswa;
               obj[3] = item.kelas;
               obj[4] = item.telepon;
               obj[5] = item.alamat;
               model.addRow(obj); 
            } 
           
       } catch(Exception ex) {
           System.out.println("initRowTabel ERROR" + ex);
       }
    }
    
    
    void initComboBoxKelas() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        String[] kelas = {"X", "XI", "XII"};
        String[] prodi = {"RPL", "TKJ"};
        
       
     
        for(String i : kelas) {
            for(String y : prodi) {
                model.addElement((i + " " + y));
            }
        }
        
        cmb_kelas.setModel(model);
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
            
            List<Siswa> data = siswaController.search(query);
            
            for(int i = 0; i < data.size(); i++) {
               Object[] obj = new Object[4];
               Siswa item = data.get(i);
               obj[0] = i + 1;
               obj[1] = item.nisn;
               obj[2] = item.namaSiswa;
               obj[3] = item.kelas;
               obj[3] = item.telepon;
               
               model.addRow(obj); 
            } 
        } catch(Exception ex) {
            System.out.println("onSearch ex" + ex);
        }
    }
    
    private void resetForm() {
        txt_nisn.setEditable(true);
        txt_nisn.setText("");
        txt_nama.setText("");
        txt_alamat.setText("");
        txt_telepon.setText("");
        cmb_kelas.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txt_telepon = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmb_kelas = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_siswa = new javax.swing.JTable();
        txt_nama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nisn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btn_laporan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_simpan.setBackground(new java.awt.Color(51, 153, 255));
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("Simpan");
        btn_simpan.setBorder(null);
        btn_simpan.setBorderPainted(false);
        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_simpanMousePressed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 0, 51));
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.setBorder(null);
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

        btn_reset.setBackground(new java.awt.Color(102, 204, 255));
        btn_reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset.setText("Reset");
        btn_reset.setBorder(null);
        btn_reset.setBorderPainted(false);
        btn_reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_resetMousePressed(evt);
            }
        });
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane1.setViewportView(txt_alamat);

        jLabel9.setText("Alamat");

        jLabel7.setText("Telepon");

        cmb_kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Kelas");

        tbl_siswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_siswaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_siswa);

        jLabel6.setText("Nama Siswa");

        jLabel5.setText("NISN");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Form Siswa");

        jLabel10.setText("Cari");

        btn_laporan.setBackground(new java.awt.Color(204, 204, 0));
        btn_laporan.setForeground(new java.awt.Color(255, 255, 255));
        btn_laporan.setText("Laporan");
        btn_laporan.setBorder(null);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel6)))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txt_telepon)
                                .addComponent(cmb_kelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_nama)
                                .addComponent(txt_nisn)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(28, 28, 28)
                                .addComponent(txt_search))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 780, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txt_telepon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMousePressed
        try {
            String nisn = txt_nisn.getText().trim();
            String nama = txt_nama.getText();
            String telepon = txt_telepon.getText();
            String alamat = txt_alamat.getText();
            String kelas = cmb_kelas.getSelectedItem().toString();
            
            
            boolean isEdit = siswaController.isNISNExist(nisn);
            boolean berhasil = false;
            
            if(isEdit) {
                 berhasil = siswaController.edit(nisn, nama, kelas, alamat, telepon);
            } else {
                 berhasil = siswaController.tambah(nisn, nama, kelas, alamat, telepon);
            }

            
            
            if(!berhasil) {
               throw new Exception("Gagal menyimpan data");
            }
            
            utils.successDialog(this, "Data berhasil disimpan!");
            initRowTabel();
            resetForm();
        } catch(Exception ex) {
            utils.errorDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btn_simpanMousePressed

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        try {
            String nisn = txt_nisn.getText();

            Siswa item = siswaController.detail(nisn);

            int dialogResult = utils.errorDialog(this, 
                    "Kamu yakin ingin menghapus data " + item.namaSiswa + "? menghapus data siswa juga akan menghapus "
                    + "data peminjaman, pengembalian dari siswa yang dihapus");

            if(dialogResult == JOptionPane.YES_OPTION) {
                siswaController.hapus(nisn);
                peminjamanController.hapusByNISN(nisn);
                pengembalianController.hapusByNISN(nisn);
                
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

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetActionPerformed

    private void tbl_siswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_siswaMouseClicked
       try {
            int index =  tbl_siswa.getSelectedRow();
            TableModel mdl = tbl_siswa.getModel();
            String nisn = mdl.getValueAt(index, 1).toString();
            Siswa item = siswaController.detail(nisn);
            
            
            txt_nisn.setEditable(false);
            txt_nisn.setText(item.nisn);
            txt_nama.setText(item.namaSiswa);
            txt_telepon.setText(item.telepon);
            txt_alamat.setText(item.alamat);
            cmb_kelas.setSelectedItem(item.kelas);
        } catch(Exception ex) {
            
        }
    }//GEN-LAST:event_tbl_siswaMouseClicked

    private void btn_resetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMousePressed
        resetForm();
    }//GEN-LAST:event_btn_resetMousePressed

    private void btn_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMousePressed
         utils.bukaLaporan("laporan_siswa", siswaController.con);
    }//GEN-LAST:event_btn_laporanMousePressed

    private void btn_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_laporanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cmb_kelas;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_siswa;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nisn;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_telepon;
    // End of variables declaration//GEN-END:variables
}
