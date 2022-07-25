package RetiDiCalcolatori.ProvaScritta11_11_2020.parte2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server {


    private final Registro  registro;
    private final static int tPort=1111;

    public Server(){
        registro=new Registro();
        StartServer();
    }


    private void StartServer(){
        Thread t=new Thread(new Runnable(){
            public void run(){
                try{
                    ServerSocket server=new ServerSocket(tPort);
                    while(true){
                        Socket client=server.accept();
                        new ThreadAnalisiHandler(client,registro);

                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }



}
class Registro{

    private static int COUNTER=0; //MAX20
    List<Double> db;

    public Registro(){
        db= Collections.synchronizedList(new LinkedList<Double>());
    }

    public synchronized Risposta addAnalisi(double param1,double param2){
        if(COUNTER==20) new Risposta(-1,-1);
        COUNTER++;
        double x=Math.sqrt(param1);
        double y=Math.pow(param2,2);

        db.add(x);

        return new Risposta(x,y);
    }

    public synchronized double valoreMedio_X(){
        int cardinalita=db.size();
        double somma=0;
        for(Double x: db){
            somma+=x;
        }
        return somma/cardinalita;
    }

}
