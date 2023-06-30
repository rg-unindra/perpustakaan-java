/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengembalian;

/**
 *
 * @author Farhan Fadila
 */
public class Pengembalian {
   public String idPengembalian;
   public String idPeminjaman;
   public String nisn;
   public int idAdminPengembalian;
   public long denda;
   public int lamaPinjam;
   public String tanggalPengembalian;
   
   
   public Pengembalian(
       String idPengembalian,
       String idPeminjaman,
       String nisn,
       int idAdminPengembalian,
       long denda,
       int lamaPinjam,
       String tanggalPengembalian
   ) {
       this.idPengembalian = idPengembalian;
       this.idPeminjaman = idPeminjaman;
       this.nisn = nisn;
       this.idAdminPengembalian = idAdminPengembalian;
       this.denda = denda;
       this.lamaPinjam = lamaPinjam;
       this.tanggalPengembalian = tanggalPengembalian;
   }
   
   @Override
   public String toString() {
       return String.format(
           "Pengembalian [idPengembalian=%s, idPeminjaman=%s, idAdminPengembalian=%d, denda=%d, tanggalPengembalian=%s]",
           idPengembalian,
           idPeminjaman,
           idAdminPengembalian,
           denda,
           tanggalPengembalian
       );
   }
}
