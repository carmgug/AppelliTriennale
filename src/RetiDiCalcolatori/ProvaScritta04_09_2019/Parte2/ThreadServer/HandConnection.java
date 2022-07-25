package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte2.ThreadServer;

import ProvaScritta04_09_2019.Parte2.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HandConnection extends Thread{

    private final Server server;
    private ServerSocket socket;
    private final static int tPort=3000;


    public HandConnection(Server server){
        this.server=server;
    }

    public void run(){
        try{
            socket=new ServerSocket(tPort);
            while (true){
                Socket client=socket.accept();
                new HandPrenotazione(server,client);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
