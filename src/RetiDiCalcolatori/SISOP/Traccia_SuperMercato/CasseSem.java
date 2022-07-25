package RetiDiCalcolatori.SISOP.Traccia_SuperMercato;

import java.util.concurrent.Semaphore;

public class CasseSem extends Casse{

    private Semaphore mutex=new Semaphore(1);
    private Semaphore coda=new Semaphore(0,true);
    private Semaphore[] Consegnati;
    private Semaphore[] Esci;

    private boolean[] Cassa_Libera;
    private int[] prodotti_Consegnati;

    public CasseSem(int N_Casse){
        super(N_Casse);
        Cassa_Libera=new boolean[N_Casse];
        prodotti_Consegnati=new int[N_Casse];
        Consegnati=new Semaphore[N_Casse];
        Esci=new Semaphore[N_Casse];
        for(int i=0;i<N_Casse;i++){
            Consegnati[i]=new Semaphore(0);
            Esci[i]=new Semaphore(0);
        }

    }

    @Override
    protected int getIdCassa() throws InterruptedException {
        coda.acquire();
        mutex.acquire();
        int Id=0;
        while(!Cassa_Libera[Id]) Id++;
        Cassa_Libera[Id]=false;
        mutex.release();
        return Id;
    }

    @Override
    protected void consegnaProdotti(int id, int p) throws InterruptedException {
        mutex.acquire();
        prodotti_Consegnati[id]=p;
        mutex.release();
        Consegnati[id].release();
        Esci[id].acquire();

    }

    @Override
    protected int segnalaCassaLibera(int id) throws InterruptedException {
        mutex.acquire();
        Cassa_Libera[id]=true;
        mutex.release();
        coda.release();
        Consegnati[id].acquire();
        mutex.acquire();
        int p=prodotti_Consegnati[id];
        mutex.release();
        return p;
    }

    @Override
    protected void congedaCliente(int id) {
        Esci[id].release();
    }
}
