package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ThreadHandlerCentralinaRegistrata extends Thread{

    private DatagramSocket socket;
    private final static int uPort=6000;
    Server gestore;

    public ThreadHandlerCentralinaRegistrata(Server gestore){
        this.gestore=gestore;
    }


    public void run(){
        try {
            socket=new DatagramSocket(uPort);
            while(true){
                byte[] buf=new byte[256];
                DatagramPacket packet=new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String msg=new String(packet.getData());
                String[] split=msg.split("#");

                int idCentralina=Integer.parseInt(split[0]);
                String grandezza=split[1];
                double misura=Double.parseDouble(split[2]);
                Dato d=new Dato(grandezza,misura,System.currentTimeMillis());

                gestore.addDato(idCentralina,d);

                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }





}
