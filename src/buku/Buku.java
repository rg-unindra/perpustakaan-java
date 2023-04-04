/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buku;

/**
 *
 * @author Farhan Fadila
 */
public class Buku {
    int id;
    int id_kategori;
    int id_rak;
    String judul;
    String keterangan;
    String isbn;
    String penerbit;
    String pengarang;
    int tahun;
    int halaman;
    int jumlah;
    long tanggal_masuk;
    
    public Buku(
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
        long tanggal_masuk
    ) {
        this.id = id;
        this.id_kategori = id_kategori;
        this.id_rak = id_rak;
        this.judul = judul;
        this.keterangan = keterangan;
        this.isbn = isbn;
        this.penerbit = penerbit;
        this.pengarang = pengarang;
        this.tahun = tahun;
        this.halaman = halaman;
        this.jumlah = jumlah;
        this.tanggal_masuk = tanggal_masuk;
    }
}
