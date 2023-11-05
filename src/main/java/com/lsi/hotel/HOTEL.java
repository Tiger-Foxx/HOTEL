/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.lsi.hotel;

import CONTROLER.Connexion;
import VUE.LOGIN;
import VUE.SplashScreen;
import VUE.Menu;
import javax.swing.*;
import java.awt.*; 
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIGER FOX
 */
public class HOTEL {

    public static void main(String[] args) {
        
        //ON VERIFIE SI LE SERVEUR MYSQL EST LANCE , SINON ON LE LANCE ? POUR VERIFIER ON ESSAIE D'ETABLIR UNE CONNEXION ET CREER UN STATEMENT
            
        try {
              /* on execute la commande 'mysqld--console'  */
              Process process = Runtime.getRuntime().exec(new String[]{"cmd", "/c", "mysqld", "--console"});
              System.out.println("lancement du serveur mysql ....");
              /* on met le programme en pause pendant 4s pour laisser le temps au serveuyr de se lancer  */            
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "ERREUR LORS DU LANCEMENT DU SERVEUR MYSQL\nASSUREZ-VOUS D'AVOIR mysql DANS VOS VARIABLES D'ENVIRONNEMENT!");
            e2.printStackTrace();
        }
            
        
        
        
        /* ON APPLIQUE LE THEM DARK OU LIGHT EN FONCTION DU CONTENU DU FOCHOER THEME.TXT */
        try {
            File MonFichier=new File("theme.txt");
            /*si le fichier n'existe pas encore on le cree et on ecrit Dark a l'interieur c'est le theme par defaut*/
            if (MonFichier.createNewFile()) {
                FileWriter MonWriter=new FileWriter("theme.txt");
                MonWriter.write("Dark");
                MonWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            String data=Files.readString(Paths.get("theme.txt"));
            /* si le mot white est dedans on applique le theme FlatLightLaf sinon le theme flatDarkLaf*/
            if (data.equals("White")) {
                FlatLightLaf.setup();
            }
            else{
                FlatDarkLaf.setup();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
         }
        
        /***** ON CREE LA BASE DE DONNEE SI ELLE N4EXISTE PAS DEJA  */
        
        
        
         /***** ON CREE LA BASE DE DONNEE SI ELLE N4EXISTE PAS DEJA  */
        
        new SplashScreen(4500);
        //new Menuold().setVisible(true);
        new LOGIN().setVisible(true);
    }
}

