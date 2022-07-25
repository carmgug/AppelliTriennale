package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadHandleConnection extends Thread{


    private final Porto porto;
    private final static int tPort=3000;
    private ServerSocket server;

    public ThreadHandleConnection(Porto porto){
        this.porto=porto;
    }


    public void run(){
        try{
            server=new ServerSocket(tPort);
            while(true){
                Socket nave=server.accept();
                new ThreadHandlerRichiesta(porto,nave);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }





}
