/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peminjaman;

import buku.Buku;

/**
 *
 * @author Farhan Fadila
 */
public class CheckoutItem {
    public Buku buku;
    public int count;
    
    public CheckoutItem(
       Buku buku,
       int count
    ) {
        this.buku = buku;
        this.count = count;
    }
    
    public void setCount(int value){
        this.count=value;
    }
    
    
    @Override
    public String toString() {
        return  buku + ",  " + count;
    }
}
