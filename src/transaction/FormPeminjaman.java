/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;
import authentiocation.*;
import buku.*;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import siswa.*;
import utils.Utils;
/**
 *
 * @author Farhan Fadila
 */
public class FormPeminjaman extends javax.swing.JFrame {
    private final SiswaController siswaController = new SiswaController();
    private final AuthenticationController authenticationController = new AuthenticationController();
    private final BukuController bukuController = new BukuController();
    private final PeminjamanController peminjamanController = new PeminjamanController();
    private final Utils utils = new Utils();
    private DefaultTableModel model;
    
    
    private final Map<Integer, Integer> siswaMap = new HashMap<>();
    private final Map<Integer, Integer> bukuMap = new HashMap<>();
    /**
     * Creates new form FormPeminjaman
     */
    public FormPeminjaman() {
        initComponents();
        initColumnTabel();
        initRowTabel();
        initComboBoxBuku();
        initComboBoxSiswa();
        initComboBoxHari();
        txt_id.setEditable(false);
        txt_denda.setEditable(false);
    }
    
     private void initColumnTabel() {
        model = new DefaultTableModel ();
        
        tbl_pinjam.setModel(model);
        model.addColumn("ID");
        model.addColumn("Buku");
        model.addColumn("Siswa");
        model.addColumn("Status");
        model.addColumn("Denda");
        model.addColumn("Max Hari");
        model.addColumn("Tanggal Pinjam");
    }
  
    private void initRowTabel() {
       model.getDataVector().removeAllElements();
       model.fireTableDataChanged();
       try {
            
            List<Peminjaman> data = peminjamanController.data();
            
            for(int i = 0; i < data.size(); i++) {
               Object[] obj = new Object[7];
               Peminjaman item = data.get(i);
               Buku buku = bukuController.detail(item.id_buku);
               Siswa siswa = siswaController.detail(item.id_siswa);
               
               obj[0] = item.id_peminjaman;
               obj[1] = buku.judul;
               obj[2] = siswa.nama_siswa;
               obj[3] = utils.statusToText(item.status);
               obj[4] = !"SP".equals(item.status) ? utils.formatToRupiah(utils.denda(item.tanggal_pinjam, item.max_hari)) : "";
               obj[5] = item.max_hari;
               obj[6] =  utils.dMY(utils.toDate(item.tanggal_pinjam));
               model.addRow(obj); 
            } 
           
       } catch(Exception ex) {
           System.out.println("initRowTabel ERROR" + ex);
       }
    }
    
    void initComboBoxBuku() {
        final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        List<Buku> data = bukuController.data();
        
        for (int i = 0; i < data.size(); i++) {
            Buku item = data.get(i);
            model.addElement(item.judul);
            bukuMap.putIfAbsent(i, item.id);
        }
        
        cmb_buku.setModel(model);
    }
    
    void initComboBoxSiswa() {
        final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        List<Siswa> data = siswaController.data();
        
        for (int i = 0; i < data.size(); i++) {
            Siswa item = data.get(i);
            model.addElement(item.nama_siswa);
            siswaMap.putIfAbsent(i, item.id);
        }
        
        cmb_siswa.setModel(model);
    }
    
    
    void initComboBoxHari() {
        final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
     
        
        for (int i = 1; i < 99; i++) {
           model.addElement(String.valueOf(i));
        }
        
        cmb_hari.setModel(model);
    }
    
