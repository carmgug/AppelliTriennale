package RetiDiCalcolatori.SISOP.Traccia_Boccaccio;

import java.util.concurrent.Semaphore;

public class BoccaccioSem extends Boccaccio{


    private Semaphore mutex=new Semaphore(1);
    private Semaphore possoPrendere;
    private Semaphore possoRiempire;
    private Semaphore Piango=new Semaphore(0);



    private int caramelle_curr;
    private int[] colore_caramelle;
    private int curr_piangono=0;

    public BoccaccioSem(int N){
        super(N);
        caramelle_curr=0;
        colore_caramelle=new int[N];
        riempiBarattolo();
        possoPrendere=new Semaphore(caramelle_curr);
        possoRiempire=new Semaphore(0);
    }

    private void riempiBarattolo(){

        for(int i=0;i<colore_caramelle.length;i++){
            if(colore_caramelle[i]<3) {
                colore_caramelle[i]+=3-colore_caramelle[i];
                caramelle_curr+=colore_caramelle[i];
            }
        }
        while(caramelle_curr<100){
            int j=(int) Math.random()*(colore_caramelle.length); //numero random da 0 a N escluso
            colore_caramelle[j]++;
            caramelle_curr++;
        }
    }

    @Override
    protected boolean prendi(int c) throws InterruptedException {
        possoPrendere.acquire();
        mutex.acquire();
        boolean Hopreso=false;
        if(colore_caramelle[c]>0){
            colore_caramelle[c]--;
            caramelle_curr--;
            Hopreso=true;
        }
        mutex.release();
        if(!Hopreso) possoPrendere.release();
        return Hopreso;
    }

    @Override
    protected void piangi() throws InterruptedException {
        possoRiempire.release();
        mutex.acquire();
        curr_piangono++;
        mutex.release();
        Piango.acquire();


    }

    @Override
    protected void riempi() throws InterruptedException {
        possoRiempire.acquire(3);
        mutex.acquire();
        int val_curr=caramelle_curr;
        int val_bambini=curr_piangono;
        riempiBarattolo();
        mutex.release();
        Piango.release(curr_piangono);
        possoPrendere.release(caramelle_curr-val_curr); //i permessi rilasciati devono essere pari alle caramelle
        //aggiunte

    }

}
