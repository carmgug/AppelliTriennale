package RetiDiCalcolatori.ProvaScritta18_03_2021.Parte2.LatoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private final static String hostname="shop.dimes.it";
    private final static int tPort=1111;
    private  String id_prodotto;
    private  int quantita;
    private  double PrezzoMassimo;
    private Socket client;

    public void run(){
        try{
            String msg=id_prodotto+","+quantita+","+PrezzoMassimo;
            client=new Socket(hostname,tPort);

            PrintWriter pw=new PrintWriter(client.getOutputStream(),true);
            pw.write(msg);
            pw.close();

            BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()));
            String ris=bf.readLine();
            bf.close();



        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
