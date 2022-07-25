package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte.ThreadClient;

import ProvaScritta20_01_2021.SecondaParte.Misura;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client extends Thread{


    private final static int tPort=3000;
    private final static String hostname ="localhost";
    private Socket socket;
    private int id_Sensore;
    private Random random=new Random();





    public void run(){
        try{
            socket=new Socket(hostname,tPort);
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            id_Sensore=random.nextInt(0,100); //Sensore scelto randomicamente
            pw.write(""+id_Sensore);
            pw.close();

            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            Misura msg=(Misura) ois.readObject();




        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




}
