package RetiDiCalcolatori.ProvaScritta18_03_2021.Parte1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WebService {

    Map<Studente, List<Esame>>db;

    public List<Esame> EsamiStudente(String matricola){
        Studente curr=new Studente(matricola,"","");
        //studente fittizzio utile per la ricerca;
        //containskey della mappa utilizza equals and hascode;
        if(db.containsKey(curr)){
            return db.get(curr); //ritorno la lista
        }
        return new LinkedList<Esame>(); //ritorno una lista vuota;
    }

    public List<Studente> StudentiPerEsame(String cod,int voto ){
        List<Studente> studenti=new LinkedList<Studente>();
        for(Map.Entry<Studente,List<Esame>> entry:db.entrySet()){
            if(trovaEsame(entry.getValue(),cod,voto)) studenti.add(entry.getKey());
        }
        return studenti;
    }

    private boolean trovaEsame(List<Esame> esami,String cod,int voto){
        for(Esame e: esami){
            if(e.cod.equals(cod) && e.voto>=voto) return true;
        }
        return false;
    }






}
class Studente{
    String matricola;
    String nome;
    String cognome;

    public Studente(String mat,String nome,String cognome){

    }

    public boolean equals(Object o){
        if(!(o instanceof Studente) || o==null){
            return false;
        }
        if(o==this) return true;
        Studente s=(Studente) o;
        return s.matricola.equals(this.matricola);
    }

    public int hashCode(){
        return matricola.hashCode();
    }

}
class Esame{
    String cod;
    int voto;

}
