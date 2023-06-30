/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peminjaman;

import authentiocation.*; 
import buku.Buku;
import buku.BukuController;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pengembalian.PengembalianController;
import siswa.*;
import utils.*;

/**
 *
 * @author Farhan Fadila
 */
public class FormPinjam extends javax.swing.JFrame {
    private final AuthenticationController authenticationController = new AuthenticationController();
    private final PeminjamanController peminjamanController = new PeminjamanController();
    private final PengembalianController pengembalianController = new PengembalianController();
    private final BukuController bukuController = new BukuController();
    private final SiswaController siswaController = new SiswaController();
    
    private DisableEditTableModel model;
    private final Utils utils = new Utils();
    /**
     * Creates new form FormPinjam
     */
    public FormPinjam() {
        initComponents();
        initColumnTabel();
        initRowTabel();
        listenSearch();
        setExtendedState(JFrame.MAXIMIZED_BOTH);   
    }
    
    private void initColumnTabel() {
        model = new DisableEditTableModel();
        
        tbl_peminjaman.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Peminjaman");
        model.addColumn("Siswa");
        model.addColumn("Buku");
        model.addColumn("Jumlah");
        model.addColumn("Tanggal");
    }
    
    private void initRowTabel() {
        try {
            List<Peminjaman> data = peminjamanController.data();

            fillRow(data);
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
           
            List<Peminjaman> data = peminjamanController.search(query);
            
            fillRow(data);
        } catch(Exception ex) {
            System.out.println("onSearch ex" + ex);
        }
    }
    
    
    private void fillRow(List<Peminjaman> data) {
        try {
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            
            for(int i = 0; i < data.size(); i++) {
               Object[] obj = new Object[6];
               Peminjaman item = data.get(i);
               Siswa siswa = siswaController.detail(item.nisn);
               obj[0] = i + 1;
               obj[1] = item.idPeminjaman;
               obj[2] = siswa.namaSiswa + " " + siswa.kelas; 
               obj[4] = item.jumlah;
               obj[5] = item.tanggalPinjam;
               
               String[] idBuku = item.idBuku.split(",");
               String[] judulBuku = new String[100];
               
               for(int y = 0; y < idBuku.length; y++) {
                   Buku buku = bukuController.detail(idBuku[y]);
                   judulBuku[y] = buku.judul; 
               }
               
               obj[3] = utils.convertArray2String(judulBuku);
               
               
               model.addRow(obj); 
            } 
        } catch(Exception ex) {
            System.out.println("fillRow ex" + ex);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_peminjaman = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        lbl_pinjam = new javax.swing.JLabel();
        lbl_refresh = new javax.swing.JLabel();
        btn_pengembalian = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tbl_peminjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_peminjaman);

        btn_tambah.setBackground(new java.awt.Color(51, 153, 255));
        btn_tambah.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah.setText("Tambah");
        btn_tambah.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_tambah.setBorderPainted(false);
        btn_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambahMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tambahMousePressed(evt);
            }
        });
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 0, 51));
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("Hapus");
        btn_hapus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_hapus.setBorderPainted(false);
        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_hapusMousePressed(evt);
            }
        });
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        jLabel1.setText("Cari");

        lbl_pinjam.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_pinjam.setText("Pinjam");

        lbl_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_refresh.png"))); // NOI18N
        lbl_refresh.setText("perbaharui");
        lbl_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_refreshMousePressed(evt);
            }
        });

        btn_pengembalian.setBackground(new java.awt.Color(102, 204, 0));
        btn_pengembalian.setForeground(new java.awt.Color(255, 255, 255));
        btn_pengembalian.setText("Pengembalian");
        btn_pengembalian.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_pengembalian.setBorderPainted(false);
        btn_pengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pengembalianMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_pengembalianMousePressed(evt);
            }
        });
        btn_pengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pengembalianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_pengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_pinjam)
                                .addGap(555, 555, 555)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txt_search, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 864, Short.MAX_VALUE)
                                .addComponent(lbl_refresh))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(44, 44, 44))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(lbl_refresh)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_pinjam))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
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

    private void btn_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseClicked

    }//GEN-LAST:event_btn_tambahMouseClicked

    private void btn_tambahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMousePressed
        JFrame frame = new FormSearchSiswa();
        
        frame.setVisible(true);
    }//GEN-LAST:event_btn_tambahMousePressed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusMouseClicked

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusMousePressed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void lbl_refreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_refreshMousePressed
        initRowTabel();
    }//GEN-LAST:event_lbl_refreshMousePressed

    private void btn_pengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengembalianMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pengembalianMouseClicked

    private void btn_pengembalianMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengembalianMousePressed
       try {
          
            int selectedRow = tbl_peminjaman.getSelectedRow();
        
            if (selectedRow <= -1) {
                throw new Exception("Silahkan pilih salah satu data di tabel terlebih dahulu");
            }
            
            String idPeminjaman = model.getValueAt(selectedRow, 1).toString();
            Peminjaman peminjaman = peminjamanController.detail(idPeminjaman);
            
            if (peminjaman == null) {
                throw new Exception("Data peminjaman dari id = " + idPeminjaman + " tidak ditemukan" );
            }
            User user = authenticationController.currentSession();
            Date startDate = utils.stringToDate(peminjaman.tanggalPinjam);
            Date endDate = utils.dateTimeNow();
            
   
            
            String nisn = peminjaman.nisn;
            int idAdminPengembalian =  user.id;
            int lamaPinjam = utils.diffrenceInDays(startDate, endDate);
            long denda = utils.calculateFine(lamaPinjam);
            String tanggalPengembalian = utils.dMY(endDate);
            
            String idPengembalian = utils.generateRandomBarcode(10);
            
            while(pengembalianController.isIDExist(idPengembalian)) {
                idPengembalian = utils.generateRandomBarcode(10);
            }
            
             boolean berhasil = false;
            
             berhasil = peminjamanController.ubahStatusToSDK(idPeminjaman);
            
             berhasil = pengembalianController.kembali(idPengembalian, idPeminjaman, nisn, idAdminPengembalian, denda, lamaPinjam, tanggalPengembalian);
            
            if (!berhasil) {
                throw new Exception("Gagal menyimpan data");
            }
            
            initRowTabel();
       } catch (Exception ex) {
           utils.errorDialog(this, ex.getMessage());
       }
    }//GEN-LAST:event_btn_pengembalianMousePressed

    private void btn_pengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pengembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pengembalianActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_pengembalian;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_pinjam;
    private javax.swing.JLabel lbl_refresh;
    private javax.swing.JTable tbl_peminjaman;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
