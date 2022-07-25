package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte.ThreadClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadSensori extends Thread{

    private final static String hostname="localhost";
    private final static int uPort=4000;
    private DatagramSocket socket;
    private final int id_Sensore;
    private Random random=new Random();

    public ThreadSensori(int id_Sensore){
        this.id_Sensore=id_Sensore;

    }

    public void run(){
        try { socket=new DatagramSocket();
        InetAddress address= InetAddress.getByName(hostname);
        byte[] buf=new byte[256];
        while(true){
            TimeUnit.MINUTES.sleep(5);
            buf=GeneraMisura().getBytes();
            DatagramPacket packet=new DatagramPacket(buf,buf.length,address,uPort);
            socket.send(packet);
        }
        }catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    private String GeneraMisura(){
        double misura=random.nextDouble(0,250); //valori randomici
        long timestamp=System.currentTimeMillis();

        return id_Sensore+"#"+misura+"#"+timestamp;

    }




}
