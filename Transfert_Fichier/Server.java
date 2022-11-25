package server;
import java.io.*;
import java.net.*;
import java.util.Vector;
import function.*;

public class Server{
    int port;
    int clport;
    int clport1;
    int clport2;
    ServerSocket server;

    
    public Server() {
        try {
            ServerSocket server = new ServerSocket( this.getPort() ); 
            this.setServer(server);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }


    public Server(int port, int clport,int clport1,int clport2) {
        this.port = port;
        this.clport = clport;
        this.clport1 = clport1;
        this.clport2 = clport2;
        try {
            ServerSocket server = new ServerSocket( this.getPort() ); 
            this.setServer(server);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }


    public void getSend() throws Exception{     ///recoit le fichier texte venant du client, le distribue en 3 parties et les renvoient au 3 server 
        try {
            Functions fct = new Functions();
                         
                                            
            Socket client = this.getServer().accept();           
            InputStream is = client.getInputStream(); 
            ObjectInputStream msg = new ObjectInputStream(is);  
            Object obj1  = msg.readObject(); 

            Vector vect = fct.readerLine(String.valueOf(obj1));

            Socket clientSvr = new Socket("localhost", this.getClport());
            OutputStream os = clientSvr.getOutputStream();                          
            ObjectOutputStream message = new ObjectOutputStream(os);
            Object obj  = String.valueOf(vect.elementAt(0));
            for (int i = 1; i < (int) vect.size()/3; i++) {
                 obj  = obj + "//"+String.valueOf(vect.elementAt(i)); 
            }
            message.writeObject(obj+"=> "+String.valueOf(obj1));

            Socket clientSvr1 = new Socket("localhost", this.getClport1());
            OutputStream os1 = clientSvr1.getOutputStream();                         
            ObjectOutputStream message1 = new ObjectOutputStream(os1);
            Object obj2  = String.valueOf(vect.elementAt((int) vect.size()/3));
            for (int i = ((int) vect.size()/3)+1 ; i < (int) vect.size()*2/3; i++) {
                obj2 = obj2 +"//" +String.valueOf(vect.elementAt(i));
            }
            message1.writeObject(obj2+"=> "+String.valueOf(obj1));  

            Socket clientSvr2 = new Socket("localhost", this.getClport2());
            OutputStream os2 = clientSvr2.getOutputStream();                         
            ObjectOutputStream message2 = new ObjectOutputStream(os2);
            Object obj3  = String.valueOf(vect.elementAt((int) vect.size()*2/3));
            for (int i = ((int) vect.size()*2/3)+1; i <  vect.size(); i++) {
                obj3 = obj3 +"//"+String.valueOf(vect.elementAt(i));
            }
            message2.writeObject(obj3+"=> "+String.valueOf(obj1));   
        
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public void sendSearch(Object obj1) throws Exception{       ///renvoit au 3 serveurs le nom du fichier que le client veut obtenir
        Socket clientSvr = new Socket("localhost", this.getClport());
        OutputStream os = clientSvr.getOutputStream();                          
        ObjectOutputStream message = new ObjectOutputStream(os);
        message.writeObject(String.valueOf(obj1));

        Socket clientSvr1 = new Socket("localhost", this.getClport1());
        OutputStream os1 = clientSvr1.getOutputStream();                         
        ObjectOutputStream message1 = new ObjectOutputStream(os1);
        message1.writeObject(String.valueOf(obj1));

        Socket clientSvr2 = new Socket("localhost", this.getClport2());
        OutputStream os2 = clientSvr2.getOutputStream();                         
        ObjectOutputStream message2 = new ObjectOutputStream(os2);
        message2.writeObject(obj1);
    }
    public void verify() throws Exception{                      ///verifie l action que le client veut effectue et agit selon cette action 
        try {
            Socket client = this.getServer().accept();           
            InputStream is = client.getInputStream(); 
            ObjectInputStream msg = new ObjectInputStream(is);  
            Object obj1  = msg.readObject();
            if (String.valueOf(obj1).equalsIgnoreCase("download")) {
                this.sendSearch(obj1);
            }
            if (String.valueOf(obj1).equalsIgnoreCase("upload")) {
                this.getSend();
            }
            
             
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }


    public int getClport() {
        return clport;
    }


    public void setClport(int clport) {
        this.clport = clport;
    }


    public int getClport1() {
        return clport1;
    }


    public void setClport1(int clport1) {
        this.clport1 = clport1;
    }


    public int getClport2() {
        return clport2;
    }


    public void setClport2(int clport2) {
        this.clport2 = clport2;
    }


    public ServerSocket getServer() {
        return server;
    }


    public void setServer(ServerSocket server) {
        this.server = server;
    }
}