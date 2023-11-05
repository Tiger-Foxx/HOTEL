/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;

import java.io.FileWriter;
import java.util.Date;
// Importer la classe Desktop pour lancer une application externe
import java.awt.Desktop;

// Importer la classe File pour représenter un fichier
import java.io.File;

// Importer la classe IOException pour gérer les exceptions d'entrée/sortie
import java.io.IOException;

/**
 *
 * @author TIGER FOX
 */
public class LOG {
    // Définir la fonction qui prend trois chaînes de caractères comme paramètres
public static void ecrireLog(String operation, String sujet, String utilisateur) {
    try {
        // Ouvrir le fichier csv en mode écriture
        FileWriter writer = new FileWriter("log.csv", true);
        
        // Écrire les trois chaînes de caractères séparées par des virgules dans le fichier
        writer.write((" "+ operation + " ; Sur : " + sujet + " ; Par : " + utilisateur + " ; le : ").toUpperCase());
        // Écrire la date du jour dans le fichier
        writer.write(new Date().toString());
        // Passer à la ligne suivante dans le fichier
        writer.write("\n");
        // Fermer le fichier
        writer.close();
    } catch (Exception e) {
        // Afficher le message d'erreur en cas d'exception
        System.out.println(e.getMessage());
    }
}




// Définir la fonction qui prend le nom d'un fichier comme paramètre
public static void ouvrirFichier(String nomFichier) {
    try {
        // Créer un objet File à partir du nom du fichier
        File fichier = new File(nomFichier);
        // Vérifier si le fichier existe et si le système d'exploitation supporte la fonctionnalité Desktop
        if (fichier.exists() && Desktop.isDesktopSupported()) {
            // Obtenir l'instance de Desktop
            Desktop desktop = Desktop.getDesktop();
            // Lancer l'application associée au fichier
            desktop.open(fichier);
        } else {
            // Afficher un message d'erreur si le fichier n'existe pas ou si la fonctionnalité Desktop n'est pas supportée
            System.out.println("Le fichier " + nomFichier + " n'existe pas ou la fonctionnalité Desktop n'est pas supportée.");
        }
    } catch (IOException e) {
        // Afficher le message d'erreur en cas d'exception d'entrée/sortie
        System.out.println(e.getMessage());
    }
}

// Appeler la fonction avec le nom d'un fichier comme exemple


}
