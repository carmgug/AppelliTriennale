package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte.ThreadClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client2 extends Thread{


    //NON SO SE QUESTA CLASSE ANDAVA EFFETTIVAMENTE REALIZZATA
    MulticastSocket socket;
    private int bPort;
    private String bAddress;

    public void run(){
        try {
            socket = new MulticastSocket(bPort);
            InetAddress address = InetAddress.getByName(bAddress);
            socket.joinGroup(address);
            DatagramPacket packet;

            byte[] buf=new byte[256];
            packet=new DatagramPacket(buf,buf.length);
            socket.receive(packet);

            String msg=new String(packet.getData());


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
