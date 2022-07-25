package RetiDiCalcolatori.ProvaScritta2020gennaio.SecondaParte;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Random;

public class CentroBenessere extends Thread{

    private String hostnameCentroBenessere;
    private final static String gAddress="224.3.2.1";
    private final static int gPort=2222;
    private final static Random rand=new Random();
    private final static double val=0.3;
    private static String GESTORE_ADDRESS;
    private static int GESTORE_PORT;
    private MulticastSocket mSocket;

    public void run(){
        try {
            mSocket = new MulticastSocket(gPort);
            InetAddress group= InetAddress.getByName(gAddress);
            mSocket.joinGroup(group);
            while (true) {
                //Ricevi il la richiesta
                byte[] buf= new byte[512];
                DatagramPacket packet=new DatagramPacket(buf,buf.length);
                mSocket.receive(packet);
                //Leggo la richiesta
                String richiesta=new String(packet.getData());
                double number_1=rand.nextDouble(1);
                if(number_1>val){//Invia l'offerta
                    int prezzo=rand.nextInt(50,151);
                    String offerta="<"+hostnameCentroBenessere+">"+
                            "<"+prezzo+">";
                    inviaOfferta(offerta);
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void inviaOfferta(String offerta) throws IOException {
        Socket socket=new Socket(GESTORE_ADDRESS, GESTORE_PORT);
        PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
        pw.println(offerta);

        pw.close(); socket.close();
    }

}
