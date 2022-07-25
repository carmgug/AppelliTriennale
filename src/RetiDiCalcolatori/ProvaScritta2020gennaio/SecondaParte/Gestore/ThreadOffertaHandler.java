package RetiDiCalcolatori.ProvaScritta2020gennaio.SecondaParte.Gestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadOffertaHandler extends Thread{


    private final int idRichiesta;
    private final Registro registro;
    private final static int tPort=3333;
    private ServerSocket socketCB;

    public ThreadOffertaHandler(int idRichiesta,Registro registro){
        this.idRichiesta=idRichiesta;
        this.registro=registro;
    }



    public void run(){
        try {
            socketCB=new ServerSocket(tPort);
            while(true){
                Socket cb=socketCB.accept();
                BufferedReader bf=new BufferedReader(new InputStreamReader(cb.getInputStream()));

                String offerta=bf.readLine();
                bf.close();

                boolean AncoraAperta=registro.addOfferta(idRichiesta,offerta);
                if(!AncoraAperta) {
                    cb.close();
                    socketCB.close();
                    break; //esci dal while true e non ascoltare più offerte.
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }





}
