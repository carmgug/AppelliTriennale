package RetiDiCalcolatori.ProvaScritta2020gennaio.SecondaParte.Gestore;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static java.lang.Thread.sleep;

public class ThreadHandlerRichiesta {
    private final static int tPort=1111;
    private Registro registro;
    private ServerSocket socketClient;

    public ThreadHandlerRichiesta(Registro registro){
        this.registro=registro;
    }





    public void run(){
        try {
            socketClient = new ServerSocket(tPort);
            while(true){
                Socket Client=socketClient.accept();
                BufferedReader br=new BufferedReader(new InputStreamReader(Client.getInputStream()));

                //Ricevo la richiesta
                String richiesta=br.readLine();
                System.out.println("Ho ricevuto la richiesta: "+richiesta);
                br.close();



                int idRichiesta=registro.addRichiesta(Client,richiesta);
                new ThreadTimeoutHandler(idRichiesta,registro);
                //Ho finito il mio lavoro

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
