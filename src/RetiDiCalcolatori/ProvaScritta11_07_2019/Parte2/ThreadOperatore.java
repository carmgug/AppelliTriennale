package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadOperatore extends Thread{

    private final int tPort=4000;
    private final int n_banchina;
    private final static int min_t=2,max_t=5;
    private final Porto porto;

    private ServerSocket serverSocket;
    private Random random=new Random();


    public ThreadOperatore(Porto porto,int n_banchina){
        this.porto=porto;
        this.n_banchina=n_banchina;
    }


    public void run(){

        try {
            serverSocket =new ServerSocket(tPort);
            while(true){
                Socket socket=serverSocket.accept();
                BufferedReader bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg=bf.readLine(); //nave arrivata;


                int n_container = porto.getNumeroContainer(Integer.parseInt(msg));
                for (int i = 0; i < n_container; i++) {
                    long tmp_Attesa = random.nextLong(2, 5);
                    TimeUnit.MINUTES.sleep(tmp_Attesa);
                }
                PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
                pw.write("ok");
                pw.close();
                socket.close();
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }






    }



}
