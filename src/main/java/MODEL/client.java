/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLER.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author TIGER FOX
 */
public class client {
    

    
    private String nom;
    private int tel;
    private String adresse;
    private int ID;
    

    //METHODES :

    public client(String nom, int tel, String adresse) {
        this.nom = nom;
        this.tel = tel;
        this.adresse = adresse;
    }

    public client() {
    }
    
   
    
    
    
    
    
    //GETTER ET SETTER 
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
}
