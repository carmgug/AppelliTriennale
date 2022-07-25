package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte.ClientVari;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class ThreadGestoreCentralina extends Thread{

    private final static String uAddress="localhost";
    private final long time;
    private final static String[] grandezza= {"temperatura","umidita","pressione atmosferica"};
    private final int uPort;
    private final int idCentralina;

    private final Random random=new Random();

    public ThreadGestoreCentralina(int idCentralina,long time,int uPort){
        this.idCentralina=idCentralina;
        this.time=time;
        this.uPort=uPort;

    }

    public void run(){
        try{
            DatagramSocket socket=new DatagramSocket();
            byte[] buf=new byte[256];
            while(true){
                sleep(time);
                buf=produciMisura().getBytes();
                InetAddress address=InetAddress.getByName(uAddress);
                DatagramPacket packet=new DatagramPacket(buf,buf.length,address,uPort);
                socket.send(packet);
            }

        }catch (IOException |InterruptedException e){
            e.printStackTrace();
        }
    }

    private String produciMisura(){
        int g= random.nextInt(0,grandezza.length);
        int valore= random.nextInt(0,100);

        String msg=idCentralina+"#"+g+"#"+valore;
        return msg;

    }


}
