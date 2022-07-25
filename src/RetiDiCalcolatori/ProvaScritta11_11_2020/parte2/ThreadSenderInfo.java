package RetiDiCalcolatori.ProvaScritta11_11_2020.parte2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ThreadSenderInfo extends Thread{

    public final static int bPort=4000;
    public final static long time=10000;
    public final static String bAddress="230.0.0.1";
    public MulticastSocket mSocket;
    public Registro registro;



    public void run(){
        try {
            //Strutture dati utili
            byte[] buf=new byte[256];

            mSocket =new MulticastSocket(bPort);
            InetAddress address=InetAddress.getByName(bAddress);

            while(true){
                sleep(time);
                Double val_med_x=registro.valoreMedio_X();
                buf=val_med_x.toString().getBytes();
                DatagramPacket packet=new DatagramPacket(buf,buf.length,address,bPort);
                mSocket.send(packet);

            }

        }catch (IOException|InterruptedException e){
            e.printStackTrace();
        }
    }

}
