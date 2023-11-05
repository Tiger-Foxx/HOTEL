/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.sql.Date;

/**
 *
 * @author TIGER FOX
 */
public class reservation {
    int id_reservation;
    Date date;
    int duree;
    int numero_chambre;
    int ID_client;
    String NomClient;

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String NomClient) {
        this.NomClient = NomClient;
    }

    public reservation() {
    }
    
    
    
    

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNumero_chambre() {
        return numero_chambre;
    }

    public void setNumero_chambre(int numero_chambre) {
        this.numero_chambre = numero_chambre;
    }

    public int getID_client() {
        return ID_client;
    }

    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    
    
    public reservation(int id_reservation, Date date, int duree, int numero_chambre, int ID_client) {
        this.id_reservation = id_reservation;
        this.date = date;
        this.duree = duree;
        this.numero_chambre = numero_chambre;
        this.ID_client = ID_client;
    }
    

    public reservation( Date date, int duree, int numero_chambre, int ID_client) {
        this.date = date;
        this.duree = duree;
        this.numero_chambre = numero_chambre;
        this.ID_client = ID_client;
    }
    
    
    
    
    
}
