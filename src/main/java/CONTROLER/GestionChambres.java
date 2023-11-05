/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;

import MODEL.chambre;
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
public class GestionChambres {
    
    Connection con;
    Connexion cn=new Connexion();
    PreparedStatement pst;
    ResultSet res;
    
    public boolean Ajouter(chambre mp){
        String Operation = "Ajout Chambre ";
        String sujet=" ; etoiles  : " +mp.getEtoiles()+" ; etage: "+mp.getEtage()+" ; description : "+mp.getDescription();
        String utilisateur=Connexion.name;
        // on prepare la requete SQL avec des variables qui vont recevoir les valeurs des attributs de l'objet pris en parametre 

        String sql="INSERT INTO chambres (etage,etoiles,description) VALUES " + "(?,?,?)";
        try {
            con=cn.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1, mp.getEtage());
            pst.setInt(2, mp.getEtoiles());
            pst.setString(3, mp.getDescription());
            System.out.println(sql);
            System.out.println(pst);
             System.out.println(sql);
            pst.executeUpdate();
            LOG.ecrireLog(Operation, sujet, utilisateur);
            JOptionPane.showMessageDialog(null, "Chambre Ajoutée !!! ");
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

    public List<chambre> Liste() {
        
      List<chambre> lis =new ArrayList<>();
      String sql= "select * from chambres";
        try {
            con=cn.getConnection();
             pst=con.prepareStatement(sql);
             res=pst.executeQuery();
             
             while (res.next()) {
                chambre mo = new chambre();
                mo.setDescription(res.getString("description"));
                mo.setEtage(res.getInt("etage"));
                mo.setEtoiles(res.getInt("etoiles"));
                mo.setNumero_chambre(res.getInt("numero_chambre"));
                mo.setReservee(res.getBoolean("reserved"));
                lis.add(mo);  
            }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERREUR DE RECUPERATION AVEC LA BD");
            e.printStackTrace();
            
        }
        return lis;
    }


    public boolean Supprimer(int selectedChamberID) {
        String Operation = "Suppression Chambres";
        String sujet="chambre :" +String.valueOf(selectedChamberID) ;
        String utilisateur=Connexion.name;
        String sql="DELETE FROM chambres WHERE numero_chambre = ?";
        try {
            con=cn.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1, selectedChamberID);
            System.out.println(sql);
            System.out.println(pst);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Chambre Supprimée !!!");
            LOG.ecrireLog(Operation, sujet, utilisateur);
             return true;
        } catch (Exception e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "UNE erreur s'est produite ");


            e.getStackTrace();
             return false;
        }
    }
    
    
    
   public List<chambre> Rechercher(String etage,String etoiles,String description,int reservee){
      List<chambre> lis =new ArrayList<>();
       System.out.println("etage :/"+etage+"/etoiles:/"+etoiles+"description:/"+description);
      
      String sql="";
       if (etage.equals("") && !etoiles.equals("") ) {
        sql=(reservee==0)?"SELECT * FROM chambres WHERE  etoiles ="+etoiles+" AND description LIKE '%"+description+"%';":"SELECT * FROM chambres WHERE  etoiles ="+etoiles+" AND description LIKE '%"+description+"%' AND reserved=0;";

       }
       if (etoiles.equals("") && !etage.equals("")) {
        sql=(reservee==0)?"SELECT * FROM chambres WHERE  etage ="+etage+" AND description LIKE '%"+description+"%';":"SELECT * FROM chambres WHERE  etage ="+etage+" AND description LIKE '%"+description+"%' AND reserved=0;";

       }
       if (etage.equals("") && etoiles.equals("")) {
         sql=(reservee==0)?"SELECT * FROM chambres WHERE  description LIKE '%"+description+"%';":"SELECT * FROM chambres WHERE  description LIKE '%"+description+"%' AND reserved=0;";

       }
       if (!etage.equals("") && !etoiles.equals("")) {
         sql=(reservee==0)?"SELECT * FROM chambres WHERE etage ="+etage+" AND etoiles ="+etoiles+" AND description LIKE '%"+description+"%';":"SELECT * FROM chambres WHERE etage ="+etage+" AND etoiles ="+etoiles+" AND description LIKE '%"+description+"%' AND reserved=0;";

       }
       
       System.out.println("sql : "+sql);
      try {
            con=cn.getConnection();
             pst=con.prepareStatement(sql);
             System.out.println(pst);
             res=pst.executeQuery();
             
             while (res.next()) {
                chambre mo = new chambre();
                mo.setDescription(res.getString("description"));
                mo.setEtage(res.getInt("etage"));
                mo.setEtoiles(res.getInt("etoiles"));
                mo.setNumero_chambre(res.getInt("numero_chambre"));
                mo.setReservee(res.getBoolean("reserved"));
                lis.add(mo);  
            }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERREUR DE RECUPERATION AVEC LA BD");
            e.printStackTrace();
        }
        return lis;
   }  
    
}
