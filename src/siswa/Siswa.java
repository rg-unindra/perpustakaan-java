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
    public String nisn;
    public String namaSiswa;
    public String kelas;
    public String alamat;
    public String telepon;
    
    
  public  Siswa( 
        String nisn,
        String namaSiswa,
        String kelas,
        String alamat,
        String telepon
    ) {
      this.nisn = nisn;
      this.namaSiswa = namaSiswa;
      this.kelas = kelas;
      this.alamat = alamat;
      this.telepon = telepon;
    }
}
