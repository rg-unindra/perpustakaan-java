/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buku;

/**
 *
 * @author KKP Unindra
 */
public class Buku {
    public String idBuku;
    public String judul;
    public String pengarang;
    public String penerbit;
    public int tahun;
    public int jumlah;
    public long tanggalMasuk;
    
    public Buku(
        String idBuku,
        String judul,
        String pengarang,
        String penerbit,
        int tahun,
        int jumlah,
        long tanggalMasuk
    ) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahun = tahun;
        this.jumlah = jumlah;
        this.tanggalMasuk = tanggalMasuk;
    }
    
    
    @Override
    public String toString() {
        return "Buku{" +
                "idBuku='" + idBuku + '\'' +
                ", judul='" + judul + '\'' +
                ", pengarang='" + pengarang + '\'' +
                ", penerbit='" + penerbit + '\'' +
                ", tahun=" + tahun +
                ", jumlah=" + jumlah +
                ", tanggalMasuk=" + tanggalMasuk +
                '}';
    }
}
