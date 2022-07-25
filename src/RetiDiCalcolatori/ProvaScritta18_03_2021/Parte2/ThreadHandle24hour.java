package RetiDiCalcolatori.ProvaScritta18_03_2021.Parte2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class ThreadHandle24hour extends Thread{

    private Registro registro;
    private String id_Negozio;
    private String hostname;
    private final static int uPort=3333;

    public ThreadHandle24hour(String idNegozio, String hostname) {

    }

    public void run() {
        while (true){
            try {
                TimeUnit.HOURS.sleep(24);
                Double Eurovendite=registro.getVendite(id_Negozio);


                DatagramSocket socket= new DatagramSocket();
                byte[] buf=new byte[256];
                InetAddress address=InetAddress.getByName(hostname);

                buf=((""+Eurovendite)).getBytes();

                DatagramPacket packet=new DatagramPacket(buf,buf.length,address,uPort);
                socket.send(packet);


            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
