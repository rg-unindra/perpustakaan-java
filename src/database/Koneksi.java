/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Farhan Fadila
 */
public  class Koneksi {
    final String databaseName = "perpustakaan";
    public Connection con;
    public Statement statement;
    
    
    public void start() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            // INISIALISASI CONNECTION
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, "root", "");
            createStatement();
            System.out.println("Database Started");
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println("DATABASE ERROR" + ex);
        }
    }
    
    public ResultSet executeQuery(String query) {
       ResultSet rs = null;
       if(statement == null) {
           try {
               
               createStatement();
           } catch (SQLException ex) {
               System.out.println("Execute Query Exception => " + query + ", Exception " + ex);
               Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
        try {
           rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return rs;
    }
    
       
    public void  executeQuery2(String query) {
       if(statement == null) {
           try {
               createStatement();
           } catch (SQLException ex) {
               Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            System.out.println("Execute Query Exception => " + query + ", Exception " + ex);
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void createStatement() throws SQLException {
        if(con == null) {
            start();
        }
        
        // INISIALISASI STATEMENT
        statement = con.createStatement();
    }
    
    public String objectToString(Object[] objects) { 
        String query = " (";
        for(int i = 0; i < objects.length; i++) {
            Object o = objects[i];
            boolean isLast = i == objects.length - 1;
            if(isLast) {
                query+= "'" + o + "')";
            } else {
                query+= "'" + o + "',";
            }
        }
        return query;
    }
    
    
    
    public boolean isIDExist(String table, String field, String id)   {
        try {
           ResultSet result =   executeQuery("SELECT COUNT('" + field + "') FROM `" + table + "` WHERE " + field + " = '" + id + "'");
            
           if(result.next()) {
               return result.getInt(1) > 0;
           }
            return false;
        } catch(Exception ex) {
            return false;
        }
    }
}


