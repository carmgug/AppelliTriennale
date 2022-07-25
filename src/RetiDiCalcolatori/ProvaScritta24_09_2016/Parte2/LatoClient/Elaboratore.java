package RetiDiCalcolatori.ProvaScritta24_09_2016.Parte2.LatoClient;

import ProvaScritta24_09_2016.Parte2.OffertaRisorsa;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Elaboratore extends Thread{


    private final static int tPort=3000;

    private final static String hostnameGestore="gestore.unical.it";
    private Socket socket;
    private OffertaRisorsa offerta;



    public void run(){
        try{
            socket=new Socket(hostnameGestore,tPort);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(offerta);
            oos.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
