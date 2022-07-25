package RetiDiCalcolatori.ProvaScritta24_09_2016.Parte2.LatoClient;

import ProvaScritta24_09_2016.Parte2.RichiestaRisorsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread{

    private Socket socket;
    private RichiestaRisorsa richiesta;
    private final static int tPort=2000;
    private final static String hostnameGestore="gestore.unical.it";



    public void run(){
        try{
            socket=new Socket(hostnameGestore,tPort);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(richiesta);
            //inviata richiesta aspetto rispsta;
            BufferedReader bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String hostnameElaboratore=bf.readLine();
            oos.close();
            bf.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
