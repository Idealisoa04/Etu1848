package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import function.*;
import server.*;
import affichage.*;

public class Main {
    public static void main(String[] args) {

        try {
            
            Fenetre fen = new Fenetre();
            fen.create();
            Client clt = new Client();
            clt.setFen(fen);
           

        //    Client client = new Client(2004,"localhost");

        //    client.send("D:/TexteH.txt");

        //    svrP.getSend();

           
            // client.sendAction("download");
            // svrP.verify();
            // svr1.get();
            // svr2.get();
            // svr3.get();

            //ex1.sendAll();

            // ex1.sendSmth("Texte1.txt");
            // ex1.sendSmth("Texte2.txt");
            // ex1.sendSmth("Texte3.txt");

            // Exemple ex2 = new Exemple();
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        
	}
}