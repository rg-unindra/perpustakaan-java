/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengembalian;
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
public class PengembalianController extends Koneksi {
    public PengembalianController() {
        if (con == null) {
            start();
        }
    }
    
    public List<Pengembalian> data() {
        List<Pengembalian> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `pengembalian` ORDER BY `tanggal_pengembalian` DESC");

        try {
            while (result.next()) {
                temp.add(pengembalian(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public Pengembalian detail(String idPengembalian) {

        ResultSet result = executeQuery("SELECT * FROM `pengembalian` WHERE id_pengembalian = '" + idPengembalian + "'" );

        try {
            if (result.next()) {
                return pengembalian(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Pengembalian> search(String query) {
       List<Pengembalian> temp = new ArrayList<>();
       
       
        String searchQuery = "SELECT * FROM `pengembalian` peng"
                + "JOIN `peminjaman` pem ON pem.id_peminjaman = peng.id_peminjaman"
                + "JOIN `siswa` s ON s.nisn = peng.nisn"
                + "WHERE CONCAT(peng.id_pengembalian, peng.id_peminjaman, peng.nisn, peng.tanggal_pengembalian, s.nama_siswa) "
                + "LIKE '% " + query + "%'";

        ResultSet result = executeQuery(searchQuery);

        try {
            while (result.next()) {
                temp.add(pengembalian(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public boolean kembali(
        String idPengembalian, 
        String idPeminjaman, 
        String nisn,
        int idAdminPengembalian,
        long denda,
        int lamaPinjam,
        String tanggalPengembalian
   ) {
     try {
        Object[] object = {
                        idPengembalian,
                        idPeminjaman, 
                        nisn, 
                        idAdminPengembalian, 
                        denda, 
                        lamaPinjam, 
                        tanggalPengembalian
                        };
    
        String query = "INSERT INTO `pengembalian` ("
                + "id_pengembalian, "
                + "id_peminjaman, "
                + "nisn, "
                + "id_admin_pengembalian, "
                + "denda, "
                + "lama_pinjam, "
                + "tanggal_pengembalian"
                + ") VALUES " + objectToString(object);
      
        executeQuery2(query);
        
        return true;
     } catch(Exception ex) {
        System.out.println(ex);
        return false;
     }
   }
    
    public boolean isIDExist(String idPengembalian) {
           return isIDExist("pengembalian", "id_pengembalian", idPengembalian);
    }
    
    private Pengembalian pengembalian(ResultSet result) {
       try {
            return new Pengembalian(
                    result.getString(1), result.getString(2), result.getString(3),
                    result.getInt(4),
                    result.getLong(5),
                    result.getInt(6),
                    result.getString(7)
            );
       } catch(SQLException ex) {
          ex.printStackTrace();
       }
       
       return null;
    }
}
