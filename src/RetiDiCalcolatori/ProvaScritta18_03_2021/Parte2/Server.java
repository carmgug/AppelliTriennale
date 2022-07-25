package RetiDiCalcolatori.ProvaScritta18_03_2021.Parte2;

import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Server {

    private final static int tPort=1111;
    private Registro registro;


    private void startServer(){
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    ServerSocket socket=new ServerSocket(tPort);
                    while(true){
                        Socket client=socket.accept();
                        new ThreadRichiestaHandler(client,registro);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();

    }

}
class Registro{
    private Map<String,Record> db;

    public Registro(){
        db= Collections.synchronizedMap(new HashMap<String,Record>());
        //Non sarebbe necessario la mappa sincronizzata perchè i Thread itereranno sulla lista
        //Non modificando la mappa ma solo leggendola, però nel caso si volesse aggiungere un nuovo negozio
        //durante l'esecuzione del server allora è necessaria la mappa sincronizzata.
    }

    public Map<String,Record> getMappa(){
        return db;
    }


    public synchronized void addVendita(String idNegozio, double prezzoVendita) {
        db.get(idNegozio).addVendita(prezzoVendita);
    }

    public synchronized void addNegozio(String idNegozio,String hostname){
        if(!db.containsKey(idNegozio)){
            Record record=new Record(hostname);
            db.put(idNegozio,record);
            new ThreadHandle24hour(idNegozio,hostname);
        }


    }

    public synchronized Double getVendite(String idNegozio) {
        //per costruzione si ha obbligatorimante tale id
        return db.get(idNegozio).getVendite();

    }
}

class Record{
    private final String hostnameNegozio;
    private double ImportototaleGiornaliero;

    public Record(String hostnameNegozio){
        this.hostnameNegozio=hostnameNegozio;
        ImportototaleGiornaliero=0;
    }

    public void addVendita(double importo){
        ImportototaleGiornaliero+=importo;
    }
    public double getVendite(){
        Double res=ImportototaleGiornaliero;
        ImportototaleGiornaliero=0;
        return res;
    }

    public String getHostname() {
        return  hostnameNegozio;
    }
}
