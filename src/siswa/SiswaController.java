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


        ResultSet result = executeQuery("SELECT * FROM `siswa` ORDER BY `nama_siswa` ASC");

        try {
            while(result.next()) {
               temp.add(new Siswa(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(SiswaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public boolean tambah(
        String nisn,
        String nama_siswa,
        String telepon,
        int tahun_angkatan
    ) {
        try {
          Object[] object = {nisn, nama_siswa, telepon, tahun_angkatan};
          executeQuery2("INSERT INTO `siswa` (nisn, nama_siswa, telepon, tahun_angkatan) VALUES " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Siswa Exception => " + ex);
            return false;
        }
    }
    
    
    public boolean edit( 
        int id,
        String nisn,
        String nama_siswa,
        String telepon,
        int tahun_angkatan
    ) {
        try {
        String updateQuery = "UPDATE siswa SET " +
        "nisn = '" + nisn + "', " +
        "nama_siswa = '" + nama_siswa + "', " +
        "telepon = '" + telepon + "', " +
        "tahun_angkatan = " + tahun_angkatan + " " +
        "WHERE id_siswa = " + id;

        executeQuery2(updateQuery);
        
          return true;
        } catch(Exception ex) {
            System.out.println("Edit Siswa Exception => " + ex);
            return false;
        }
    }
    
    
     public Siswa detail(int id) {
         
        ResultSet result = executeQuery("SELECT * FROM `siswa` WHERE id_siswa = " + id + "");
        
        try {
            if(result.next()) {
               return new Siswa(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5));
            }
        } catch (SQLException ex) {
           Logger.getLogger(SiswaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
    public List<Siswa> search(String query) {
       List<Siswa> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `siswa` WHERE CONCAT(id_siswa, nisn, nama_siswa, telepon) LIKE '%" + query + "%' ORDER BY `judul` ASC");

        try {
            while (result.next()) {
                 temp.add(new Siswa(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SiswaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
     
     public boolean hapus(int id) {
        try {
            executeQuery2("DELETE FROM `siswa` WHERE `id_siswa` = " + id);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
