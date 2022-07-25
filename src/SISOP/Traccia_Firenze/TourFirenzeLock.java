package SISOP.Traccia_Firenze;

import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class TourFirenzeLock extends TourFirenze{


    private Lock l=new ReentrantLock(true);
    private Condition gruppoFormato=l.newCondition();
    private Condition tourIniziato=l.newCondition();
    private Condition possoEntrare=l.newCondition();
    private Condition FineTour=l.newCondition();

    LinkedList<Thread> coda=new LinkedList<>();
    private int curr_tur;//turisti nel gruppo corrente;
    private boolean TourInziato;
    private boolean TourFinito;

    public TourFirenzeLock(){
        super();
        curr_tur=0;
        TourInziato=false;
        TourFinito=false;
    }

    //metodi DI supporto
    protected boolean gruppoFormato(){
        return curr_tur>=20;
    }

    @Override
    protected void attendiFormazioneGruppo() throws InterruptedException {
        l.lock();
        try{
            while(!gruppoFormato()){
                gruppoFormato.await();
            }
            System.out.println("Il gruppo è formato!!");
        }finally {
            l.unlock();
        }
    }

    @Override
    protected void visitaInizia() {
        l.lock();
        try{
            TourInziato=true;
            TourFinito=false;
            tourIniziato.signalAll();
        }finally {
            l.unlock();
        }

    }

    @Override
    protected void visitaFine() {
        l.lock();
        try{
            TourInziato=false;
            TourFinito=true;
            FineTour.signalAll();
            curr_tur=0;
            possoEntrare.signalAll();
        }finally {
            l.unlock();
        }

    }


    private boolean possoEntrare(){
        if(TourInziato || gruppoFormato()) return false; //non puoi entrare
        else return coda.getFirst()==Thread.currentThread(); //vedo se c'è qualcuno prima di me
    }

    @Override
    protected void turistaInizia() throws InterruptedException{
        l.lock();
        try{
            coda.addLast(Thread.currentThread());
            while(!possoEntrare()){
                possoEntrare.await();
            }
            coda.removeFirst();//ho rimosso me stesso
            possoEntrare.signalAll(); //risveglio il mio precedente
            curr_tur++;//entro nel gruppo;
            if(gruppoFormato()) gruppoFormato.signalAll();//risveglio la guida
            while(!TourInziato){
                tourIniziato.await();
            }
        }finally {
            l.unlock();
        }
    }

    private boolean FineTour(){
        return TourFinito;
    }


    @Override
    protected void turistaFine() throws InterruptedException {
        l.lock();
        try{
            while(!FineTour()){
                FineTour.await();
            }

        }finally {
            l.unlock();
        }

    }
}
