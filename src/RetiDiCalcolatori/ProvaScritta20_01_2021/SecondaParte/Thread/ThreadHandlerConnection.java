package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte.Thread;

import ProvaScritta20_01_2021.SecondaParte.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadHandlerConnection extends Thread{

    private final Server server;
    private ServerSocket socket;
    private final int tPort;

    public ThreadHandlerConnection(Server server,int tPort){
        this.server=server;
        this.tPort=tPort;
    }

    public void run(){
        try{
            socket=new ServerSocket(tPort);
            while (true){
                Socket client=socket.accept();
                new ThreadHandleRichiesta(client,server);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
