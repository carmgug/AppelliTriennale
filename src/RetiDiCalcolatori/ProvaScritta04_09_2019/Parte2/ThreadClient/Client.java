package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte2.ThreadClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Client extends Thread{

    private final static String hostname="clinica.unical.it";
    private final static int tPort=3000;
    private final static int tPort2=4000;
    private final String codEsame;
    private Random random=new Random();
    private Socket socket;

    public Client(String codEsame) {
        this.codEsame = codEsame;
    }


    public void run(){
        try {
            socket=new Socket(hostname,tPort);
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            pw.write(codEsame);

            //Gestisci max tempo 1h
            new TimeOutHandler(this).start();
            BufferedReader bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String prenotazione=bf.readLine();
            bf.close();

            TimeUnit.HOURS.sleep(3);

            int scelta=random.nextInt(0,10);
            if(scelta<3) {
                cancellaPrenotazione(prenotazione);
            }

        } catch (IOException|InterruptedException e) {
            System.out.println("Mi sono disconnesso");
        }
    }

    private void cancellaPrenotazione(String prenotazione){
        try {
            String[] split=prenotazione.split("#");
            socket=new Socket(hostname,tPort2);
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            pw.write(split[0]+"#"+split[1]);
            pw.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Mi sono disconnesso");
        }
    }





}
