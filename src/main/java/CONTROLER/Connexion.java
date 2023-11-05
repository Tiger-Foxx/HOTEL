/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLER;


/**
 *
 * @author TIGER FOX
 */

import com.ibatis.common.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Connexion {
    
        Connection con;
        public static String name;
        public static String passW;
        
     /*methode qui etablit la connexion avec la BD MySQL */
    public Connection getConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel",name,passW);
            
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur de Connection");
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    //methode pour creer la bd hotel chez l'utilisateur  a partir d'un script sql
    
    public static void testCreation(){
        String script = "src/hotel.sql";
        try {
            // Créer une connexion JDBC avec la base de données par défaut
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306?serverTimezone=UTC", 
                name, 
                passW
            );

            // Créer un ScriptRunner
            ScriptRunner runner = new ScriptRunner(conn,false,false);          
// Exécuter le script
            BufferedReader reader = new BufferedReader(new FileReader(script));
            runner.runScript(reader);

            // Fermer le reader et la connexion
            reader.close();
            conn.close();

        } catch (Exception e) {
            // Gérer les exceptions
            script = "hotel.sql";
        try {
            
            // Créer une connexion JDBC avec la base de données par défaut
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306?serverTimezone=UTC", 
                name, 
                passW
            );

            // Créer un ScriptRunner
            ScriptRunner runner = new ScriptRunner(conn,false,false);          
// Exécuter le script
            BufferedReader reader = new BufferedReader(new FileReader(script));
            runner.runScript(reader);

            // Fermer le reader et la connexion
            reader.close();
            conn.close();

        }
        catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur l'ors de la creation de la base de donnees"); 
        }
        }
        

        }
    
    
    
}

