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
                temp.add(new Buku(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4),
                        result.getString(5), result.getString(6), result.getString(7), result.getString(8),
                        result.getInt(9), result.getInt(10), result.getInt(11), result.getLong(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }

    public boolean tambah(
            int id_kategori,
            int id_rak,
            String judul,
            String keterangan,
            String isbn,
            String penerbit,
            String pengarang,
            int tahun,
            int halaman,
            int jumlah,
            long tanggal_masuk
    ) {
        try {
            Object[] object = { id_kategori, id_rak , judul, keterangan, isbn, penerbit, pengarang, tahun, halaman, jumlah, tanggal_masuk};
            executeQuery2("INSERT INTO `buku` (id_kategori, id_rak, judul, keterangan, isbn, penerbit, pengarang, tahun, halaman, jumlah, tanggal_masuk) VALUES " + objectToString(object));
            return true;
        } catch (Exception ex) {
            System.out.println("Tambah Buku Exception => " + ex);
            return false;
        }
    }

    public boolean edit(
            int id,
            int id_kategori,
            int id_rak,
            String judul,
            String keterangan,
            String isbn,
            String penerbit,
            String pengarang,
            int tahun,
            int halaman,
            int jumlah,
            long tanggal_masuk) {
        try {
            String updateQuery = "UPDATE buku SET " +
                                "id_kategori = " + id_kategori + ", " +
                                "id_rak = " + id_rak + ", " +
                                "judul = '" + judul + "', " +
                                "keterangan = '" + keterangan + "', " +
                                "isbn = '" + isbn + "', " +
                                "penerbit = '" + penerbit + "', " +
                                "pengarang = '" + pengarang + "', " +
                                "tahun = " + tahun + ", " +
                                "halaman = " + halaman + ", " +
                                "jumlah = " + jumlah + ", " +
                                "tanggal_masuk = " + tanggal_masuk + " " +
                                "WHERE id_buku = " + id;

            executeQuery2(updateQuery);

            return true;
        } catch (Exception ex) {
            System.out.println("Tambah Buku Exception => " + ex);
            return false;
        }
    }

    public Buku detail(int id) {

        ResultSet result = executeQuery("SELECT * FROM `buku` WHERE id_buku = " + id);

        try {
            if (result.next()) {
                return new Buku(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4),
                        result.getString(5), result.getString(6), result.getString(7), result.getString(8),
                        result.getInt(9), result.getInt(10), result.getInt(11), result.getLong(12));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BukuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean hapus(int id) {
        try {
            executeQuery2("DELETE FROM `buku` WHERE `id_buku` = " + id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
