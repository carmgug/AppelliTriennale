package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte;

import ProvaScritta2020gennaio.SecondaParte.Gestore.ThreadHandlerRichiesta;

import java.io.IOException;
import java.util.*;

public class Server {

    Map<Integer, List<Dato>> db;

    public Server(){
        db= new HashMap<Integer,List<Dato>>();
        startServer();
    }

    private void startServer()  {
        try {
            new ThreadHandlerClient1(this);
            new ThreadHandlerClient2(this);
            new ThreadHandlerCentralina(this);
            new ThreadHandlerCentralinaRegistrata(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public synchronized boolean addCentralina(int idCentralina){
        if(!db.containsKey(idCentralina)){
            List<Dato> dati=Collections.synchronizedList(new LinkedList<Dato>());
            db.put(idCentralina,dati);
            return true;
        }
        return false;

    }

    public synchronized void addDato(int idCentralina,Dato d){
        if(db.containsKey(idCentralina)){
            db.get(idCentralina).add(d);
        }
    }

    public synchronized Dato getMedia(int idCentralina,long from,long to){
        if(db.containsKey(idCentralina)){
            List<Dato> dati=db.get(idCentralina);
            String grandezza="";
            int occ=0;
            double med=0;
            for (Dato d: dati){
                if(grandezza.length()==0) grandezza=d.getGrandezza();
                if(d.getTime()>= from && d.getTime()<=to && d.getGrandezza()==grandezza){
                    occ++;
                    med+=d.getValore();
                }
            }
            if(occ!=0) med=med/occ;
            return new Dato(grandezza,med,-1);
        }
        return new Dato("error",-1,-1);
    }


    public synchronized Dato getDato(int idCentralina, String grandezza) {
        if(db.containsKey(idCentralina)){
            List<Dato> dato=db.get(idCentralina);
            for(Dato d: dato){
                if(d.getGrandezza()==grandezza){
                    return new Dato(d.getGrandezza(),d.getValore(),d.getTime());
                }
            }
        }
        return new Dato("-1",-1,-1); //nel caso la grandezza richiesta non è stata trovata
    }
}

