package SISOP.Traccia_Muratori;

import java.util.concurrent.Semaphore;

public class CasaSP extends Casa{


    private int[] MaxFile; //file di mattoni da raggiungere
    private int[] file_curr;

    //per accesso alle variabili protette
    private Semaphore mutex=new Semaphore(1);
    //per accesso ai muri
    private Semaphore[] possoLavorare=new Semaphore[2];



    public CasaSP(int N){
        super();
        this.N=N;
        MaxFile=new int[2];
        MaxFile[mattoni]=this.N*n_pareti; //vi sono N*n_pareti file da predisporre in totale
        MaxFile[cemento]=this.N*n_pareti; //sono uguali a quelle di mattoni.
        file_curr=new int[2];//inizializzate a 0

        //per ogni fila posso predisporre una fila di cemento o di mattoni
        //prima va depositato il cemento
        //poi i mattoni
        possoLavorare[mattoni]=new Semaphore(0);
        possoLavorare[cemento]=new Semaphore(n_pareti);


    }



    @Override
    protected boolean inizia(int t) throws InterruptedException {
        boolean esisteLavoro=false;
        mutex.acquire();
        if(file_curr[t]<MaxFile[t]){
            file_curr[t]++;
            esisteLavoro=true;
        }
        mutex.release();
        if(esisteLavoro) possoLavorare[t].acquire();
        return esisteLavoro;
    }

    @Override
    protected void termina(int t) {
        //ho finito di predisporre la fila di mattoni o di cemento
        //quindi lascio il posto a un muratore
        //il lavoro successivo sarà
        //fila di mattoni allora c'è posto per una fila di cemento
        //fila di cemento allora c'è posto per una fila di mattoni
        int Operazione=1-t;
        //1-MATTONI=CEMENTO //1-CEMENTO=MATTONI
        //1-0=1             //1-1=0

        possoLavorare[Operazione].release();
    }


    public static void main(String[] args){
        int N=20;
        Casa casa=new CasaSP(N);
        Muratore[] muratori_m=new Muratore[5];
        Muratore[] muratori_c=new Muratore[7];
        for(int i=0;i< muratori_m.length;i++){
            muratori_m[i]=new Muratore(casa.mattoni, Integer.toString(i), casa);
            muratori_m[i].start();
        }

        for(int i=0;i<muratori_c.length;i++){
            muratori_c[i]=new Muratore(casa.cemento, Integer.toString(i+5), casa);
            muratori_c[i].start();
        }



    }
}
