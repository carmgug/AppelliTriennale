package SISOP.Traccia_SuperMercato;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasseLC extends Casse{

    private Lock l=new ReentrantLock();
    private Condition inCoda=l.newCondition();
    private Condition Esci=l.newCondition();
    private Condition[] inConsegna;

    private boolean[] Cassa_Libera;
    private boolean[] Consegnato;
    private int[] prod_Consegnati;
    private LinkedList<Cliente> coda=new LinkedList<>();


    public CasseLC(int N_Casse) {
        super(N_Casse);
        Cassa_Libera=new boolean[N_Casse];
        Consegnato=new boolean[N_Casse];
        prod_Consegnati=new int[N_Casse];
        inConsegna=new Condition[N_Casse];
        for(int i=0;i<N_Casse;i++){
            inConsegna[i]=l.newCondition();
        }
    }

    private boolean isCassaLibera(){
        int i=0;
        while(i<N_Casse){
            if(Cassa_Libera[i]==true) return true;
            i++;
        }
        return false;
    }

    private boolean MioTurno(){
        Cliente x=(Cliente) Thread.currentThread();
        return coda.getFirst()==x;
    }

    @Override
    protected int getIdCassa() throws InterruptedException {
        int ID=-1;
        l.lock();
        try{
            coda.addLast((Cliente)Thread.currentThread());
            while(!MioTurno() || !isCassaLibera()){
                inCoda.await();
            }
            ID=0;
            while(!Cassa_Libera[ID]) ID++; //esiste sicuramente una
            Cassa_Libera[ID]=false; //occupata da me;
            //ritorna l'ID
        }finally {
            l.unlock();
        }
        return ID;
    }

    @Override
    protected void consegnaProdotti(int id, int p) throws InterruptedException {
        l.lock();
        try{
            prod_Consegnati[id]=p;
            Consegnato[id]=true;
            inConsegna[id].signal();
            while(Consegnato[id]){
                Esci.await();
            }

        }finally {
            l.unlock();
        }

    }

    @Override
    protected int segnalaCassaLibera(int id) throws InterruptedException {
        int p=-1;
        l.lock();
        try{
            Cassa_Libera[id]=true;
            inCoda.signalAll();
            while (!Consegnato[id]) {
                inConsegna[id].await();
            }
            p=prod_Consegnati[id];
        }finally {
            l.unlock();
        }
        return p;
    }

    @Override
    protected void congedaCliente(int id) {
        l.lock();
        try{
            Consegnato[id]=false;
            Esci.signalAll();

        }finally {
            l.unlock();
        }
    }
}
