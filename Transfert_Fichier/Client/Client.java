package server;
import java.io.*;
import java.net.*;
import java.util.Vector;

import function.*;
import affichage.*;
public class Client {
    int port;
    String id;
    Socket client;
    Fenetre fen;

    public void setFen(Fenetre fen) {
        this.fen = fen;
    }
    public Fenetre getFen() {
        return fen;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
    public Socket getClient() {
        return client;
    }

    public Client(int port,String id) throws Exception{
        this.port = port;
        this.id = id;
        Socket client = new Socket(this.getId(), this.getPort());   
        this.setClient(client);
    }
    public Client() {
    }
    public void setPort(int port) {
        this.port = port;
    }
    public int getPort() {
        return port;
    }
    public void sendAction(String btnName) throws Exception{
        try {                     
            OutputStream output = this.getClient().getOutputStream();                                 
            Object msg = btnName ;                                                          
            ObjectOutputStream objoutput = new ObjectOutputStream(output);                  
            objoutput.writeObject(msg);                                                      

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public boolean verify() throws Exception{
        try {
            InputStream is = client.getInputStream(); 
            ObjectInputStream msg = new ObjectInputStream(is);  
            Object obj1  = msg.readObject();
            String fst = String.valueOf(obj1);
            
            InputStream is1 = client.getInputStream(); 
            ObjectInputStream msg1 = new ObjectInputStream(is1);  
            Object obj  = msg1.readObject();
            String scd = String.valueOf(obj);

            if(fst.equalsIgnoreCase("update")){

            }
            if (fst.equalsIgnoreCase("download")) {
                InputStream is2 = client.getInputStream(); 
                ObjectInputStream msg2 = new ObjectInputStream(is2);  
                Object obj2  = msg2.readObject();
                String thrd = String.valueOf(obj2);

                System.out.println(thrd);
                this.get(thrd,scd);

                is2.close();
            }
            is.close();
            is1.close();

        } catch (Exception e) {
            //TODO: handle exception
        }
        return true;
    }
    public  void send(String txt,String action) throws Exception{
        try {                                
            OutputStream output = this.getClient().getOutputStream();                                 
            Object msg = action;                                                            
            ObjectOutputStream objoutput = new ObjectOutputStream(output);                  
            objoutput.writeObject(msg);   
            
            OutputStream output1 = this.getClient().getOutputStream();                                 
            Object msg1 = txt;                                                            
            ObjectOutputStream objoutput1 = new ObjectOutputStream(output1);                  
            objoutput.writeObject(msg1); 

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public void get(String file,String obj1) throws Exception{
        try {
            
            String txt = String.valueOf(obj1);

            Functions fct = new Functions();
            
            String home = System.getProperty("user.home");
            String directory = home+"/Downloads/"+file;
            File fichier = new File(directory);
            fichier.createNewFile();
            
            String[] vect = txt.split("===");
            String all = vect[0];
            for(int i=1 ; i<vect.length ; i++){
                all = all+vect[i];
            }
            String[] vect1 = all.split("&&&");
            for (int j = 0; j < vect1.length; j++) {
                String[] vect2 = vect1[j].split("//");
                fct.writeLine(directory, vect2[0]);
                for (int k = 1; k < vect2.length; k++) {
                    fct.writeLine(directory, vect2[k]);
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public String[] lister(String liste)
    {
        String[] rep = liste.split("===");
        return rep;
    }

}
