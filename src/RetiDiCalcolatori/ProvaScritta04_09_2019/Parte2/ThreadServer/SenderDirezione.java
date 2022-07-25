package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte2.ThreadServer;

import ProvaScritta04_09_2019.Parte2.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SenderDirezione extends Thread{


    private final static String hostname="clinica.unical.it";
    private final static int uPort=5000;
    private final static LocalTime end=LocalTime.of(12,00);
    private DatagramSocket socket;
    private Server server;




    public void run(){

        try {
            byte[] buf = new byte[256];
            socket = new DatagramSocket();
            InetAddress address=InetAddress.getByName(hostname);
            DatagramPacket p;


            while (true) {
                if (isActive()) {
                    List<String> msg=server.getStatistica();
                    for(String x: msg){
                        buf=x.getBytes();
                        p=new DatagramPacket(buf, buf.length,address,uPort);
                        socket.send(p);
                    }
                }
                TimeUnit.HOURS.sleep(24); //Dovrai rimandare sicuramente tra 24 ore;
            }
        }catch (IOException|InterruptedException e){
            e.printStackTrace();
        }//catch

    }


    private boolean isActive() throws InterruptedException {
        LocalTime now=LocalTime.now();
        if(now.isAfter(end)){
            return true;
        }
        //TimeUnit.HOURS.sleep(end.getHour()- now.getHour());
        TimeUnit.HOURS.sleep(1); //controlla tra un ora se sei fuori servizio
        return false;
    }



}
