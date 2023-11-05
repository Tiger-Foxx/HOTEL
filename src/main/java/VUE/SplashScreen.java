/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VUE;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 *
 * @author TIGER FOX
 */
public class SplashScreen extends JWindow {
    public SplashScreen(int duration) {
        // Créez le contenu du splash screen ici
        JPanel content = (JPanel)getContentPane();
        
        
        try {
            String data=Files.readString(Paths.get("theme.txt"));
            /* si le mot white est dedans on applique le theme FlatLightLaf sinon le theme flatDarkLaf*/
            if (data.equals("White")) {
                content.setBackground(Color.WHITE);
            }
            else{
                content.setBackground(Color.DARK_GRAY);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
         }
        

        // Positionnez et dimensionnez la fenêtre
        int width = 450+100;
        int height = 115*2+100;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x, y, width, height);

        // Ajoutez une image et du texte
        JLabel label = new JLabel(new ImageIcon("20.png"));
        JLabel text2 = new JLabel("HOTEL FOX", JLabel.CENTER);
        text2.setFont(new java.awt.Font("Segoe UI", 1, 38));
        JLabel text = new JLabel("Veuillez patienter...", JLabel.CENTER);
        text.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        content.add(label, BorderLayout.CENTER);
        content.add(text, BorderLayout.NORTH);
        content.add(text2, BorderLayout.SOUTH);
        
        // Affichez le splash screen et attendez
        setVisible(true);
        
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setVisible(false);
    }
    
 }
