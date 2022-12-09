package function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Vector;

public class Functions {

    
    
    public Functions() {
    }

    public Vector readerLine(String fichierTxt) throws Exception
    {
        Vector vect = new Vector<>();
        try{
            FileReader reader = new FileReader(fichierTxt);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line ;
    
            while ((line = bufferedReader.readLine()) != null) {
                vect.add(line);
            }

    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vect;

    }

    public void writeLine(String fichierTxt,String texte) throws Exception
    {
        try {
            FileWriter writer = new FileWriter(fichierTxt, true);
            writer.write("\n");	// write new line
            writer.write(texte);
            writer.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

   
}