    private void resetForm() {
        txt_id.setText("");
        txt_buku.setText("");
        txt_siswa.setText("");
        txt_denda.setText("");
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_buku = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_cari_buku = new javax.swing.JButton();
        txt_siswa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btn_cari_siswa = new javax.swing.JButton();
        cmb_buku = new javax.swing.JComboBox<>();
        cmb_siswa = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_pinjam = new javax.swing.JTable();
        btn_pinjam = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cmb_hari = new javax.swing.JComboBox<>();
        btn_pengembalian = new javax.swing.JButton();
        txt_denda = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btn_reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Form Peminjaman");

        jLabel13.setText("ID");

        txt_buku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bukuActionPerformed(evt);
            }
        });

        jLabel14.setText("Buku");

        btn_cari_buku.setText("Cari");
        btn_cari_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_cari_bukuMousePressed(evt);
            }
        });

        txt_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_siswaActionPerformed(evt);
            }
        });

        jLabel16.setText("Siswa");

        btn_cari_siswa.setText("Cari");
        btn_cari_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_cari_siswaMousePressed(evt);
            }
        });

        cmb_buku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb_siswa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tbl_pinjam.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_pinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_pinjamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_pinjam);

        btn_pinjam.setText("Pinjam");
        btn_pinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_pinjamMousePressed(evt);
            }
        });
        btn_pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pinjamActionPerformed(evt);
            }
        });
        btn_pinjam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_pinjamKeyPressed(evt);
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

        jLabel17.setText("Lama");

        cmb_hari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_pengembalian.setText("Pengembalian");
        btn_pengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_pengembalianMousePressed(evt);
            }
        });
        btn_pengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pengembalianActionPerformed(evt);
            }
        });

        jLabel11.setText("Denda");

        btn_reset.setText("Reset");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_pengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(251, 251, 251))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_id))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_buku, 0, 388, Short.MAX_VALUE)
                                    .addComponent(txt_buku)
                                    .addComponent(txt_siswa)
                                    .addComponent(cmb_siswa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_hari, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_denda))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_cari_buku)
                            .addComponent(btn_cari_siswa)
                            .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel3)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_buku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari_buku))
                .addGap(14, 14, 14)
                .addComponent(cmb_buku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(btn_cari_siswa))
                .addGap(16, 16, 16)
                .addComponent(cmb_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cmb_hari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_denda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bukuActionPerformed

    private void txt_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_siswaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_siswaActionPerformed

    private void btn_pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pinjamActionPerformed

    private void btn_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_laporanActionPerformed

    private void btn_cari_bukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cari_bukuMousePressed
        try {
            String query = txt_buku.getText();
            
            List<Buku> data = bukuController.search(query);
            
            if(data.isEmpty()) {
               utils.errorDialog(this, "Tidak menemukan buku dengan pencarian " + query);
            } else {
               Buku first = data.get(0);

               cmb_buku.setSelectedItem(String.valueOf(bukuMap.containsValue(first.id)));
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btn_cari_bukuMousePressed

    private void btn_cari_siswaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cari_siswaMousePressed
        try {
            String query = txt_siswa.getText();
            
            List<Siswa> data = siswaController.search(query);
            
            if(data.isEmpty()) {
               utils.errorDialog(this, "Tidak menemukan siswa dengan pencarian " + query);
            } else {
               Siswa first = data.get(0);

               cmb_siswa.setSelectedItem(String.valueOf(siswaMap.containsValue(first.id)));
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btn_cari_siswaMousePressed

    private void btn_pinjamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_pinjamKeyPressed
     
    }//GEN-LAST:event_btn_pinjamKeyPressed

    private void btn_pinjamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pinjamMousePressed
       try {
        String id = txt_id.getText();
        
        if(!id.isEmpty()) {
           utils.errorDialog(this, "Silahkan reset form terlebih dahulu");
           return;
        }
         
        int id_buku = bukuMap.get(cmb_buku.getSelectedIndex());
        int id_siswa = siswaMap.get(cmb_siswa.getSelectedIndex());
        int id_admin = authenticationController.currentSession().id;
        int max_hari =  Integer.parseInt(cmb_hari.getSelectedItem().toString());
        
        int yes = utils.errorDialog(this, "Pastikan data sudah benar");
        
        if(yes == JOptionPane.YES_OPTION) {
            boolean berhasil = peminjamanController.pinjam(id_buku, id_siswa, id_admin, utils.epochTimeNow(), max_hari);
        
            if(!berhasil) {
                throw new Exception("Gagal menyimpan data");
            }

            utils.successDialog(this, "Data berhasil disimpan!");

            initRowTabel();
            resetForm();
        }
      } catch(Exception ex) {
           System.out.println(ex);
      }
    }//GEN-LAST:event_btn_pinjamMousePressed

    private void tbl_pinjamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pinjamMousePressed
       try {
            int index =  tbl_pinjam.getSelectedRow();
            TableModel mdl = tbl_pinjam.getModel();
            int id = Integer.parseInt(mdl.getValueAt(index, 0).toString());
            Peminjaman item = peminjamanController.detail(id);
            
            txt_id.setText(String.valueOf(id));
            txt_denda.setText(utils.formatToRupiah(item.denda));
            cmb_hari.setSelectedItem(String.valueOf(item.max_hari));
            cmb_buku.setSelectedIndex(utils.indexOf(bukuMap, item.id_buku));
            cmb_siswa.setSelectedIndex(utils.indexOf(siswaMap, item.id_siswa));
       } catch(Exception ex) {
           System.out.println(ex);
       }
    }//GEN-LAST:event_tbl_pinjamMousePressed

    private void btn_pengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pengembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pengembalianActionPerformed

    private void btn_pengembalianMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengembalianMousePressed
        try {
            String id = txt_id.getText();
        
            if(id.isEmpty()) {
               utils.errorDialog(this, "Silahkan pilih data pinjam terlebih dahulu");
               return;
            }
            
            Peminjaman item = peminjamanController.detail(Integer.parseInt(id));
            
            if("TDK".equals(item.status)) {
               utils.errorDialog(this, "Peminjaman buku telah dikembalikan");
               return;
            }
            
            long denda = utils.denda(item.tanggal_pinjam, item.max_hari);
            int id_admin = authenticationController.currentSession().id;
            
            boolean berhasil = peminjamanController.pengembalian(Integer.parseInt(id), id_admin, utils.epochTimeNow(), denda);
            
            
            if(!berhasil) {
                throw new Exception("Gagal menyimpan data");
            }

            utils.successDialog(this, "Buku berhasil dikembalikan, denda = " + utils.formatToRupiah(denda) );

            initRowTabel();
            resetForm();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btn_pengembalianMousePressed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_resetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMousePressed
        resetForm();
    }//GEN-LAST:event_btn_resetMousePressed

    private void btn_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMousePressed
       utils.bukaLaporan("laporan_peminjaman", peminjamanController.con);
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
            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari_buku;
    private javax.swing.JButton btn_cari_siswa;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_pengembalian;
    private javax.swing.JButton btn_pinjam;
    private javax.swing.JButton btn_reset;
    private javax.swing.JComboBox<String> cmb_buku;
    private javax.swing.JComboBox<String> cmb_hari;
    private javax.swing.JComboBox<String> cmb_siswa;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_pinjam;
    private javax.swing.JTextField txt_buku;
    private javax.swing.JTextField txt_denda;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_siswa;
    // End of variables declaration//GEN-END:variables
}
