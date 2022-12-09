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
    int i;

    ServerSocket server;

    Server1 svr1;
    Server1 svr2;
    Server1 svr3;


    public void setI(int i) {
        this.i = i;
    }
    public int getI() {
        return i;
    }
    public void setSvr1(Server1 svr1) {
        this.svr1 = svr1;
        this.svr1.setPortP(this.getPort());
        this.svr1.setPrincip("localhost");
    }
    public void setSvr2(Server1 svr2) {
        this.svr2 = svr2;
        this.svr2.setPortP(this.getPort());
        this.svr2.setPrincip("localhost");
    }
    public void setSvr3(Server1 svr3) {
        this.svr3 = svr3;
        this.svr3.setPortP(this.getPort());
        this.svr3.setPrincip("localhost");
    }
    public Server1 getSvr1() {
        return svr1;
    }
    public Server1 getSvr2() {
        return svr2;
    }
    public Server1 getSvr3() {
        return svr3;
    }
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

    public String get() throws Exception{
        String rep = "";
        try {
                Socket client = this.getServer().accept();           
                InputStream is = client.getInputStream(); 
                ObjectInputStream msg = new ObjectInputStream(is);  
                Object obj1  = msg.readObject();
                rep = String.valueOf(obj1);
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        return rep;
    }

    public String sendClient() throws Exception{
        String rep = "";
        try {
                String from1 = this.get();
                System.out.println(from1);
                String from2 = this.get();
                String from3 = this.get();
                rep = from1+"==="+from2+"==="+from3;

        } catch (Exception e) {
            //TODO: handle exception
        }
        return rep;
    }

    public void getSend(String txt,String texte) throws Exception{     /// distribue en 3 parties et les renvoient au 3 server 
        try {
            Functions fct = new Functions(); 

            Vector vect = fct.readerLine(txt);

            Socket clientSvr = new Socket("localhost", this.getClport());
            OutputStream os = clientSvr.getOutputStream();                          
            ObjectOutputStream message = new ObjectOutputStream(os);
            Object obj  = String.valueOf(vect.elementAt(0));
            for (int i = 1; i < (int) vect.size()/3; i++) {
                 obj  = obj +"//"+String.valueOf(vect.elementAt(i)); 
            }
            message.writeObject(obj+"=>"+texte);

            Socket clientSvr1 = new Socket("localhost", this.getClport1());
            OutputStream os1 = clientSvr1.getOutputStream();                         
            ObjectOutputStream message1 = new ObjectOutputStream(os1);
            Object obj2  = String.valueOf(vect.elementAt((int) vect.size()/3));
            for (int i = ((int) vect.size()/3)+1 ; i < (int) vect.size()*2/3; i++) {
                obj2 = obj2 +"//" +String.valueOf(vect.elementAt(i));
            }
            message1.writeObject(obj2+"=>"+texte);  

            Socket clientSvr2 = new Socket("localhost", this.getClport2());
            OutputStream os2 = clientSvr2.getOutputStream();                         
            ObjectOutputStream message2 = new ObjectOutputStream(os2);
            Object obj3  = String.valueOf(vect.elementAt((int) vect.size()*2/3));
            for (int i = ((int) vect.size()*2/3)+1; i <  vect.size(); i++) {
                obj3 = obj3 +"//"+String.valueOf(vect.elementAt(i));
            }
            message2.writeObject(obj3+"=>"+texte);   

            os.close();
            os1.close();
            os2.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    public void sendSearch(String txt) throws Exception{       
        Socket clientSvr = new Socket("localhost", this.getClport());
        OutputStream os = clientSvr.getOutputStream();                          
        ObjectOutputStream message = new ObjectOutputStream(os);
        message.writeObject(txt);

        Socket clientSvr1 = new Socket("localhost", this.getClport1());
        OutputStream os1 = clientSvr1.getOutputStream();                         
        ObjectOutputStream message1 = new ObjectOutputStream(os1);
        message1.writeObject(txt);

        Socket clientSvr2 = new Socket("localhost", this.getClport2());
        OutputStream os2 = clientSvr2.getOutputStream();                         
        ObjectOutputStream message2 = new ObjectOutputStream(os2);
        message2.writeObject(txt);
        
        this.getSvr1().searchinFile();
        this.getSvr2().searchinFile();
        this.getSvr3().searchinFile();

        os.close();
        os1.close();
        os2.close();
    }
    public boolean verify() throws Exception{                      ///verifie l action que le client veut effectue et agit selon cette action 
        try {
            Socket client = this.getServer().accept();           
            InputStream is = client.getInputStream(); 
            ObjectInputStream msg = new ObjectInputStream(is);  
            Object obj1  = msg.readObject();
            String action = String.valueOf(obj1);

            InputStream is1 = client.getInputStream(); 
            ObjectInputStream msg1 = new ObjectInputStream(is1);  
            Object obj  = msg1.readObject();
            String txt = String.valueOf(obj);

            Functions fct = new Functions();
            if (action.equalsIgnoreCase("upload")) {
               int rand = (int)(Math.random()*100);
               fct.writeLine("Texte.txt","Texte"+rand+".txt =>"+txt);
               this.getSend(txt,"Texte"+rand+".txt");
               this.getSvr1().get();
               this.getSvr2().get();
               this.getSvr3().get();

               String rep = lister();
               OutputStream os = client.getOutputStream();
               ObjectOutputStream message = new ObjectOutputStream(os);
               message.writeObject("update");

               OutputStream os1 = client.getOutputStream();
               ObjectOutputStream message1 = new ObjectOutputStream(os1);
               message1.writeObject(rep);

               os.close();
               os1.close();

            }
            if (String.valueOf(obj1).equalsIgnoreCase("download")) {

                this.sendSearch(txt);

                OutputStream os1 = client.getOutputStream();
                ObjectOutputStream message1 = new ObjectOutputStream(os1);
                message1.writeObject("download");

                OutputStream os = client.getOutputStream();
                ObjectOutputStream message = new ObjectOutputStream(os);
                message.writeObject(sendClient());

                OutputStream os2 = client.getOutputStream();
                ObjectOutputStream message2 = new ObjectOutputStream(os2);
                message2.writeObject(txt);
                
                os1.close();
                os.close();
                os2.close();

            }
             is.close();
             is1.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        return true;
    }
    public String lister() throws Exception{
        Functions fct =  new Functions();
        Vector vect = fct.readerLine("Texte.txt");
        String rep1 = "";
        for (int i = 0; i < vect.size(); i++) {
            String[] rep = String.valueOf(vect.elementAt(i)).split("=>");
            rep1 = rep[0];
            for (int j = 1; j < rep.length; j++) {
                rep1 = "===" + rep[j];
            }
            }
            return rep1;
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