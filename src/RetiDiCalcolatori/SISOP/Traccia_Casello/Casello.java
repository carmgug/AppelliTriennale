package RetiDiCalcolatori.SISOP.Traccia_Casello;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Casello {

    private int N_porte;
    private double T;
    //P code diverse


    //Semafori
    private static Semaphore mutexA=new Semaphore(1);
    private static Semaphore[] Coda;

    //variabili da proteggere
    private double incasso;

    public Casello(int n,double tariffa){
        this.incasso=0;
        this.N_porte=n;
        this.T=tariffa;
        Coda=new Semaphore[N_porte];
        for(int i=0;i<Coda.length;i++){
            Coda[i]=new Semaphore(1);
        }

    }

    //da proteggere
    public void Inserisci_V(Veicolo v,int p) throws InterruptedException {
        Coda[p].acquire();
        System.out.print("Il veicolo "+v.getId()+"è entrato al casello "+p);
    }

    public void Paga(Veicolo v,int p,int x) throws InterruptedException {
        mutexA.acquire();
        incasso+=T*x;

        mutexA.release();
        Coda[p].release();
    }

    public int getPorta(){
        return (int) Math.random()*(Coda.length-0)+0;
    }








}
