package RetiDiCalcolatori.ProvaScritta24_09_2016.Parte2.ThreadServer;

import ProvaScritta24_09_2016.Parte2.Gestore;
import ProvaScritta24_09_2016.Parte2.RichiestaRisorsa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HandleConnessione extends Thread{

    private final static int tPort=2000;
    private ServerSocket server;
    private final Gestore gestore;

    public HandleConnessione(Gestore gestore){
        this.gestore=gestore;
    }



    public void run(){
        try{
            server=new ServerSocket(tPort);
            while(isActive()){
                Socket client=server.accept();
                new HandleClient(client,gestore).start();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean isActive(){
        return gestore.gestisciCliente();
    }


}
class HandleClient extends Thread{
    private final Socket client;
    private final Gestore gestore;

    public HandleClient(Socket client,Gestore gestore){
        this.gestore=gestore;
        this.client=client;
    }

    public void run(){
        try{
            ObjectInputStream ios=new ObjectInputStream(client.getInputStream());
            RichiestaRisorsa richiesta=(RichiestaRisorsa) ios.readObject();

            String addressElaboratore=gestore.getOfferta(richiesta);
            PrintWriter pw=new PrintWriter(client.getOutputStream(),true);
            pw.write(addressElaboratore);
            client.close();

        }catch (IOException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
