/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;

import MODEL.client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author TIGER FOX
 */
public class GestionClient {
    Connection con;
    Connexion cn=new Connexion();
    PreparedStatement pst;
    ResultSet res;
    
    public boolean Ajouter(client mp){
        String Operation = "Ajout Client ";
        String sujet=" Client - " +mp.getNom() ;
        String utilisateur=Connexion.name;
        // on prepare la requete SQL avec des variables qui vont recevoir les valeurs des attributs de l'objet pris en parametre 

        String sql="INSERT INTO client (nom,tel,adresse) VALUES " + "(?,?,?)";
        try {
            con=cn.getConnection();
            pst=con.prepareStatement(sql);
            pst.setString(1, mp.getNom());
            pst.setInt(2, mp.getTel());
            pst.setString(3, mp.getAdresse());
            System.out.println(sql);
            System.out.println(pst);
             System.out.println(sql);
            pst.executeUpdate();
            LOG.ecrireLog(Operation, sujet, utilisateur);
            JOptionPane.showMessageDialog(null, "Client Ajouté !!! ");
             return true;
        } catch (Exception e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "UNE EREUR S'EST PRODUITE DANS L'AJOUT ");

            e.getStackTrace();
             return false;
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                    System.out.println("errrrrrrrrrrrr");

                e.getStackTrace();
            }
            
        }
        
    }

    public List<client> Liste() {
        
      List<client> lis =new ArrayList<>();
      String sql= "select * from client";
        try {
            con=cn.getConnection();
             pst=con.prepareStatement(sql);
             res=pst.executeQuery();
             
             while (res.next()) {
                client mo = new client();
                mo.setID(res.getInt("ID"));
                mo.setNom(res.getString("nom"));
                mo.setAdresse(res.getString("adresse"));
                mo.setTel(res.getInt("tel"));
                lis.add(mo);  
            }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERREUR DE RECUPERATION AVEC LA BD");
            e.getStackTrace();
            
        }
        return lis;
    }


    public boolean Supprimer(int selectedIndivID) {
        String Operation = "Suppression Client";
        String sujet="Client :" +String.valueOf(selectedIndivID) ;
        String utilisateur=Connexion.name;
        String sql="DELETE FROM client WHERE id = ?";
        try {
            con=cn.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1, selectedIndivID);
            System.out.println(sql);
            System.out.println(pst);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Client Supprimé !!!");
            LOG.ecrireLog(Operation, sujet, utilisateur);
             return true;
        } catch (Exception e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "UNE erreur s'est produite ");


            e.getStackTrace();
             return false;
        }
    }
    
    
    
   public List<client> Rechercher(String nom,String tel,String adresse){
      List<client> lis =new ArrayList<>();
      String sql= "SELECT * FROM client WHERE nom LIKE '%"+nom+"%'  AND tel LIKE '%"+tel+"%' AND adresse LIKE '%"+adresse+"%';";
        try {
            con=cn.getConnection();
             pst=con.prepareStatement(sql);
             System.out.println(sql);
             res=pst.executeQuery();
             System.out.println(pst);
             
             while (res.next()) {
                client mo = new client();
                mo.setID(res.getInt("ID"));
                mo.setNom(res.getString("nom"));
                mo.setAdresse(res.getString("adresse"));
                mo.setTel(res.getInt("tel"));
                lis.add(mo);  
                
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lis;
   }
   
   
    String verfyNom(String nom){
        if ("".equals(nom)) {
            return "nom";
        }
        else{
            return "'"+nom+"'";
        }
    }
    String verfyTel(String nom){
        if ("".equals(nom)) {
            return "tel";
        }
        else{
            return ""+nom+"";
        }
    }
    String verfyAdresse(String nom){
        if ("".equals(nom)) {
            return "adresse";
        }
        else{
            return  "'"+nom+"'";
        }
    }
  
   

    public void Modifier(String name, String tel, String adresse, int ClientID) {
        String Operation = "Modification Client ";
        String sujet=" Client numero  " +ClientID + " ; nom = "+verfyNom(name)+" ; addresse = "+verfyAdresse(adresse)+" ; tel = "+verfyTel(tel) ;
        String utilisateur=Connexion.name;
        
        String sql="UPDATE client SET nom ="+verfyNom(name)+", adresse ="+verfyAdresse(adresse)+", tel ="+verfyTel(tel)+"  WHERE id ="+ClientID;
        System.out.println(sql);
        try {
            con=cn.getConnection();
             pst=con.prepareStatement(sql);
             pst.executeUpdate();
             LOG.ecrireLog(Operation, sujet, utilisateur);
             JOptionPane.showMessageDialog(null, "Client modifié !!!\nVeuillez Actualiser la table après chaque modification ");
             
             
        } catch (Exception e) {
            
             JOptionPane.showMessageDialog(null, "ERREUR l'ors de la modification !!! ");
             e.printStackTrace();
        }
    }
    
    
    public void Annuler(){
        
        String sql="ROLLBACK";
        try {
            con=cn.getConnection();
            pst=con.prepareStatement(sql);
            pst.executeQuery(sql);
            JOptionPane.showMessageDialog(null, "Action annulée !!!");
        } catch (Exception e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "UNE erreur s'est produite ");


            e.getStackTrace();
        }
    }
    
}
