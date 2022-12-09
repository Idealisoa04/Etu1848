package server;
import java.io.*;
import java.net.*;
import java.util.Vector;
import function.*;

public class Server1{
    int port;
    String txt;
    ServerSocket server;

    String princip;
    int portP;

public void setPortP(int portP) {
    this.portP = portP;
}
public void setPrincip(String princip) {
    this.princip = princip;
}
public int getPortP() {
    return portP;
}
public String getPrincip() {
    return princip;
}
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
            
            is.close();
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
            String txt = String.valueOf(obj1);

            Functions fct = new Functions();
            Vector vect  = fct.readerLine(this.getTxt());

            Vector vect1 = new Vector<>();
            Vector vect2 = new Vector<>();

            for (int i = 0; i < vect.size(); i++) {
                vect1.add(String.valueOf(vect.elementAt(i)).split("=>")[0]);
                vect2.add(String.valueOf(vect.elementAt(i)).split("=>")[1]);
            }

            String content = "";

            for (int index = 0; index < vect2.size(); index++) {
                if (String.valueOf(vect2.elementAt(index)).equalsIgnoreCase(txt)) {
                    content = content+ String.valueOf(vect1.elementAt(index))+"&&&";
                }
            }

            Socket clientSvr = new Socket(this.getPrincip(),this.getPortP());
            OutputStream os = clientSvr.getOutputStream();                          
            ObjectOutputStream message = new ObjectOutputStream(os);
            message.writeObject(content);
            // Socket clientSvr = new Socket("localhost", this.getPort());
            // OutputStream os2 = clientSvr.getOutputStream();                         
            // ObjectOutputStream message2 = new ObjectOutputStream(os2);
            // String msg1 = "";
            // for (int i = 0; i < vect1.size(); i++) {
            //     if (String.valueOf(vect1.elementAt(i)).equalsIgnoreCase(String.valueOf(obj1))) {
            //        msg1 = msg1 + String.valueOf(vect1.elementAt(i-1)).equalsIgnoreCase(String.valueOf(obj1));
            //     }
            // }
            // message2.writeObject(msg1);
                is.close();
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