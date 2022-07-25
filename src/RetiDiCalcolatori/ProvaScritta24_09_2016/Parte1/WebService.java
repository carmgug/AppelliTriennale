package RetiDiCalcolatori.ProvaScritta24_09_2016.Parte1;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class WebService {


    private Map<String, Info> db;

    public int VotoStudente(String esame, int matricola){
        if(db.containsKey(esame)){
            Info i=db.get(esame);
            if(i.voti.containsKey(matricola)){
                int voto=i.voti.get(matricola);
                return voto;
            }

        }

        return -1;
    }

    public String EsameGiorno(Data data){
        for(Map.Entry<String,Info> entry:db.entrySet()){
            Data d2=entry.getValue().d;
            if(isEqual(data,d2)){
                return entry.getKey();
            }
        }

        return "nessun risultato trovato";
    }

    private boolean isEqual(Data d1,Data d2){
        if(d2.getAnno()==d1.getAnno() && d2.getMese()==d1.getMese() && d2.getGiorno()==d1.getGiorno()){
            return true;
        }
        return false;
    }



}
class Data implements Serializable{
    int Giorno;
    int Mese;
    int Anno;

    public int getGiorno() {
        return Giorno;
    }

    public int getMese() {
        return Mese;
    }

    public int getAnno() {
        return Anno;
    }
}
class Info{
    Map<String,Integer> voti; //Studenti che hanno passato il seguente esame;
    Data d; //data del prossimo appello
}
