package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte;

import ProvaScritta2020gennaio.SecondaParte.Gestore.Gestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadHandlerClient1 extends Thread { //ho sbagliato ho implementato la seconda richiesta e non la prima


    private final ServerSocket server;
    private final Server gestore;
    private final static int tPort=4000;

    public ThreadHandlerClient1(Server gestore) throws IOException {
        this.gestore=gestore;
        server=new ServerSocket(tPort);
    }


    public void run(){
        try{
            while(true){
                Socket client=server.accept();
                BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()));

                String line=bf.readLine();
                String[] split=line.split("#");
                int idCentralina=Integer.parseInt(split[0]);
                long from=Long.parseLong(split[1]);
                long to=Long.parseLong(split[2]);
                bf.close();

                Dato res=gestore.getMedia(idCentralina,from,to);

                ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(res);

                oos.close(); client.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
