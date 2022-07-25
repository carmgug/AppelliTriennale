package SISOP.Traccia_BarMod;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BarSemafori extends Bar{

    private Semaphore mutex=new Semaphore(1);
    private Semaphore cassa=new Semaphore(1,true);
    private Semaphore bancone=new Semaphore(4,true);


    private int fila_cassa=0;
    private int fila_bancone=0;


    @Override
    protected int scegli() throws InterruptedException {
        mutex.acquire();
        int id=-1;
        if(fila_cassa==0) {id=super.PAGARE;fila_cassa++;}
        else if(fila_bancone<4) {id=super.BERE;fila_bancone++;}
        else if(fila_cassa<=(fila_bancone-4)) {id=super.PAGARE;fila_cassa++;}
        else {id=super.BERE;fila_bancone++;}
        mutex.release();
        return id;
    }

    @Override
    protected void inizia(int i) throws InterruptedException {
        if(i==super.BERE) bevi();
        else paga();
    }

    private int tempo(int max,int min){
        return (int) (Math.random()*(max-min+1))+min;
    }

    private void paga() throws InterruptedException {
        cassa.acquire();
        TimeUnit.SECONDS.sleep(tempo(10,5));
        mutex.acquire();
        fila_cassa--;
        mutex.release();
        cassa.release();
    }

    private void bevi() throws InterruptedException {
        bancone.acquire();
        System.out.println(Thread.currentThread().getState());
        TimeUnit.SECONDS.sleep(tempo(40,20));
        mutex.acquire();
        fila_bancone--;
        mutex.release();
        bancone.release();

    }

    @Override
    protected void finisci(int i) throws InterruptedException {
        if(i==super.BERE) paga();
        else bevi();
    }
}
