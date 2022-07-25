package RetiDiCalcolatori.SISOP.Traccia_Trenino;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class TreninoSEM extends Trenino{




    private Semaphore mutex=new Semaphore(1);
    private Semaphore puntoDiAccesso=new Semaphore(0);
    //posso muovere solamente se sono saliti tutti e dieci i turisti
    private Semaphore possoMuovere=new Semaphore(0);
    private Semaphore[] possoScendere=new Semaphore[cabine.length];


    private int tur_arrivati;

    private int treno_curr;
    private int[] pos_curr=new int[cabine.length];


    public TreninoSEM(){
        super();
        for(int i=0;i< cabine.length;i++){ //inizializziamo la posizione delle cabine e gli
            //strumenti per accedere a quest'ultime
            pos_curr[i]=i;//posizione corrente
            possoScendere[i]=new Semaphore(0);
        }
        treno_curr=0;//indica il treno al punto di accesso
        tur_arrivati=0;//non ci sono turisti;


    }



    @Override
    protected void turSali() throws InterruptedException {
        mutex.acquire();
        tur_arrivati++;//è arrivato un turista
        mutex.release();
        puntoDiAccesso.acquire();//se supero il punto di Accesso Posso Salire
        //ho superato il punto di Accesso
        mutex.acquire();
        tur_arrivati--;//è salito un tur;
        mutex.release();


    }

    @Override
    protected void turScendi() throws InterruptedException {
        int n_cabina=-1;
        //leggo in maniera prottetta il numero della cabina e salvo
        //stai attento in teoria per la sequenzialità imposta
        //scendi viene effettuato dopo essere salito
        //e la variabile treno_curr non viene modificata finchè tutti i turisti non sono saliti
        //e l'ultimo turista prima legge il valore e solo dopo da il permesso di partire
        //quindi l'accesso potrebbe anche non volere per forza il mutex.
        //perchè sostanzialmente è tutto fermo e solo i turisti sono in running che leggono (non modificano)
        mutex.acquire();
        n_cabina=treno_curr;
        mutex.release();
        possoMuovere.release();
        possoScendere[n_cabina].acquire();

    }

    @Override
    protected void impFaiScendere() throws InterruptedException {
        mutex.acquire();
        if(cabine[treno_curr]==0) {possoScendere[treno_curr].release(10); cabine[treno_curr]=10;}
        mutex.release();

    }

    @Override
    protected void impFaiSalire() throws InterruptedException {
        mutex.acquire();
        if(tur_arrivati>=10) {
            puntoDiAccesso.release(10);
            cabine[treno_curr] = 0;
        }
        else possoMuovere.release(10); //se non salgono i turisti il treno può subito partire;
        mutex.release();

    }

    @Override
    protected void impMuovi() throws InterruptedException {
        possoMuovere.acquire(10); //posso muovere solo se sono saliti tutti e 10 i turisti;
        mutex.acquire();
        //struttura circolare--->contatore modulare
        //il treno si muove in senso orario
        //quindi se lo 0 era al punto di accesso il prossimo sarà il 9 poi 8 poi 7 ecc
        //cabine.lenght indica quante cabine ci sono sulla struttura circolare 10 richieste dalla traccia
        treno_curr=(treno_curr-1+cabine.length)%cabine.length;
        mutex.release();
    }
}
