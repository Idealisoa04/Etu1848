package affichage;
import java.awt.Color;

import javax.swing.*;
import java.awt.*;

import listener.Ecoute;
import listener.*;

public class Fenetre extends JFrame{
    String[] liste;

    public void create(){
        JFrame fenetre = new JFrame();
        JPanel pan = new JPanel();

        Ecoute ect = new Ecoute();

        JButton bt1 = new JButton("upload");
        JButton bt2 = new JButton("download");
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(100,50));

        ect.setTexto(text);
        bt1.addMouseListener(ect);
        bt2.addMouseListener(ect);

        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                pan.add(new JLabel(liste[i]));
            }
        }
        
        pan.add(bt1);
        pan.add(text);
        pan.add(bt2);
        

        pan.setBackground(Color.YELLOW);
        pan.setPreferredSize(new Dimension(100,100));
        pan.setBounds(0, 100, 500, 100);

        fenetre.setSize(new Dimension(500,500));
        fenetre.add(pan);
        fenetre.setVisible(true);
    }
}
