package RetiDiCalcolatori.ProvaScritta24_09_2016.Parte2.ThreadServer;

import ProvaScritta24_09_2016.Parte2.Gestore;
import ProvaScritta24_09_2016.Parte2.OffertaRisorsa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HandleElaboratore extends Thread{


    private final static int tPort=3000;
    private ServerSocket server;
    private final Gestore gestore;

    public HandleElaboratore(Gestore gestore){
        this.gestore=gestore;
    }

    public void run(){
        try{
            server=new ServerSocket(tPort);
            while(isActive()){
                Socket elaboratore=server.accept();
                ObjectInputStream ois=new ObjectInputStream(elaboratore.getInputStream());
                //ricevuto l'oggetto lo inserisci
                OffertaRisorsa offerta=(OffertaRisorsa) ois.readObject();
                String ip_e=elaboratore.getInetAddress().getHostAddress();
                gestore.addOffertaRisorsa(offerta,ip_e);
                //ho finito
                ois.close();
                elaboratore.close();

            }

        }catch (IOException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private boolean isActive(){
        return  gestore.GestisciElaboratore();
    }


}
