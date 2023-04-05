/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import authentiocation.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kategori.FormKategori;
import rak.FormRak;
import buku.FormBuku;
import siswa.FormSiswa;
import transaction.FormPeminjaman;

/**
 *
 * @author Farhan Fadila
 */
public class HomePage extends javax.swing.JFrame {
    final AuthenticationController authenticationController = new AuthenticationController();
    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
    }
    
    
    private void navigasi(String menu) {
       JFrame frame;
       
        switch(menu) {
            case "kategori":
                frame = new FormKategori();
                break;
            case "buku":
                frame = new FormBuku();
                break;
            case "siswa":
                frame = new FormSiswa();
                break;
            case "peminjaman":
                frame = new FormPeminjaman();
                break;
            default:
                frame = new FormRak();
        }
        
       frame.setAlwaysOnTop(true);
       frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ic_buku = new javax.swing.JLabel();
        ic_siswa = new javax.swing.JLabel();
        ic_rak = new javax.swing.JLabel();
        ic_kategori = new javax.swing.JLabel();
        ic_pinjam = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Aplikasi Perpustakaan");

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Logout");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        ic_buku.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ic_buku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_buku.png"))); // NOI18N
        ic_buku.setText("Buku");
        ic_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ic_bukuMousePressed(evt);
            }
        });

        ic_siswa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ic_siswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_siswa.png"))); // NOI18N
        ic_siswa.setText("Siswa");
        ic_siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ic_siswaMousePressed(evt);
            }
        });

        ic_rak.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ic_rak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_rak.png"))); // NOI18N
        ic_rak.setText("Rak");
        ic_rak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ic_rakMousePressed(evt);
            }
        });

        ic_kategori.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ic_kategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_kategori.png"))); // NOI18N
        ic_kategori.setText("Kategori");
        ic_kategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ic_kategoriMousePressed(evt);
            }
        });

        ic_pinjam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ic_pinjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ic_pinjam.png"))); // NOI18N
        ic_pinjam.setText("Pinjam");
        ic_pinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ic_pinjamMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ic_buku)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(ic_rak)))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ic_pinjam)
                                        .addGap(56, 56, 56)
                                        .addComponent(ic_siswa))
                                    .addComponent(ic_kategori)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(163, 163, 163)))
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ic_buku)
                    .addComponent(ic_pinjam)
                    .addComponent(ic_siswa))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ic_kategori)
                    .addComponent(ic_rak))
                .addContainerGap(176, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        final boolean logout = authenticationController.logout();

        if(logout) {
            JFrame login = new FormLogin();
            dispose();
            login.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Gagal Logout");
        }

    }//GEN-LAST:event_jButton1MousePressed

    private void ic_bukuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_bukuMousePressed
       navigasi("buku");
    }//GEN-LAST:event_ic_bukuMousePressed

    private void ic_siswaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_siswaMousePressed
       navigasi("siswa");
    }//GEN-LAST:event_ic_siswaMousePressed

    private void ic_rakMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_rakMousePressed
        navigasi("rak");
    }//GEN-LAST:event_ic_rakMousePressed

    private void ic_kategoriMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_kategoriMousePressed
        navigasi("kategori");
    }//GEN-LAST:event_ic_kategoriMousePressed

    private void ic_pinjamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ic_pinjamMousePressed
       navigasi("peminjaman");
    }//GEN-LAST:event_ic_pinjamMousePressed

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ic_buku;
    private javax.swing.JLabel ic_kategori;
    private javax.swing.JLabel ic_pinjam;
    private javax.swing.JLabel ic_rak;
    private javax.swing.JLabel ic_siswa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
