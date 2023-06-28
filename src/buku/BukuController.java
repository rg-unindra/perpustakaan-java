/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buku;

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
public class BukuController extends Koneksi {
    public BukuController() {
        if (con == null) {
            start();
        }
    }

    public List<Buku> data() {
        List<Buku> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `buku` ORDER BY `judul` ASC");

        try {
            while (result.next()) {
                temp.add(buku(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }

    public boolean simpan(
        String idBuku,
        String judul,
        String pengarang,
        String penerbit,
        int tahun,
        int jumlah,
        long tanggalMasuk
    ) {
        try {
            boolean isExist = isBookExist(idBuku);
            
            if(isExist) {
             return edit(idBuku, judul, pengarang, penerbit, tahun, jumlah, tanggalMasuk);
            }
            Object[] object = {idBuku, judul, pengarang, penerbit, tahun, jumlah, tanggalMasuk};
            executeQuery2("INSERT INTO `buku` (id_buku, judul, pengarang, penerbit, tahun,jumlah, tanggal_masuk) VALUES " + objectToString(object));
            return true;
        } catch (Exception ex) {
            System.out.println("Tambah Buku Exception => " + ex);
            return false;
        }
    }

    public boolean edit(
        String idBuku,
        String judul,
        String pengarang,
        String penerbit,
        int tahun,
        int jumlah,
        long tanggalMasuk
    ) {
        try {
            String updateQuery = "UPDATE buku SET " +
                                "judul = '" + judul + "', " +
                                "pengarang = '" + pengarang + "', " +
                                "penerbit = '" + penerbit + "', " +
                                "tahun = " + tahun + ", " +
                                "jumlah = " + jumlah + ", " +
                                "tanggal_masuk = " + tanggalMasuk + " " +
                                "WHERE id_buku = '" + idBuku + "'";

            executeQuery2(updateQuery);

            return true;
        } catch (Exception ex) {
            System.out.println("Tambah Exception => " + ex);
            return false;
        }
    }

    public Buku detail(String idBuku) {

        ResultSet result = executeQuery("SELECT * FROM `buku` WHERE id_buku = '" + idBuku + "'" );

        try {
            if (result.next()) {
                return buku(result);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean hapus(String idBuku) {
        try {
            executeQuery2("DELETE FROM `buku` WHERE `id_buku` = '" + idBuku + "'");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public List<Buku> search(String query) {
       List<Buku> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `buku` WHERE CONCAT(id_buku, judul, pengarang, penerbit, tahun, jumlah) LIKE '%" + query + "%' ORDER BY `judul` ASC");

        try {
            while (result.next()) {
                temp.add(buku(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public boolean isBookExist(String idBuku) {
           return isIDExist("buku", "id_buku", idBuku);
    }
    
    private Buku buku(ResultSet result) {
       try {
            return new Buku(result.getString(1),result.getString(2), result.getString(3), result.getString(4),
                            result.getInt(5), result.getInt(6),
                            result.getLong(7));
       } catch(SQLException ex) {
          ex.printStackTrace();
       }
       
       return null;
    }
} 
