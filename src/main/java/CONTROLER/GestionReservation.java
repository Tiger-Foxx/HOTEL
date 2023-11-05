/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;

import MODEL.client;
import MODEL.reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author TIGER FOX
 */
public class GestionReservation {
    Connection con;
    Connexion cn=new Connexion();
    Connexion cn2=new Connexion();
    PreparedStatement pst;
    ResultSet res;
    
    public boolean Ajouter(reservation mp){
        ResultSet rs;
        try {
        Connection conn=cn.getConnection();
        String sql3 = "SELECT * FROM reservation WHERE numero_chambre = ? ORDER BY date DESC";
        PreparedStatement ps = conn.prepareStatement (sql3);
        ps.setInt(1, mp.getNumero_chambre ());
        rs = ps.executeQuery ();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "OUPS!!!");
            e.printStackTrace();
            return false;
        }
        
        
        String sql2="UPDATE chambres SET reserved=1 where numero_chambre="+mp.getNumero_chambre();
        String Operation = "Reservation ";
        String sujet=" Du client N° " +mp.getID_client()+" pour la chambre N° "+mp.getNumero_chambre()+" pour "+mp.getDuree()+" jours";
        String utilisateur=Connexion.name;
        // on prepare la requete SQL avec des variables qui vont recevoir les valeurs des attributs de l'objet pris en parametre 

        String sql="INSERT INTO reservation (date,duree,numero_chambre,ID_client) VALUES " + "(?,?,?,?)";
        
