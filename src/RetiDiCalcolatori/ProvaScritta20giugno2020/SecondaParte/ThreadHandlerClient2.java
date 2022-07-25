package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadHandlerClient2 {

    private final static int tport=3000;
    private final ServerSocket server;
    private final Server gestore;


    public ThreadHandlerClient2(Server gestore) throws IOException {
        this.gestore=gestore;
        server=new ServerSocket(tport);
    }

    public void run(){
        try{
            while (true) {
                Socket client=server.accept();
                BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()));

                String line=bf.readLine();
                String[] split=line.split("#");
                int idCentralina=Integer.parseInt(split[0]);
                String grandezza=split[1];

                bf.close();

                Dato d=gestore.getDato(idCentralina,grandezza);

                ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(d);

                oos.close();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
