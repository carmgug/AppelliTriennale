package RetiDiCalcolatori.ProvaScritta2020Maggio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Server {

    int tPort=3000;
    Map<Integer, Record> db;


    public Server(){
        db=new HashMap<Integer,Record>();
        startServer();
    }

    public void startServer() {
        new Runnable() {

            ServerSocket serverSocket;

            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(tPort);
                    while (true) {
                        Socket s = serverSocket.accept();
                        new ThreadHandlerOfferta(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
    }

    public synchronized String addOfferta(Offerta offerta){
        int curr_prodotto= offerta.prodotto.codice;
        if(db.containsKey(curr_prodotto)){
            Record r=db.get(curr_prodotto);
            if(r.status!=false){
                if(r.prodotto.prezzo<=offerta.value){
                    r.offerteRicevute.add(offerta);
                    return "OK";
                }
                return "TROPPO BASSA";
            }
            return "SCADUTA";
        }
        return "SCADUTA";
    }


    public long getTime(int codiceProdotto) {
        //L'elemento esiste sicuramente
        long time=db.get(codiceProdotto).prodotto.tempo;
        return time;
    }

    public List<Offerta> chiudiAsta(int codiceProdotto) {
        //l'elemento esiste sicuramente per costruzione
        Record r=db.get(codiceProdotto);
        r.setStatus(false);
        return r.getofferteRicevute();
    }

    //metodi per aggiungere un Prodotto;
    public boolean addProdotto(Prodotto p){
        int codice=p.getCodice();
        if(db.containsKey(codice)) return false; //elemento già presente;

        Record r=new Record(p);
        db.put(codice,r);
        //creiamo il thread che controllerà la chiusura dell'asta.
        return true;
    }


}

class Record{
    List<Offerta> offerteRicevute;
    Prodotto prodotto;
    boolean status;

    public Record(Prodotto p){
        this.prodotto=p;
        this.status=true;
        this.offerteRicevute=new LinkedList<>();
    }

    public void setStatus(boolean b) {

    }

    public List<Offerta> getofferteRicevute() {
        return null;
    }
}





