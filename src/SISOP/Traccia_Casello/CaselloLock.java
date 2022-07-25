package SISOP.Traccia_Casello;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.ReentrantLock;

public class CaselloLock {

    private int N_porte;
    private double T;
    //P code diverse



    private static Lock l=new ReentrantLock();
    private static Condition[] SportelloLibero;
    private ArrayList<LinkedList<Veicolo>> Coda;

    //variabili da proteggere
    private double incasso;

    public CaselloLock(int n,double tariffa){
        this.incasso=0;
        this.N_porte=n;
        this.T=tariffa;
        SportelloLibero=new Condition[N_porte];
        Coda=new ArrayList<>(N_porte);
        for(int i=0;i<N_porte;i++){
            SportelloLibero[i]=l.newCondition();
            Coda.set(i,new LinkedList<Veicolo>());
        }

    }

    //da proteggere
    public void Inserisci_V(Veicolo v,int p) throws InterruptedException {
        l.lock();
        try{
            Coda.get(p).addLast(v);
            while(!MioTurno(v, Coda.get(p))){
                SportelloLibero[p].await();
            }
            System.out.print("Il veicolo "+v.getId()+"è entrato in coda al casello "+p);
        }
        finally {
            l.unlock();
        }

    }


    private boolean MioTurno(Veicolo v,LinkedList<Veicolo> porta){
        return porta.get(0)==v;
    }

    public void Paga(Veicolo v,int p,int x) throws InterruptedException {
        l.lock();
        try {
            incasso += T * x;
            Coda.get(p).removeFirst();
            if(!Coda.get(p).isEmpty()) SportelloLibero.notifyAll();
        }
        finally {
            l.unlock();
        }

    }

    public int getPorta(){
        return (int) Math.random()*(Coda.size()-0)+0;
    }


}
