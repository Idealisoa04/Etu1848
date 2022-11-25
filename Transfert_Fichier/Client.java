package server;
import java.io.*;
import java.net.*;
import function.*;
public class Client {
    int port;
    String id;
    Socket client;

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
    public  void send(String txt) throws Exception{
        try {                                
                                 
            OutputStream output = this.getClient().getOutputStream();                                 
            Object msg = txt ;                                                          
            ObjectOutputStream objoutput = new ObjectOutputStream(output);                  
            objoutput.writeObject(msg);                                                      

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public void get(String txt) throws Exception{
        try {

            OutputStream output = this.getClient().getOutputStream();                                 
            Object msg = txt ;                                                          
            ObjectOutputStream objoutput = new ObjectOutputStream(output);                  
            objoutput.writeObject(msg); 

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
