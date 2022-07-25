package RetiDiCalcolatori.SISOP.Traccia_Cementificio;

import SISOP.Tracce_Esame.Traccia_AziendaAgricola.Adetto;

import java.util.concurrent.Semaphore;

public class CementificioSem extends Cementificio {

    private int sacchi_curr;

    private Semaphore mutex=new Semaphore(1);
    private Semaphore possoEntrare;
    //si presuppone che più clienti contemporanemente possono prelevare un sacco;
    private Semaphore possoPrelevare;

    private Semaphore possoRifornire=new Semaphore(0);


    public CementificioSem(int N,int P){
        super();
        this.N=N;this.P=P;
        sacchi_curr=P;
        possoEntrare=new Semaphore(N,true);
        possoPrelevare=new Semaphore(P);

    }

    @Override
    public void entra() throws InterruptedException {
        possoEntrare.acquire();

    }

    @Override
    public void esci() {
        possoEntrare.release();
    }

    @Override
    public void preleva() throws InterruptedException {
        possoPrelevare.acquire();
        mutex.acquire();
        sacchi_curr--;
        if(sacchi_curr==0) possoRifornire.release();
        mutex.release();

    }

    @Override
    public void iniziaRiferimento() throws InterruptedException {
        possoRifornire.acquire();

    }

    @Override
    public void terminaRiferimento() throws InterruptedException {
        mutex.acquire();
        sacchi_curr=P;
        possoPrelevare.release(P);

    }


    public static void main(String[] args){
        int N=100,P=1000;
        int C=10;int A=1;
        Cementificio negozio=new CementificioSem(N,P);
        Cliente[] Clienti=new Cliente[C];
        Addetto[] Addetti=new Addetto[A];
        for(int i=0;i<C;i++){
            Clienti[i]=new Cliente(Integer.toString(i), negozio);
            Clienti[i].start();
        }

        for(int i=0;i<A;i++){
            Addetti[i]=new Addetto(Integer.toString(i+C), negozio);
            Addetti[i].start();
        }

        for(int i=0;i<C;i++){
            try {
                Clienti[i].join();
            }catch(InterruptedException e){}
        }

        Addetti[0].interrupt();

    }
}
