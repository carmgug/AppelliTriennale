package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte.Thread;

import ProvaScritta20_01_2021.SecondaParte.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.TimeUnit;

public class ThreadSenderInfo extends Thread{


    private final Server server;
    private final int id_Sensore;

    private final static int gPort=5000;
    private final static String gAddress="230.0.0.1";
    private MulticastSocket msocket;

    public ThreadSenderInfo(Server server,int id_Sensore){
        this.server=server;
        this.id_Sensore=id_Sensore;

    }


    public void run(){
        try{
            msocket=new MulticastSocket(gPort);
            byte[] buf=new byte[256];

            InetAddress address=InetAddress.getByName(gAddress);

            buf=(""+id_Sensore).getBytes();
            DatagramPacket packet=new DatagramPacket(buf,buf.length,address,gPort);
            while (true){
                TimeUnit.MINUTES.sleep(10);
                if(!Funziona()){
                    msocket.send(packet);

                }

            }




        }catch (IOException|InterruptedException e){
            e.printStackTrace();
        }
    }

    private boolean Funziona(){
        return server.getStato(id_Sensore);
    }

}
