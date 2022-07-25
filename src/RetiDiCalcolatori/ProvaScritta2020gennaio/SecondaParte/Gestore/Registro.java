package RetiDiCalcolatori.ProvaScritta2020gennaio.SecondaParte.Gestore;

import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Registro {

    private static int COUNTER=0;
    HashMap<Integer,RecordRegistro> Richieste=new HashMap();

    public synchronized int addRichiesta(Socket client,String richiesta){
        RecordRegistro record=new RecordRegistro(client,richiesta);
        Richieste.put(COUNTER,record);
        COUNTER++;
        return COUNTER;
    }

    public synchronized Socket chiudiRichiesta(int idRichiesta){
        Richieste.get(idRichiesta).setStatus(false);
        return Richieste.get(idRichiesta).getSocketClient();
    }

    public synchronized boolean addOfferta(int idRichiesta,String offerta){
        RecordRegistro record=Richieste.get(idRichiesta);
        if(record.getStatus()){
            record.addOfferta(offerta);
            return true; //offerta aggiunta la richiesta è ancora aperta
        }
        return false; //offerta non aggiunta allora la richiesta è stata chiusa


    }
    public synchronized List<String> getOfferteRicevute(int idRichiesta){
        return Richieste.get(idRichiesta).getOfferteRicevute();
    }


    public synchronized String getRichiesta(int idRichiesta) {
        return Richieste.get(idRichiesta).getRichiesta();
    }

    public synchronized boolean getStatus(int idRichiesta){
        return Richieste.get(idRichiesta).getStatus();
    }




}

class RecordRegistro {
    private Socket socketClient;
    private List<String> OfferteRicevute;
    private String richiesta;
    private boolean status;

    RecordRegistro(Socket client, String richiesta){
        this.status=true;
        this.socketClient=client;
        this.richiesta=richiesta;
        OfferteRicevute=new LinkedList<>();
    }

    void setStatus(boolean status){
        this.status=status;
    }
    boolean getStatus(){
        return status;
    }

    void addOfferta(String Offerta){
        OfferteRicevute.add(Offerta);
    }

    List<String> getOfferteRicevute(){
        return OfferteRicevute;
    }

    Socket getSocketClient(){
        return socketClient;
    }

    String getRichiesta(){
        return richiesta;
    }

}
