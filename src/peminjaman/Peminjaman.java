/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peminjaman;

/**
 *
 * @author Farhan Fadila
 */
public class Peminjaman {
    public String idPeminjaman;
    public String idBuku;
    public String jumlah;
    public String nisn;
    public int idAdminPinjam;
    public long tanggalPinjam;

    public Peminjaman(
         String idPeminjaman,
         String idBuku,
         String jumlah,
         String nisn,
         int idAdminPinjam,
         long tanggalPinjam
    ) {
        this.idPeminjaman = idPeminjaman;
        this.idBuku = idBuku;
        this.jumlah = jumlah;
        this.nisn = nisn;
        this.idAdminPinjam = idAdminPinjam;
        this.tanggalPinjam = tanggalPinjam;
    }

    @Override
    public String toString() {
        return "";
    }
}

