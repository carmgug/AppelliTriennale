package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte2.ThreadServer;

import ProvaScritta04_09_2019.Parte2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HandAnnullaPrenotazione extends Thread{

    private Server server;
    private ServerSocket socket;
    private final static int tPort=4000;



    public void run(){

            try{
                socket=new ServerSocket(tPort);
                while (true){
                    Socket paziente=socket.accept();
                    BufferedReader bf=new BufferedReader(new InputStreamReader(paziente.getInputStream()));
                    String msg= bf.readLine();

                    String[] split=msg.split("#");
                    int codPrenotazione=Integer.parseInt(split[1]);

                    server.removePrenotazione(split[0],codPrenotazione);
                    paziente.close();

                }

            }catch (IOException e){
                e.printStackTrace();
            }

    }




}
