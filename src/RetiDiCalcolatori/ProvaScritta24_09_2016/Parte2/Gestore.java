package RetiDiCalcolatori.ProvaScritta24_09_2016.Parte2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//50 minuti
public class Gestore {
    private static int M;
    private static int N;

    private List<Record> db;

    public Gestore(int M,int N){
        this.M=M;
        this.N=N;
        db= Collections.synchronizedList(new LinkedList<Record>());

    }

    public synchronized boolean GestisciElaboratore() {
        if(M==0) return false;
        M--;
        return true;
    }
    public synchronized boolean gestisciCliente() {
        if(N==0) return false;
        N--;
        return true;
    }
    public synchronized void addOffertaRisorsa(OffertaRisorsa offerta,String hostname){
        Record r=new Record(offerta,hostname);
        db.add(r);
    }

    public synchronized String getOfferta(RichiestaRisorsa richiesta){
        for(Record record:db){
            OffertaRisorsa offerta= record.offerta;
            if(isEqual(richiesta,offerta)) return record.hostname;
        }
        return "Nessuna offerta trovata";
    }



    private boolean isEqual(RichiestaRisorsa r1,OffertaRisorsa r2){
        if(r1.getTipo()==r2.getTipo() && r1.getDescrizione().equals(r2.getDescrizione())){
            return true;
        }
        return false;
    }




}
class Record{
    OffertaRisorsa offerta;
    String hostname;

    public Record(OffertaRisorsa offerta, String hostname) {
        this.offerta=offerta;
        this.hostname=hostname;
    }
}


