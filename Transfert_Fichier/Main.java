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

public class Main {
    public static void main(String[] args) {

        try {
            
           Server1 svr1 = new Server1(2005, "Texte1.txt");
           Server1 svr2 = new Server1(2006, "Texte2.txt");
           Server1 svr3 = new Server1(2007, "Texte3.txt");

           Server svrP = new Server(2004, 2005, 2006, 2007);
            Thread tr = new Thread();
            tr.start();
            while(svrP.getSend){
                System.out.println("hello");
                
                svr1.get();
                svr2.get();
                svr3.get();
            }

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