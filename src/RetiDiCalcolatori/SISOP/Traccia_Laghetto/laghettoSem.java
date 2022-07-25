package RetiDiCalcolatori.SISOP.Traccia_Laghetto;

import java.util.concurrent.Semaphore;

public class laghettoSem extends laghetto {


    private Semaphore mutex=new Semaphore(1);
    private Semaphore[] Azione=new Semaphore[2];


    private int[] curr_persone=new int[2];
    private int curr_pesci;

    public laghettoSem(int minPesci,int maxPesci,int P,int N){
        super(maxPesci, minPesci,P,N);
        curr_pesci=maxPesci;
        Azione[pesca]=new Semaphore(maxPesci-minPesci);
        Azione[ripopolamento]=new Semaphore(0);

    }

    @Override
    protected void inizia(int t) throws InterruptedException {
        Azione[t].acquire();
        mutex.acquire();
        curr_persone[t]++;
        if(t==pesca) curr_pesci--;
        else curr_pesci+=10;
        System.out.format("Ci sono %s perscatori e %s addetti nel lago%n", curr_persone[pesca],curr_persone[ripopolamento]);
        mutex.release();


    }


    @Override
    protected void finisci(int t) throws InterruptedException {
        mutex.acquire();
        curr_persone[t]--;
        System.out.format("E' uscito un %s%n", (t==pesca) ? "PESCATORE":"ADDETTO");
        System.out.format("Ci sono %s perscatori e %s addetti nel lago%n", curr_persone[pesca],curr_persone[ripopolamento]);
        politicaRisveglio(t);
        mutex.release();

    }

    private void politicaRisveglio(int t){
        if(curr_persone[t]!=0) return; //non devo risvegliare nessuno
        if(t==pesca && maxPesci-curr_pesci>=10) Azione[t].release((maxPesci-curr_pesci)/10);
        if(t==ripopolamento && curr_pesci>minPesci) Azione[t].release(curr_pesci-minPesci);

    }

    public static void main(String[] args){

    }


}
