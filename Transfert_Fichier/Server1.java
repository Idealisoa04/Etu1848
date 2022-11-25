package server;
import java.io.*;
import java.net.*;
import java.util.Vector;
import function.*;

public class Server1{
    int port;
    String txt;
    ServerSocket server;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    } 

    public Server1(int port,String txt) {
        this.port = port;
        this.txt = txt;
        try {
            ServerSocket server = new ServerSocket( this.getPort() ); 
            this.setServer(server);
        } catch (Exception e) {
            //TODO: handle exception
        }
       
    }

    public void get() throws Exception{             ///recoit les parties du fichier et le stocke dans un fichier texte 
        try {
            Functions fct = new Functions();
                          
                                            
            Socket client = this.getServer().accept();           
            InputStream is = client.getInputStream(); 
            ObjectInputStream msg = new ObjectInputStream(is);  
            Object obj1  = msg.readObject();

            fct.writeLine(this.getTxt(),String.valueOf(obj1));
            
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void searchinFile() throws Exception{    ///recoit le nom du fichier que le client veut obtenir et cherche dans le fichier texte les parties respectives de ce fichier
        try {
            Socket client = this.getServer().accept();           
            InputStream is = client.getInputStream(); 
            ObjectInputStream msg = new ObjectInputStream(is);  
            Object obj1  = msg.readObject();

            Functions fct = new Functions();
            Vector vect  = fct.readerLine(this.getTxt());
            Vector vect1 = new Vector<>();
            for (int i = 0; i < vect.size(); i++) {
                vect1.add(String.valueOf(vect.elementAt(i)).split("=>"));
            }
            
            Socket clientSvr = new Socket("localhost", this.getPort());
            OutputStream os2 = clientSvr.getOutputStream();                         
            ObjectOutputStream message2 = new ObjectOutputStream(os2);
            String msg1 = "";
            for (int i = 0; i < vect1.size(); i++) {
                if (String.valueOf(vect1.elementAt(i)).equalsIgnoreCase(String.valueOf(obj1))) {
                   msg1 = msg1 + String.valueOf(vect1.elementAt(i-1)).equalsIgnoreCase(String.valueOf(obj1));
                }
            }
            message2.writeObject(msg1);

        } catch (Exception e) {
            //TODO: handle exception
        }
       

    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

   
    
}