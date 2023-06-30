/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siswa;

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
public class SiswaController extends Koneksi {
    public SiswaController() {
        if(con == null) {
            start();
        }
    }
    
    
    public List<Siswa> data() {
         List<Siswa> temp = new ArrayList<>();


        ResultSet result = executeQuery("SELECT * FROM `siswa` GROUP BY `kelas` ORDER BY `kelas` ASC, `nama_siswa` ASC");

        try {
            while(result.next()) {
               temp.add(siswa(result));
            }
        } catch (SQLException ex) {
           Logger.getLogger(SiswaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public boolean tambah(
        String nisn,
        String namaSiswa,
        String kelas,
        String alamat,
        String telepon
    ) {
        try {
          Object[] object = {nisn, namaSiswa, kelas, alamat, telepon};
          executeQuery2("INSERT INTO `siswa` (nisn, nama_siswa, kelas, alamat, telepon) VALUES " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Exception => " + ex);
            return false;
        }
    }
    
    
    public boolean edit( 
        String nisn,
        String namaSiswa,
        String kelas,
        String alamat,
        String telepon
    ) {
        try {
        String updateQuery = "UPDATE siswa SET " +
                            "nama_siswa = '" + namaSiswa + "', " +
                            "kelas = '" + kelas + "', " +
                            "alamat = '" + alamat + "', " +
                            "telepon = '" + telepon + "' " +
                            "WHERE nisn = '" + nisn + "'";

        executeQuery2(updateQuery);
        
          return true;
        } catch(Exception ex) {
            System.out.println("Edit Exception => " + ex);
            return false;
        }
    }
    
    
     public Siswa detail(
        String nisn
     ) {
         
        ResultSet result = executeQuery("SELECT * FROM `siswa` WHERE nisn = '" + nisn + "'");
        
        try {
            if(result.next()) {
               return siswa(result);
            }
        } catch (SQLException ex) {
           Logger.getLogger(SiswaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
    public List<Siswa> search(String query) {
       List<Siswa> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `siswa` WHERE CONCAT(nisn, nama_siswa, kelas, alamat, telepon) LIKE '%" + query + "%' ORDER BY `nama_siswa` ASC");

        try {
            while (result.next()) {
                 temp.add(siswa(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SiswaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
     
     public boolean hapus(String nisn) {
        try {
            executeQuery2("DELETE FROM `siswa` WHERE `nisn` = " + nisn);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
     
     
    private Siswa siswa(ResultSet result) {
       try {
            return new Siswa(result.getString(1), result.getString(2), result.getString(3),
                            result.getString(4), result.getString(5));
       } catch(SQLException ex) {
          ex.printStackTrace();
       }
       
       return null;
    }
}
