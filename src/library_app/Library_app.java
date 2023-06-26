/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_app;

import authentiocation.AuthenticationController;
import authentiocation.FormLogin;
import home.HomePage;
import javax.swing.JFrame;

/**
 *
 * @author Farhan Fadila
 */
public class Library_app {
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      final AuthenticationController authenticationController = new AuthenticationController() ;
     
      boolean login = authenticationController.isLoggedIn();
        
        JFrame nextPage;
        if(login) {
            nextPage = new HomePage();
        } else {
            nextPage = new FormLogin();
        }
        
       
       nextPage.setAlwaysOnTop(true);
       nextPage.setVisible(true);
    }
}
