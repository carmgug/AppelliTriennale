package RetiDiCalcolatori.ProvaScritta11_11_2020.parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class Client extends Thread{

    private final static String bAddress="230.0.0.1";
    private final static int uPort=4000;
    private final static int tPort=1111;
    private final static String hostname="analisi.dimes.it";
    private MulticastSocket mSocket;
    private Socket socket;

    private final String nomeAnalisi;
    private final double param1;
    private final double param2;

    public Client(String s,double p1,double p2){
        nomeAnalisi=s;
        param2=p2;
        param1=p1;

    }

    public void run(){


        try {
            byte[] buf=new byte[256];
            mSocket=new MulticastSocket(4000);
            InetAddress address=InetAddress.getByName(bAddress);
            mSocket.joinGroup(address);
            DatagramPacket packet=new DatagramPacket(buf, buf.length);
            mSocket.receive(packet);

            String msg=new String(packet.getData());
            System.out.println("Ho ricevuto il valore medio: "+ msg);

            msg=nomeAnalisi+"#"+param1+"#"+param2;

            Socket socket=new Socket(hostname,tPort);
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            pw.write(msg);

            pw.close();

            BufferedReader bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            msg=bf.readLine();

            System.out.println("Ho ricevuto risposta: "+msg);





        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
