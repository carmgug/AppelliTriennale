package SISOP.Traccia_Giugno_2021;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TourFirenzeLock extends TourFirenze {

    private Lock lc=new ReentrantLock();
    private Condition guida=lc.newCondition();
    private Condition turista=lc.newCondition();
    private Condition InizioTour=lc.newCondition();
    private Condition FineTour=lc.newCondition();



    private LinkedList<Turista> coda=new LinkedList<>();
    private int turisti_curr=0;
    private boolean VisitaIniziata=false;
    private boolean VisitaFinita=false;


    @Override
    protected void attendiFormazioneGruppo() throws InterruptedException{
        lc.lock();
        try{
            turista.signalAll();
            while(turisti_curr<Turisti_suff){
                guida.await();
            }

        }finally {
            lc.unlock();
        }
    }

    @Override
    protected void visitaInizia() {
        lc.lock();
        try{
            VisitaIniziata=true;
            VisitaFinita=false;
            InizioTour.signalAll();
        }finally {
            lc.unlock();
        }

    }

    @Override
    protected void visitaFine() {
        lc.lock();
        try{
            VisitaIniziata=false;
            VisitaFinita=true;
            turisti_curr=0;
            FineTour.signalAll();
        }finally {

        }

    }


    private boolean MioTurno(){
        Turista e=(Turista) Thread.currentThread();
        if(e==coda.getFirst()) return true;
        else return false;
    }

    @Override
    protected void turistaInizia() throws InterruptedException {
        lc.lock();
        try{
            coda.addLast((Turista)Thread.currentThread());
            while(!MioTurno() || turisti_curr>=Turisti_suff /*|| VisitaIniziata*/){ //IN CASO SI VOGLIA accettare più di 20 turista allora basta togliere il secondo or e inserire il 3;
                turista.await();
            }
            coda.removeFirst();
            turisti_curr++;
            if(turisti_curr==20) guida.signal();
            turista.signalAll();
            while(!VisitaIniziata) InizioTour.await();
        }finally {
            lc.unlock();
        }

    }

    @Override
    protected void turistaFine() throws InterruptedException {
        lc.lock();
        try{
            while(!VisitaFinita) FineTour.await();

        }finally {
            lc.unlock();
        }

    }
}
