/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kategori;

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
public class KategoriController extends Koneksi {
   
    
    public KategoriController() {
        if(con == null) {
            start();
        }
    }
    
    
    public List<Kategori> data() {
         List<Kategori> temp = new ArrayList<>();


        ResultSet result = executeQuery("SELECT * FROM `kategori` ORDER BY `nama_kategori` ASC");

        try {
            while(result.next()) {
               temp.add(new Kategori(result.getInt(1), result.getString(2), result.getString(3)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(KategoriController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public boolean tambah(
        String nama,
        String keterangan
    ) {
        try {
          Object[] object = {nama, keterangan};
          executeQuery2("INSERT INTO `kategori` (nama_kategori, keterangan) VALUES " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Kategori Exception => " + ex);
            return false;
        }
    }
    
    
    public boolean edit( 
        int id,
        String nama,
        String keterangan
    ) {
        try {
          executeQuery2("UPDATE `kategori` SET nama_kategori = '" + nama + "', keterangan = '" + keterangan + "' WHERE id_kategori = " + id);
        
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Jabatan Exception => " + ex);
            return false;
        }
    }
    
    
     public Kategori detail(int id) {
        
        ResultSet result = executeQuery("SELECT * FROM `kategori` WHERE id_kategori = " + id);

        try {
            if(result.next()) {
               return new Kategori(result.getInt(1), result.getString(2), result.getString(3));
           
            }
        } catch (SQLException ex) {
           Logger.getLogger(KategoriController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     
     public boolean hapus(int id) {
        try {
            executeQuery2("DELETE FROM `kategori` WHERE `id_kategori` = " + id);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
