package RetiDiCalcolatori.ProvaScritta15_01_2020.Parte2.LatoClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Random;

public class CentroBenessere extends Thread{
    private final static String gAddress="224.3.2.1";
    private final static int gPort=2222;
    private MulticastSocket msocket;

    private final String hostname;

    public CentroBenessere(String hostname){
        this.hostname=hostname;
    }


    public void run(){

            try {
                msocket=new MulticastSocket();
                InetAddress group=InetAddress.getByName(gAddress);
                msocket.joinGroup(group);
                byte[] buf=new byte[256];
                DatagramPacket p;

                while(true){
                    p=new DatagramPacket(buf,buf.length,group,gPort);
                    msocket.receive(p);

                    String richiesta=new String(p.getData());
                    new ThreadSendOfferta(richiesta,hostname).start();
                }

            }catch (IOException e){
                e.printStackTrace();
            }

    }


    class ThreadSendOfferta extends Thread{
        private final static String hostname="gestore.dimes.unical.it";
        private final String hostnameCentroBenessere;
        private final static int tPort=3333;
        private final String richiesta;
        private Socket server;
        private Random random=new Random();
        public ThreadSendOfferta(String richiesta,String hst){
            this.richiesta=richiesta;
            this.hostnameCentroBenessere=hst;
        }

        public void run(){
            try {//genera offerta o no
                double r = random.nextDouble(0, 1);
                if (r > 0.3) {//invia offerta
                    String offerta = generaOfferta(richiesta);

                    server = new Socket(hostname, tPort);
                    PrintWriter pw = new PrintWriter(server.getOutputStream(), true);
                    pw.write(richiesta + "#" + offerta);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        private String generaOfferta(String richiesta){
            String[] split=richiesta.split(",");
            int numeroPersone=Integer.parseInt(split[1]);
            double prezzo=random.nextDouble(50,150);
            return ""+hostnameCentroBenessere+","+prezzo*numeroPersone;
        }

    }


}
