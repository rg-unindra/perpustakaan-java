/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rak;

/**
 *
 * @author Farhan Fadila
 */
public class Rak {
    public int id;
    public String nama;
    public String keterangan;
    
    
    public Rak(
        int id, 
        String nama, 
        String keterangan
    ) {
        this.id = id;
        this.nama = nama;
        this.keterangan = keterangan;
    }
    
    @Override
    public String toString() {
        return "Rak(" + id + "," + nama + "," + keterangan + ")";
    }
}
