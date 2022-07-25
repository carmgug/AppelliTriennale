package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadHandlerScarico extends Thread{
    private final int tPort=4000;
    private int n_banchina;
    private int id_nave;
    private final Porto porto;
    private Socket connection;

    public ThreadHandlerScarico(Porto porto,int n_banchina,int id_nave){
        this.n_banchina=n_banchina;
        this.porto=porto;

    }




    public void run(){
        try {
            //supposto che il porto conosce l'indirizzo ip del porto;
            String address = porto.getAddressOperatore(n_banchina);

            connection = new Socket(address, tPort);
            PrintWriter pw = new PrintWriter(connection.getOutputStream(), true);
            pw.write("ok");
            pw.close();

            //Aspetta teminazione scaricamento
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String msg = bf.readLine();
            if (msg.equals("ok")) {
                porto.removeNave(n_banchina);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
