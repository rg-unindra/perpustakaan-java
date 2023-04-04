/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;

/**
 *
 * @author Farhan Fadila
 */
public class Peminjaman {
    public int id_peminjaman;
    public int id_buku;
    public int id_siswa;
    public int id_admin_pinjam;
    public int id_admin_kembali;
    public String status;
    public long tanggal_pinjam;
    public long tanggal_kembali;
    public long denda;

    public Peminjaman(
            int id_peminjaman, 
            int id_buku, 
            int id_siswa, 
            int id_admin_pinjam,
            int id_admin_kembali, 
            String status, 
            long tanggal_pinjam, 
            long tanggal_kembali, 
            long denda
    ) {
        this.id_peminjaman = id_peminjaman;
        this.id_buku = id_buku;
        this.id_siswa = id_siswa;
        this.id_admin_pinjam = id_admin_pinjam;
        this.id_admin_kembali = id_admin_kembali;
        this.status = status;
        this.tanggal_pinjam = tanggal_pinjam;
        this.tanggal_kembali = tanggal_kembali;
        this.denda = denda;
    }

    @Override
    public String toString() {
        return "Peminjaman{" +
                "id_peminjaman=" + id_peminjaman +
                ", id_buku=" + id_buku +
                ", id_siswa=" + id_siswa +
                ", id_admin_pinjam=" + id_admin_pinjam +
                ", id_admin_kembali=" + id_admin_kembali +
                ", status='" + status + '\'' +
                ", tanggal_pinjam=" + tanggal_pinjam +
                ", tanggal_kembali=" + tanggal_kembali +
                ", denda=" + denda +
                '}';
    }
}

