package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte2.ThreadServer;

import ProvaScritta04_09_2019.Parte2.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class HandlerQueue extends Thread{


    private final Server server;
    private final String codEsame;

    public HandlerQueue(Server server,String codEsame){
        this.server=server;
        this.codEsame=codEsame;
    }

    public void run(){
        try {
        while(true){
            TimeUnit.SECONDS.sleep(30);//Ogni 30 secondi controlla se ci sono posti liberi.
            Object[] info=server.addPazienteQueue(codEsame);
            String msg=(String)info[0];
            if(!msg.equals("non effettuata")){
                Socket paziente=(Socket) info[1];
                PrintWriter pw= null;
                pw = new PrintWriter(paziente.getOutputStream(),true);

                pw.write(msg);
                pw.close();
            }
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }





}