        try {
            if (!rs.next()) {
                
                try {
                    con=cn.getConnection();
                    pst=con.prepareStatement(sql);
                    pst.setDate(1, mp.getDate());
                    pst.setInt(2, mp.getDuree());
                    pst.setInt(3, mp.getNumero_chambre());
                    pst.setInt(4, mp.getID_client());
                    System.out.println(sql);
                    System.out.println(pst);
                    System.out.println(sql);
                    pst.executeUpdate();
                    con.close();
                    con=cn.getConnection();
                    pst=con.prepareStatement(sql2);
                    pst.executeUpdate();
                    LOG.ecrireLog(Operation, sujet, utilisateur);
                    JOptionPane.showMessageDialog(null, " Reservation EFFECTUEE ! ");
                    return true;
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "UNE EREUR S'EST PRODUITE DANS L'AJOUT ");
                    
                    e.printStackTrace();
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
                
                
            } else {
                JOptionPane.showMessageDialog(null, "LA CHAMBRE N°"+mp.getNumero_chambre()+" EST DEJA RESERVEE !");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionReservation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
   

    
    public List<reservation> Liste() {
        
      List<reservation> lis =new ArrayList<>();
      
      String nom="";
      
       String sql= "SELECT * FROM reservation ORDER BY date DESC;";
      
        try {
            con=cn.getConnection();
             pst=con.prepareStatement(sql);
             res=pst.executeQuery();
             
        while (res.next()) {
                reservation mo = new reservation();
                mo.setId_reservation(res.getInt("id_reservation"));
                mo.setDate(res.getDate("date"));
                mo.setID_client(res.getInt("ID_client"));
                mo.setDuree(res.getInt("duree"));
                mo.setNumero_chambre(res.getInt("numero_chambre"));
                
                
        try {
          String sql2= "select nom from client where ID="+mo.getID_client();
          Connection con2 = cn2.getConnection();
          PreparedStatement pst2 = con2.prepareStatement(sql2);
          ResultSet res2 = pst2.executeQuery();
             
             while (res2.next()) {
                 nom=res2.getString("nom");
            }
             mo.setNomClient(nom);
            con2.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERREUR DE RECUPERATION AVEC LA BD");
            e.getStackTrace();
            
        }    
        lis.add(mo);
                
        }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERREUR DE RECUPERATION AVEC LA BD");
            e.getStackTrace();
            
        }
        return lis;
    }
    
    
    
    public boolean Supprimer(int selectedReservID,int NumChambre) {
        String Operation = "Suppression Réservation ";
        String sujet=" Reservation N° "+String.valueOf(selectedReservID) +" de la chambre N° "+NumChambre ;
        String utilisateur=Connexion.name;
        String sql="DELETE FROM reservation WHERE id_reservation=?";
        String sql2="UPDATE chambres SET reserved=0 where numero_chambre="+NumChambre;
        try {
            con=cn.getConnection();
            pst=con.prepareStatement(sql);
            pst.setInt(1, selectedReservID);
            System.out.println(sql);
            System.out.println(pst);

            pst.executeUpdate();
            con.close();
            
            con=cn.getConnection();
            pst=con.prepareStatement(sql2);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Réservation Supprimée !!!");
            LOG.ecrireLog(Operation, sujet, utilisateur);
            
             return true;
        } catch (Exception e) {
                            e.printStackTrace();
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "UNE erreur s'est produite ");


            e.getStackTrace();
             return false;
        }
    }
    
    
    public List<reservation> Rechercher(String numChambre,String IDclient){
        String nom="";
      List<reservation> lis =new ArrayList<>();
      String sql="";
     if (numChambre.equals("") && !IDclient.equals("") ) {
        sql="SELECT * FROM reservation WHERE  ID_client ="+IDclient+" ORDER BY date DESC ;";

       }
       if (IDclient.equals("") && !numChambre.equals("")) {
        sql="SELECT * FROM reservation WHERE  numero_chambre ="+numChambre+" ORDER BY date DESC;";

       }
       if (!IDclient.equals("") && !numChambre.equals("")) {
         sql="SELECT * FROM reservation WHERE numero_chambre="+numChambre+"  AND ID_client="+IDclient+" ORDER BY date DESC;";

       }
       if (IDclient.equals("") && numChambre.equals("")) {
         sql="SELECT * FROM reservation ORDER BY date DESC;";

       }
        try {
            System.out.println(sql);
            con=cn.getConnection();
             pst=con.prepareStatement(sql);
             res=pst.executeQuery();
             
        while (res.next()) {
                reservation mo = new reservation();
                mo.setId_reservation(res.getInt("id_reservation"));
                mo.setDate(res.getDate("date"));
                mo.setID_client(res.getInt("ID_client"));
                mo.setDuree(res.getInt("duree"));
                mo.setNumero_chambre(res.getInt("numero_chambre"));
                
                
        try {
          String sql2= "select nom from client where ID="+mo.getID_client();
          Connection con2 = cn2.getConnection();
          PreparedStatement pst2 = con2.prepareStatement(sql2);
          ResultSet res2 = pst2.executeQuery();
             
             while (res2.next()) {
                 nom=res2.getString("nom");
            }
            mo.setNomClient(nom);
            con2.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERREUR DE RECUPERATION AVEC LA BD");
            e.printStackTrace();
            
        }    
        lis.add(mo);
                
        }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERREUR DE RECUPERATION AVEC LA BD");
            e.printStackTrace();
            
        }
        return lis;
   }
    
    public void MiseAjour(){
        String Operation = "MISE A JOUR DES RESERVATIONS";
        String sujet="Suppression des réservations expirées";
        String utilisateur=Connexion.name;
        
        
        String sql="DELETE FROM reservation WHERE date + INTERVAL duree DAY < CURDATE();";
        String sql2=" UPDATE chambres SET reservee = 0 WHERE numero_chambre NOT IN (SELECT numero_chambre FROM reservation);";
        try {
            con=cn.getConnection();
            pst=con.prepareStatement(sql);
            System.out.println(sql);
            System.out.println(pst);

            pst.executeUpdate();
            pst=con.prepareStatement(sql2);
            System.out.println(sql2);
            System.out.println(pst);
            
            JOptionPane.showMessageDialog(null, "Réservations Mise à jour !!!");
            LOG.ecrireLog(Operation, sujet, utilisateur);
            
        } catch (Exception e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "UNE erreur s'est produite ");


            e.printStackTrace();
        }
        
    }


}
