package RetiDiCalcolatori.ProvaScritta15_01_2020.Parte2.ThreadServer;

import ProvaScritta15_01_2020.Parte2.Gestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HandlerRichiesta extends Thread{

    private final Socket client;
    private final Gestore gestore;
    private MulticastSocket msocket;
    private final static String address="224.3.2.1";
    private final static int gPort=3333;
    private int id_Richiesta;

    public HandlerRichiesta(Gestore gestore, Socket client) {
        this.gestore=gestore;
        this.client=client;
    }


    public void run(){
        try{
            BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()));
            String msg=bf.readLine();
            bf.close();
            //aggiunta la richiesta
            id_Richiesta=gestore.addRichiesta(msg);
            //la mando in broadcast
            byte[] buf=new byte[256];
            buf=(""+id_Richiesta).getBytes();
            msocket=new MulticastSocket(gPort);
            InetAddress group= InetAddress.getByName(address);
            DatagramPacket p=new DatagramPacket(buf,buf.length,group,gPort);
            msocket.send(p);


            //Aspetto per un minuto l'arrivo di offerte
            TimeUnit.MINUTES.sleep(1);
            //Finito non ricevo più niente
            gestore.terminaRichiesta(id_Richiesta);
            //Prelevo le offerte e le invio
            List<String> offerte= gestore.getOfferte(id_Richiesta);

            ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(offerte);
            oos.close();
            client.close();





        }catch (IOException|InterruptedException e){
            e.printStackTrace();
        }
    }



}
