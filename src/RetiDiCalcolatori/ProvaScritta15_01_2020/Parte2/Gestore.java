package RetiDiCalcolatori.ProvaScritta15_01_2020.Parte2;

import ProvaScritta15_01_2020.Parte2.ThreadServer.HandlerConnessioni;
import ProvaScritta15_01_2020.Parte2.ThreadServer.ThreadTakeOfferta;

import java.awt.image.DataBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Gestore {

    Map<Integer,Record> db;
    private static int COUNT=0;



    public Gestore(){
        db=new HashMap<Integer,Record>();
        startServer();
    }

    private void startServer(){
        new HandlerConnessioni(this).start();
        new ThreadTakeOfferta(this).start();
    }

    public synchronized int addRichiesta(String richiesta){
        Record r=new Record();
        r.richiesta=richiesta;
        r.status=true;
        r.offerte=new LinkedList<String>();
        db.put(COUNT,r);
        int id_Richiesta=COUNT;
        COUNT++;
        return id_Richiesta;
    }
    public synchronized void addOfferta(Integer id_richiesta,String offerta){
        if(db.containsKey(id_richiesta)){//Dovrebbe contenerla per costruzione
            Record r=db.get(id_richiesta);
            if(r.status==true){
                r.offerte.add(offerta);
            }
        }
    }

    public synchronized void terminaRichiesta(Integer id_richiesta){
        if(db.containsKey(id_richiesta)){
            Record r=db.get(id_richiesta);
            r.status=false;
        }
    }


    public List<String> getOfferte(Integer id_richiesta) {
        if(db.containsKey(id_richiesta)){
            Record r1=db.get(id_richiesta);
            return r1.offerte;
        }
        return new LinkedList<String>(); //lista vuota
    }
}


class Record{
    String richiesta;
    boolean status;
    List<String> offerte;
    public Record(){
        status=true;
        offerte=new LinkedList<String>();
    }
}

