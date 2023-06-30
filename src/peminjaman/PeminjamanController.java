/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peminjaman;
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

        ResultSet result = executeQuery("SELECT * FROM `peminjaman` WHERE `status` = 'SDP' ORDER BY `tanggal_pinjam` DESC");

        try {
            while (result.next()) {
                temp.add(peminjaman(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
   
   public boolean pinjam(
        String idPeminjaman, 
        String idBuku, 
        String jumlah, 
        String nisn,
        int idAdminPinjam,
        String tanggalPinjam
   ) {
     try {
        Object[] object = {idPeminjaman, idBuku , jumlah, nisn, idAdminPinjam, "SDP", tanggalPinjam};
    
        String query = "INSERT INTO `peminjaman` ("
                + "id_peminjaman, "
                + "id_buku, "
                + "jumlah, "
                + "nisn, "
                + "id_admin_pinjam, "
                + "status, "
                + "tanggal_pinjam"
                + ") VALUES " + objectToString(object);
      
        executeQuery2(query);
        
        return true;
     } catch(Exception ex) {
        System.out.println(ex);
        return false;
     }
   }
   
    public boolean ubahStatusToSDK(
            String idPeminjaman
    ) {
        try {
            String updateQuery = "UPDATE peminjaman SET " +
                                "status = 'SDK' " +
                                "WHERE id_peminjaman = '" + idPeminjaman + "'";

            executeQuery2(updateQuery);

            return true;
        } catch (Exception ex) {
            System.out.println("Ubah status Exception => " + ex);
            return false;
        }
    }
   
   
   public Peminjaman detail(String idPeminjaman) {

        ResultSet result = executeQuery("SELECT * FROM `peminjaman` WHERE id_peminjaman = '" + idPeminjaman + "'" );

        try {
            if (result.next()) {
                return peminjaman(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   public List<Peminjaman> search(String query) {
       List<Peminjaman> temp = new ArrayList<>();
       
       
        String searchQuery = "SELECT * FROM `peminjaman`"
                + "WHERE `id_peminjaman` LIKE '%" + query + "%' AND `status` = 'SDP'";
        
     

        ResultSet result = executeQuery(searchQuery);

        try {
            while (result.next()) {
                temp.add(peminjaman(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
   

    public boolean hapus(String idPeminjaman) {
        try {
            executeQuery2("DELETE FROM `peminjaman` WHERE `id_peminjaman` = '" + idPeminjaman + "'");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean hapusByNISN(String nisn) {
        try {
            executeQuery2("DELETE FROM `peminjaman` WHERE `nisn` = '" + nisn + "'");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean isIDExist(String idPeminjaman) {
           return isIDExist("peminjaman", "id_peminjaman", idPeminjaman);
    }
    
    
    private Peminjaman peminjaman(ResultSet result) {
       try {
            return new Peminjaman(
                    result.getString(1), result.getString(2), result.getString(3), result.getString(4), 
                    result.getInt(5), 
                    result.getString(6), result.getString(7));
       } catch(SQLException ex) {
          ex.printStackTrace();
       }
       
       return null;
    }
}
