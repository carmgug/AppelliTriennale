package RetiDiCalcolatori.SISOP.Traccia_AziendaAgricola;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AziendaAgricolaSP extends AziendaAgricola{


    private int sacchi_curr=super.num_sacchi;
    private int num_clienti;

    Semaphore CiSonoSacchetti=new Semaphore(super.num_sacchi,true);
    Semaphore NonCiSonoSacchetti=new Semaphore(0);
    Semaphore mutex=new Semaphore(1);
    Semaphore mutexB=new Semaphore(1);

    public AziendaAgricolaSP(int num_clienti){
        super();
        this.num_clienti=num_clienti;
    }

    @Override
    public void acquista(int num) throws InterruptedException {

        mutex.acquire();
        super.guadagno+=num*super.costo;
        mutex.release();
    }

    @Override
    public void prelevaSacchi(int num) throws InterruptedException {

        int i=0;
        while(i<num){
            CiSonoSacchetti.acquire();
            mutexB.acquire();
            System.out.println(Thread.currentThread().getId() +" ha preso un sacco");
            sacchi_curr-=1;
            if(sacchi_curr==0) NonCiSonoSacchetti.release();
            mutexB.release();
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getId() +" ha portato un sacco in macchina");
            i=i+1;
        }
    }

    @Override
    public void aggiungiSacchi() throws InterruptedException {
        NonCiSonoSacchetti.acquire();
        mutexB.acquire();
        sacchi_curr+=200;
        mutexB.release();
        CiSonoSacchetti.release(200);


    }

    @Override
    public boolean SonoPresentiClienti() {
        return num_clienti!=0;
    }

    public static void main(String[] args){
        AziendaAgricolaSP Negozio=new AziendaAgricolaSP(10);
        Cliente[] clienti=new Cliente[100];
        Adetto lavoratore=new Adetto(Negozio);
        System.out.println("INIZIO GIORNATA DI LAVORO!");
        lavoratore.start();
        for(int i=0;i<10;i++) {
            clienti[i] = new Cliente(Integer.toString(i), Negozio);
            clienti[i].start();
        }


        for(int i=0;i<10;i++){
            try{
                clienti[i].join();
            }catch(InterruptedException e){}

        }


        System.out.println("FINE GIORNATA");
        System.out.println(Negozio.guadagno);
    }
}
