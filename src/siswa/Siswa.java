/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siswa;

/**
 *
 * @author Farhan Fadila
 */
public class Siswa {
    public int id;
    public String nisn;
    public String nama_siswa;
    public String telepon;
    public int tahun_angkatan;
    
    
  public  Siswa( 
     int id,
     String nisn,
     String nama_siswa,
     String telepon,
     int tahun_angkatan   
    ) {
      this.id = id;
      this.nisn = nisn;
      this.nama_siswa = nama_siswa;
      this.telepon = telepon;
      this.tahun_angkatan = tahun_angkatan;
    }
}
