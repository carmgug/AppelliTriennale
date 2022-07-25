package RetiDiCalcolatori.ProvaScritta18_03_2021.Parte2.LatoClient;

import ProvaScritta18_03_2021.Parte2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Random;

public class Negozio extends Thread{


    private final static String hostname="shop.dimes.it";
    private final static int tPort=2222;
    private final static int uPort=3333;
    private final Random random=new Random();

    private ServerSocket server;


    public Negozio(){
        avviaRicezioneProdottiVenduti();
    }

    public void run(){
        try {
            server = new ServerSocket(tPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            try {
                Socket curr=server.accept();
                BufferedReader bf=new BufferedReader(new InputStreamReader(curr.getInputStream()));
                String msg=bf.readLine();
                bf.close();

                String[] split=msg.split(",");
                double prezzoMassimo=Double.parseDouble(split[2]);
                double prezzo=random.nextDouble(0,prezzoMassimo+200);

                PrintWriter pw=new PrintWriter(curr.getOutputStream(),true);
                pw.write(""+prezzo);
                pw.close(); server.close();



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void avviaRicezioneProdottiVenduti(){
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    DatagramSocket socket = new DatagramSocket(uPort);
                    byte[] buf=new byte[256];

                    while(true) {
                        DatagramPacket packet=new DatagramPacket(buf, buf.length);
                        socket.receive(packet);
                        String msg=new String(packet.getData());
                        Double prezzo=Double.parseDouble(msg);
                        System.out.println("Ho venduto"+prezzo);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        t1.start();
    }



}
