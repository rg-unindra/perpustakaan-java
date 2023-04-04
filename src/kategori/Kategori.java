/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kategori;

/**
 *
 * @author Farhan Fadila
 */
public class Kategori {
    public int id;
    public String nama;
    public String keterangan;
    
    
    public Kategori(
        int id, 
        String namaKategori, 
        String keterangan
    ) {
        this.id = id;
        this.nama = namaKategori;
        this.keterangan = keterangan;
    }
    
    @Override
    public String toString() {
        return "Kategori(" + id + "," + nama + "," + keterangan + ")";
    }
}
