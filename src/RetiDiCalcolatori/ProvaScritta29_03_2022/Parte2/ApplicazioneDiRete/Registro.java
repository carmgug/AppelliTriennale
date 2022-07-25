package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete;



import com.sun.source.doctree.SeeTree;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Registro {


    private Map<Integer,Record> lotterie;

    public Registro(){
        lotterie= new HashMap<Integer,Record>();
    }

    public synchronized boolean addLotteria(String nomeLotteria,Set<Biglietto> biglietti ) throws IOException {
        int hashCode=nomeLotteria.hashCode();
        if(!lotterie.containsKey(hashCode)){
            Record record=new Record(nomeLotteria,biglietti);
            lotterie.put(hashCode,record);
            return true;
        }
        return false;
    }
    public synchronized void chiudiLotteria(int idLotteria){
        if(lotterie.containsKey(idLotteria)){
            lotterie.get(idLotteria).setStatus(false);
        }
    }

    public synchronized List<Biglietto> getBiglietti(Richiesta richiesta){
        int l=richiesta.getNomeLotteria().hashCode();
        if(lotterie.containsKey(l)){
            Record curr=lotterie.get(l);
            if(curr.getStatus())
                return curr.getBiglietti(richiesta.getNumeroBiglietti());
        }
        return null;
    }




}

class Record{
    private String nome;
    private boolean status; //true aperto false chiuso
    private Set<Biglietto> biglietti;


    Record(String nome,Set<Biglietto> biglietti){
        status=true;
        this.biglietti=new TreeSet<Biglietto>();
        for (Biglietto b: biglietti){
            if(b.getNomeLotteria()==nome) {
                Biglietto temp = new Biglietto(b);
                this.biglietti.add(temp);
            }
        }
    }

    public List<Biglietto> getBiglietti(int numeroBiglietti){
        if(biglietti.size()<numeroBiglietti){
            return null;
        }
        List<Biglietto> res=new LinkedList<>();
        Iterator<Biglietto> it=biglietti.iterator();
        while(it.hasNext()){
            Biglietto temp=it.next();
            res.add(temp); //Oggetto dai parametri non modificabili non ho problemi nel passarlo
            it.remove();
        }
        return res;

    }

    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status=status;
    }

    public String getNome(){
        return nome;
    }

}
