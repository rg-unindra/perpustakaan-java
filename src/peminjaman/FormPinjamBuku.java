/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peminjaman;

import authentiocation.*;
import buku.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import siswa.*;
import utils.Utils;

/**
 *
 * @author Farhan Fadila
 */
public class FormPinjamBuku extends javax.swing.JFrame {
    public String nisn;
    
    private final PeminjamanController peminjamanController = new PeminjamanController();
    private final BukuController bukuController = new BukuController();
    private final SiswaController siswaController = new SiswaController();
    private final AuthenticationController authenticationController = new AuthenticationController();
    private final Utils utils = new Utils();
    
    private final List<CheckoutItem> checkoutItems = new ArrayList<>();
    private DefaultTableModel model;
    private DefaultTableModel cariBukuModel;
    
    javax.swing.JFrame frame = this;
    
   
    /**
     * Creates new form FormPinjamBuku
     */
    public FormPinjamBuku(
          String nisn
    ) {
        initComponents();
        this.nisn = nisn;
        txt_search.requestFocus();
        getDetailSiswa();
        initColumnTabel();
        initRowTabelCariBuku();
        listenUserEditTable();
        listenSearch();
    }
    
    
    
    private void updateItem(String itemId) {
      try {
          
        if(!checkoutItems.isEmpty()) {
            for(int i = 0; i < checkoutItems.size(); i++) {
            CheckoutItem item = checkoutItems.get(i);
            if(item.buku.idBuku.equals(itemId)) {
               CheckoutItem updatedItem = item;
               int stok = bukuController.remainingStock(updatedItem.buku.idBuku);
                int editedStok = item.count + 1;

               if(editedStok > stok ) {
                   utils.errorDialog(frame, "Jumlah stok tidak mencukupi");
                   return;
               }
               updatedItem.setCount(item.count + 1);
               checkoutItems.remove(item);
               checkoutItems.add(updatedItem);
               initRowTabel();
               return;
            }
           }
        }
       

        if(!bukuController.isBookExist(itemId)) {
            utils.errorDialog(this, "Buku dengan id " + itemId + " tidak ditemukan");
            return;
        }

        Buku buku = bukuController.detail(itemId.trim());

        checkoutItems.add(new CheckoutItem(buku, 1));
        initRowTabel();
      } catch(Exception ex) {
      }
    }
    
     private void initColumnTabel() {
        model = new DefaultTableModel ();
        cariBukuModel = new DefaultTableModel ();
        
        tbl_buku.setModel(model);
        model.addColumn("No");
        model.addColumn("ID Buku");
        model.addColumn("Judul");
        model.addColumn("Jumlah");
        
        tbl_cari_buku.setModel(cariBukuModel);
        cariBukuModel.addColumn("No");
        cariBukuModel.addColumn("ID Buku");
        cariBukuModel.addColumn("Judul");
    }
     
    private void initRowTabel() {
       try {
           if(checkoutItems.isEmpty()) return;
           
           model.getDataVector().removeAllElements();
           model.fireTableDataChanged(); 
           
            for(int i = 0; i < checkoutItems.size(); i++) {
                   Object[] obj = new Object[4];
                   CheckoutItem item = checkoutItems.get(i);
                   obj[0] = i + 1;
                   obj[1] = item.buku.idBuku;
                   obj[2] = item.buku.judul;
                   obj[3] = item.count;
                   model.addRow(obj); 
           }
       } catch(Exception ex) {
           System.out.println(checkoutItems.size());
       }
    }
    
    private void initRowTabelCariBuku() {
         try {
           
           cariBukuModel.getDataVector().removeAllElements();
           cariBukuModel.fireTableDataChanged(); 
           
           List<Buku> data = bukuController.data();
           
            for(int i = 0; i < data.size(); i++) {
                   Buku item = data.get(i);
                   Object[] obj = new Object[3];
                   obj[0] = i + 1;
                   obj[1] = item.idBuku;
                   obj[2] = item.judul;
                   cariBukuModel.addRow(obj); 
           }
       } catch(Exception ex) {
           System.out.println(checkoutItems.size());
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
            
            cariBukuModel.getDataVector().removeAllElements();
            cariBukuModel.fireTableDataChanged();
            
           
            List<Buku> data = bukuController.search(query);
            
            for(int i = 0; i < data.size(); i++) {
               Object[] obj = new Object[3];
               Buku item = data.get(i);
               obj[0] = i + 1;
               obj[1] = item.idBuku;
               obj[2] = item.judul;
               cariBukuModel.addRow(obj); 
            } 
        } catch(Exception ex) {
            System.out.println("onSearch ex" + ex);
        }
    }
    
