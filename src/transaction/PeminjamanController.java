/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;
import Database.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Farhan Fadila
 */
public class PeminjamanController extends Koneksi {
   public PeminjamanController() {
        if (con == null) {
            start();
        }
    }
   
   
   public List<Peminjaman> data() {
        List<Peminjaman> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `peminjaman` ORDER BY `tanggal_pinjam` ASC");

        try {
            while (result.next()) {
                temp.add(new Peminjaman(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4),
                        result.getInt(5), result.getString(6), result.getLong(7), result.getLong(8),
                        result.getLong(9), result.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
   
   public boolean pinjam(
        int id_buku, 
        int id_siswa, 
        int id_admin_pinjam,
        long tanggal_pinjam,
        int max_hari
   ) {
     try {
        Object[] object = {id_buku, id_siswa , id_admin_pinjam, "SP", tanggal_pinjam, max_hari};
        
        String query = "INSERT INTO `peminjaman` (id_buku, id_siswa, id_admin_pinjam, status, tanggal_pinjam, max_hari) VALUES " + objectToString(object);
        
        executeQuery2(query);
        
        return true;
     } catch(Exception ex) {
        System.out.println(ex);
        return false;
     }
   }
   
    public boolean pengembalian(
        int id_peminjaman,
        int id_admin_kembali,
        long tanggal_kembali,
        long denda
   ) {
     try {
        String query = "UPDATE peminjaman SET "
                + "id_admin_kembali = " + id_admin_kembali + 
                ", tanggal_kembali = " + tanggal_kembali + 
                ", denda = " + denda + 
                ", status = 'TDK'" +
                " WHERE id_peminjaman = " + id_peminjaman;
        executeQuery2(query);
        
        return true;
     } catch(Exception ex) {
        System.out.println(ex);
        return false;
     }
   }
   
   
     
   

    public Peminjaman detail(int id) {

        ResultSet result = executeQuery("SELECT * FROM `peminjaman` WHERE id_peminjaman = " + id);

        try {
            if (result.next()) {
                return  new Peminjaman(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4),
                        result.getInt(5), result.getString(6), result.getLong(7), result.getLong(8),
                        result.getLong(9), result.getInt(10));

            }
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean hapus(int id) {
        try {
            executeQuery2("DELETE FROM `peminjaman` WHERE `id_peminjaman` = " + id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
