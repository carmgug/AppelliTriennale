package RetiDiCalcolatori.ProvaScritta15_01_2020.Parte2.ThreadServer;

import ProvaScritta15_01_2020.Parte2.Gestore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HandlerConnessioni extends Thread{

    private final Gestore gestore;
    private final static int tPort=1111;
    private ServerSocket server;

    public HandlerConnessioni(Gestore gestore) {
        this.gestore = gestore;
    }


    public void run(){
        try{
            server=new ServerSocket(tPort);
            while(true){
                Socket client=server.accept();
                Thread t1=new HandlerRichiesta(gestore,client);
                t1.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