    private void listenUserEditTable() {
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (e.getType() == TableModelEvent.UPDATE && column != TableModelEvent.ALL_COLUMNS) {
                    Object data = model.getValueAt(row, column);
                    System.out.println("User edited row: " + row + ", column: " + column + ", new value: " + data);
                    
                    if(column == 3) {
                        if(!utils.isNumber(data.toString())) {
                            utils.errorDialog(frame, nisn);
                            
                        } else {
                            if(row <= checkoutItems.size() - 1) {
                                 CheckoutItem updatedItem = checkoutItems.get(row);
                                 int stok = updatedItem.buku.jumlah;
                                 int editedStok = Integer.parseInt(data.toString());
                                 
                                if(editedStok > stok ) {
                                    utils.errorDialog(frame, "Jumlah stok tidak mencukupi");
                                    return;
                                }
                               updatedItem.setCount(Integer.parseInt(data.toString()));
                               checkoutItems.remove(row);
                               checkoutItems.add(updatedItem);
                            }
                        }
                        
                    }
                }
            }
        });
    }
    
    
    private void getDetailSiswa() {
        Siswa siswa = siswaController.detail(nisn);
        
        lbl_description.setText("daftar buku yang akan dipinjam oleh " + siswa.namaSiswa);
    }
    
    
    private void save() {
        try {
            if(checkoutItems.isEmpty()) {
                txt_search.setFocusable(true);
                throw new Exception("Buku masih kosong, silahkan pilih beberapa buku terlebih dahulu");
            }
            
            User user = authenticationController.currentSession();
            int idAdminPeminjaman =  user.id;
            
            String[] idBuku = new String[100];
            String[] jumlah = new String[100];
            
            for(int i = 0; i < checkoutItems.size(); i++) {
                CheckoutItem item = checkoutItems.get(i);
                idBuku[i] = item.buku.idBuku;
                jumlah[i] = String.valueOf(item.count);
            }
            
            String idPeminjaman = utils.generateRandomBarcode(10);
            
            while(peminjamanController.isIDExist(idPeminjaman)) {
                idPeminjaman = utils.generateRandomBarcode(10);
            }
            
            
            boolean berhasil = peminjamanController.pinjam(
                    idPeminjaman, 
                    utils.convertArray2String(idBuku), 
                    utils.convertArray2String(jumlah), 
                    nisn, 
                    idAdminPeminjaman, 
                    utils.dMY(new Date())
            );
            
            if(!berhasil) {
                throw new Exception("Gagal menyimpan data");
            }
            
             for(int i = 0; i < checkoutItems.size(); i++) {
                CheckoutItem item = checkoutItems.get(i);
                updateStockBuku(item.buku.idBuku, item.buku.jumlah - item.count, 0);
            }
            
            dispose();
            
        } catch(Exception ex) {
            System.out.println(ex);
            utils.errorDialog(this, ex.getMessage());
        }
    
    }
    
    private void updateStockBuku(String idBuku, int stock, int n) {
        try {
           if(n > 10) {
               utils.errorDialog(this, "Gagal mengupdate jumlah buku");
               return;
           }
           bukuController.editStok(idBuku, stock);
        } catch(Exception ex) {
            updateStockBuku(idBuku, stock, n++);
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
        lbl_description = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_buku = new javax.swing.JTable();
        btn_simpan = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        btn_clear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_cari_buku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_description.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_description.setText("daftar buku yang akan dipinjam oleh {{nama}}");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Daftar Buku");

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
        tbl_buku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbl_bukuFocusLost(evt);
            }
        });
        tbl_buku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_bukuKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_buku);

        btn_simpan.setBackground(new java.awt.Color(51, 153, 255));
        btn_simpan.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("Selesai");
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

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });

        btn_clear.setBackground(new java.awt.Color(255, 204, 0));
        btn_clear.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear.setText("Unselect");
        btn_clear.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_clear.setBorderPainted(false);
        btn_clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_clearMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_clearMousePressed(evt);
            }
        });
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        tbl_cari_buku.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_cari_buku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbl_cari_bukuFocusLost(evt);
            }
        });
        tbl_cari_buku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cari_bukuMouseClicked(evt);
            }
        });
        tbl_cari_buku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_cari_bukuKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_cari_buku);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(lbl_description))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_description)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
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

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusMouseClicked

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        int selectedRow = tbl_buku.getSelectedRow();
        
        if (selectedRow <= -1) {
            return;
        }
        
        checkoutItems.remove(selectedRow);
        initRowTabel();
    }//GEN-LAST:event_btn_hapusMousePressed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpanMouseClicked

    private void btn_simpanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMousePressed
       save();
    }//GEN-LAST:event_btn_simpanMousePressed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        String barcode = txt_search.getText();
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            updateItem(barcode.trim());
            txt_search.setText("");
        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void tbl_bukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_bukuKeyPressed
       System.out.println("Here tbl_bukuKeyPressed");
    }//GEN-LAST:event_tbl_bukuKeyPressed

    private void btn_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clearMouseClicked

    private void btn_clearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clearMousePressed
         tbl_buku.clearSelection();
    }//GEN-LAST:event_btn_clearMousePressed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clearActionPerformed

    private void tbl_bukuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbl_bukuFocusLost
  
    }//GEN-LAST:event_tbl_bukuFocusLost

    private void tbl_cari_bukuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbl_cari_bukuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_cari_bukuFocusLost

    private void tbl_cari_bukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_cari_bukuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_cari_bukuKeyPressed

    private void tbl_cari_bukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cari_bukuMouseClicked
       try {
           int index =  tbl_cari_buku.getSelectedRow();
           TableModel mdl = tbl_cari_buku.getModel();
           String idBuku = mdl.getValueAt(index, 1).toString();
           Buku item =  bukuController.detail(idBuku);
           
           updateItem(item.idBuku);
       } catch(Exception ex) {
       
       }
    }//GEN-LAST:event_tbl_cari_bukuMouseClicked

   
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_description;
    private javax.swing.JTable tbl_buku;
    private javax.swing.JTable tbl_cari_buku;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
