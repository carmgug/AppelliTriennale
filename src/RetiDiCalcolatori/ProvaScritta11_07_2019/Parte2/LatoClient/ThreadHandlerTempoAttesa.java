package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte2.LatoClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;

public class ThreadHandlerTempoAttesa extends Thread{

    private final int id_Nave;
    private final static int uPort=5000;
    private DatagramSocket socket;


    public ThreadHandlerTempoAttesa(int id_Nave){
        this.id_Nave=id_Nave;
    }

    public void run(){
        try{
            socket=new DatagramSocket(uPort);
            byte[] buf=new byte[256];
            DatagramPacket packet;
            packet=new DatagramPacket(buf, buf.length);

            while (true){
                TimeUnit.MINUTES.sleep(5);
                socket.receive(packet);
                String msg=new String(packet.getData()); //tempo medio ricevuto
            }



        }catch (IOException|InterruptedException e){
            e.printStackTrace();
        }



    }



}
