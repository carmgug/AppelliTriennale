package RetiDiCalcolatori.ProvaScritta2020gennaio.SecondaParte.Gestore;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.List;

public class ThreadTimeoutHandler extends Thread{

   private static final String gAddress="224.3.2.1";
   private static final int gPort=2222;

   private final int idRichiesta;
   private final Registro registro;
   private final MulticastSocket mSocket;

   public ThreadTimeoutHandler(int idRichiesta,Registro registro) throws IOException {
       this.idRichiesta=idRichiesta;
       mSocket=new MulticastSocket(gPort);
       this.registro=registro;
   }




    public void run(){
       try{
           //Invio la richiesta in multicast
           inviaRichiesta(registro.getRichiesta(idRichiesta));

           //Aspetto le offerte in arrivo
           new ThreadOffertaHandler(idRichiesta,registro);
           sleep(60000);


           //Devo inviare le offerte ricevute
           inviaOfferte();

       }catch(IOException | InterruptedException  e){
           e.printStackTrace();
       }

    }

    private void inviaRichiesta(String richiesta) throws IOException {
       byte buf[] = richiesta.getBytes();
       InetAddress group= InetAddress.getByName(gAddress);
       DatagramPacket packet=new DatagramPacket(buf, buf.length,group,gPort);
       mSocket.send(packet);
       System.out.println("Inviata richiesta in multicast");


    }
    private void inviaOfferte() throws IOException {

       //Chiudo la richiesta e prendo il socket
        Socket Client=registro.chiudiRichiesta(idRichiesta);
        //Invio lista contenente Richieste
        List<String> offerte=registro.getOfferteRicevute(idRichiesta);
        ObjectOutputStream oos=new ObjectOutputStream(Client.getOutputStream());
        oos.writeObject(offerte);
        System.out.println("Inviata lista offerte di richiesta numero"+idRichiesta);
        Client.close();
   }


}
