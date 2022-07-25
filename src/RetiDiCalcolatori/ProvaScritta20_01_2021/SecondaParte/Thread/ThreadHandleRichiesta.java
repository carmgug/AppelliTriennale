package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte.Thread;

import ProvaScritta20_01_2021.SecondaParte.Misura;
import ProvaScritta20_01_2021.SecondaParte.Server;

import java.io.*;
import java.net.Socket;

public class ThreadHandleRichiesta extends Thread{



    private final Socket client;
    private final Server server;

    public ThreadHandleRichiesta(Socket client,Server server){
        this.client=client;
        this.server=server;
    }


    public void run(){
        try{
            BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()));
            String msg=bf.readLine();
            bf.close();
            //Preleva misura
            int id_Sensore=Integer.parseInt(msg);
            Misura ris=server.getMisura(id_Sensore);
            //Invia misura
            ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(ris);
            oos.close();
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }


    }



}
