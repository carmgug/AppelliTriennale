package SISOP.Traccia_Funivia;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class FuniviaSem extends Funivia{


    private Semaphore mutex=new Semaphore(1);
    private Semaphore[] possoSalire=new Semaphore[2];
    private Semaphore possoScendere=new Semaphore(0);

    private Semaphore possoPartire=new Semaphore(0);


    private boolean tur_inBici;
    private LinkedList<Turista> turisti=new LinkedList<>();

    public FuniviaSem(){
        //prima salgono quelli a piedi;
        tur_inBici=false;
        possoSalire[piedi]=new Semaphore(0);
        possoSalire[bici]=new Semaphore(0);
    }



    @Override
    protected void pilotaStart() throws InterruptedException {
        //il pilota arriva e dice che si può entrare
        mutex.acquire();
        //false: A piedi true:In bici
        if(!tur_inBici) possoSalire[piedi].release(6);
        else possoSalire[bici].release(3);
        mutex.release();
        possoPartire.acquire();
        System.out.println("LA FUNIVIA E' PARTITA");
        //inizio viaggio
    }

    @Override
    protected void pilotaEnd() throws InterruptedException {
        mutex.acquire();

        for(Turista tur:turisti){
            System.out.format("Scende il turista[%s] di tipo:%s%n",tur.getID(),tur.getTipo()==piedi ? "PIEDI":"BICI");
        }

        possoScendere.release(turisti.size());//faccio scendere tutti i turisti;
        turisti.removeAll(turisti);
        tur_inBici=!tur_inBici; //cambio il turno corrente

        mutex.release();
    }

    @Override
    protected void turistaSali(int t) throws InterruptedException {
        possoSalire[t].acquire();
        mutex.acquire();
        turisti.add((Turista)Thread.currentThread());
        if(t==piedi && turisti.size()==6) possoPartire.release();
        else if(t==bici && turisti.size()==3) possoPartire.release();
        mutex.release();

    }

    @Override
    protected void turistaScendi(int t) throws InterruptedException {
        possoScendere.acquire();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("INIZIO GIORNATA DI LAVORO!!");
        Funivia funivia=new FuniviaSem();
        Turista[] turisti=new Turista[18+9];
        Pilota p=new Pilota("Pilota",funivia);
        p.setDaemon(true);
        p.start();
        for(int i=0;i<18;i++){
            turisti[i]=new Turista(i+"", funivia.piedi, funivia);
            turisti[i].start();
        }
        for(int i=18;i<18+9;i++){
            turisti[i]=new Turista(i+"", funivia.bici, funivia);
            turisti[i].start();
        }
        for(int i=0;i<18+9;i++){
            turisti[i].join();
        }
        System.out.println("FINE GIORNATA DI LAVORO!!");


    }
}
