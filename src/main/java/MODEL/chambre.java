/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author TIGER FOX
 */
public class chambre {
    
    int numero_chambre;
    int etage;
    int etoiles;
    boolean reservee=false;
    String description;
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    

    public int getNumero_chambre() {
        return numero_chambre;
    }

    public void setNumero_chambre(int numero_chambre) {
        this.numero_chambre = numero_chambre;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getEtoiles() {
        return etoiles;
    }

    public void setEtoiles(int etoiles) {
        this.etoiles = etoiles;
    }

    public boolean isReservee() {
        return reservee;
    }

    public void setReservee(boolean reservee) {
        this.reservee = reservee;
    }

    public chambre() {
    }

    public chambre( int etage, int etoiles,String description) {
        this.numero_chambre = numero_chambre;
        this.etage = etage;
        this.etoiles = etoiles;
        this.description=description;
    }
    
    
     public chambre( int numero_chambre,int etage, int etoiles,String description) {
        this.numero_chambre = numero_chambre;
        this.etage = etage;
        this.etoiles = etoiles;
        this.description=description;
    }
    
    
    
}
