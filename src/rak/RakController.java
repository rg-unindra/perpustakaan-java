/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rak;


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
public class RakController extends Koneksi {
     public RakController() {
        if(con == null) {
            start();
        }
    }
    
    
    public List<Rak> data() {
         List<Rak> temp = new ArrayList<>();


        ResultSet result = executeQuery("SELECT * FROM `rak` ORDER BY `nama_rak` ASC");

        try {
            while(result.next()) {
               temp.add(new Rak(result.getInt(1), result.getString(2), result.getString(3)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(RakController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public boolean tambah(
        String nama,
        String keterangan
    ) {
        try {
          Object[] object = {nama, keterangan};
          executeQuery2("INSERT INTO `rak` (nama_rak, keterangan) VALUES " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Rak Exception => " + ex);
            return false;
        }
    }
    
    
    public boolean edit( 
        int id,
        String nama,
        String keterangan
    ) {
        try {
          executeQuery2("UPDATE `rak` SET nama_rak = '" + nama + "', keterangan = '" + keterangan + "' WHERE id_rak = '" + id + "'");
        
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Rak Exception => " + ex);
            return false;
        }
    }
    
    
     public Rak detail(int id) {
        
        ResultSet result = executeQuery("SELECT * FROM `rak` WHERE id_rak = " + id + "");

        try {
            if(result.next()) {
               return new Rak(result.getInt(1), result.getString(2), result.getString(3));
           
            }
        } catch (SQLException ex) {
           Logger.getLogger(RakController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     
     public boolean hapus(int id) {
        try {
            executeQuery2("DELETE FROM `rak` WHERE `id_rak` = " + id);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
