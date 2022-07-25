package RetiDiCalcolatori.ProvaScritta15_01_2020.Parte2.LatoClient;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ThreadClient extends Thread{
    private final static String hostname="gestore.dimes.unical.it";
    private final static int tPort=1111;
    private final String richiesta;
    private Socket server;

    public ThreadClient(String richiesta){
        this.richiesta=richiesta;
    }


    public void run(){
        try{
            server=new Socket(hostname,tPort);
            PrintWriter pw=new PrintWriter(server.getOutputStream(),true);
            pw.write(richiesta);
            //invio richiesta e aspetto ricevimento offerte;
            ObjectInputStream ois=new ObjectInputStream(server.getInputStream());
            List<String> offerte=(List<String>) ois.readObject();
            ois.close();

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }



}
