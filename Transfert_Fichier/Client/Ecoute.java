package listener;

import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeExpansionListener;

import org.w3c.dom.events.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import function.*;
import server.*;
import affichage.*;
public class Ecoute implements MouseInputListener{
    JButton bt = new JButton();
    JTextField texto = new JTextField();

    
    public Ecoute() {
    }

    public void mouseClicked(java.awt.event.MouseEvent e)  {
        bt = (JButton) e.getSource();
        try {
            Client clt = new Client(2004,"localhost");
            if (bt.getText().equalsIgnoreCase("upload")) {
                JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                choose.setDialogTitle("Selectionnez un fichier texte");
                choose.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier texte", "txt");
                choose.addChoosableFileFilter(filter);
                int returnValue = choose.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    System.out.println(choose.getSelectedFile().getAbsolutePath());
                     clt.send(choose.getSelectedFile().getAbsolutePath(),"upload");
                 }
            }
            if (bt.getText().equalsIgnoreCase("download")) {
                String txt = this.getTexto().getText();
                
                clt.send(txt, "download");
                Thread tr = new Thread();
                tr.run();
                if(clt.verify()==true){
                    tr.stop();
                }

            }
        } catch (Exception ei) {
            //TODO: handle exception
        }
       
    }
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    public JButton getBt() {
        return bt;
    }

    public void setBt(JButton bt) {
        this.bt = bt;
    }

    public JTextField getTexto() {
        return texto;
    }

    public void setTexto(JTextField text) {
        this.texto = text;
    }
}
