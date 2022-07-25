package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte2;

import ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete.Biglietto;
import ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete.Richiesta;

import javax.naming.ldap.SortKey;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

public class Client {

    private String name;
    private List<Biglietto> Acquisti;
    private final static String bAddress="230.0.0.1";
    private final static int  bPort=4000;
    private final static int tPort=3000;


    public Client(String name){
        Acquisti=new LinkedList<>();
    }

    private void acquistaBiglietti(String nomeLotteria, int numeroBiglietti) throws IOException, InterruptedException, ClassNotFoundException {
        Richiesta richiesta=new Richiesta(nomeLotteria,numeroBiglietti);
        System.out.println("Sto acquistando i biglietti...");
        Socket tSocket= new Socket("localhost", tPort);
        ObjectOutputStream oos=new ObjectOutputStream(tSocket.getOutputStream());

        oos.writeObject(richiesta);
        oos.flush();oos.close();

        Thread.sleep(3000);

        ObjectInputStream ois=new ObjectInputStream((tSocket.getInputStream()));

        Acquisti=(List<Biglietto>) ois.readObject();

        ois.close(); tSocket.close();
    }

    private boolean AspettaVittoria() throws IOException {
        MulticastSocket mSocket= new MulticastSocket(3000);
        InetAddress group=InetAddress.getByName("230.0.0.1");
        mSocket.joinGroup(group);
        byte[] buf=new byte[512];
        DatagramPacket packet=new DatagramPacket(buf,buf.length);
        mSocket.receive(packet);

        String infoBiglietto= new String(packet.getData());
        String[] infoBigliettoparts= infoBiglietto.split("-");
        System.out.println("Ricevuto pacchetto multicast: "+ infoBiglietto);
        if(ValutaVittoria(infoBigliettoparts[1],Integer.parseInt(infoBigliettoparts[2])))
            return true;
        else
            return false;
    }

    private boolean ValutaVittoria(String nomeLotteria, int bigliettoVincente){
        for(Biglietto b:Acquisti){
            if(b.getNumero()==bigliettoVincente && b.getNomeLotteria()==nomeLotteria) return true;
        }
        return false;
    }





}
