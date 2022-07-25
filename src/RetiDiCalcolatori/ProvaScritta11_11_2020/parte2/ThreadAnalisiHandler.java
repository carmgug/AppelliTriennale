package RetiDiCalcolatori.ProvaScritta11_11_2020.parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class ThreadAnalisiHandler extends Thread{


    private final Socket client;
    private final Registro registro;
    private final Random random=new Random();

    public ThreadAnalisiHandler(Socket client,Registro registro){
        this.client=client;
        this.registro=registro;
    }


    public void run(){

        try {
            BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()));
            String analisi=bf.readLine();

            //Lettura dati
            String[] split=analisi.split("#");
            String nomeAnalisi=split[0];
            double param1=Double.parseDouble(split[1]);
            double param2=Double.parseDouble(split[2]);
            int time=random.nextInt(0,10);
            sleep(time);

            //effettua analisi
            Risposta ris=registro.addAnalisi(param1,param2);

            ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(ris);
            oos.close();
            client.close();


        } catch (IOException|InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



}
