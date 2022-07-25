package RetiDiCalcolatori.SISOP.Traccia_Laghetto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class laghettoLC extends laghetto{


    private Lock l=new ReentrantLock();
    private Condition possoEntrare=l.newCondition();


    private int[] curr_persone=new int[2];
    private int curr_pesci;

    public laghettoLC(int maxPesci, int minPesci, int P, int N) {
        super(maxPesci, minPesci, P, N);
        curr_pesci=maxPesci;
    }





    private boolean possoEntrare(int t){
        int op=1-t; //devo vedere se ci sono persone che devono effettuare l'operazione opposta;
        if(t==pesca && curr_pesci>minPesci && curr_persone[op]==0) return true;
        if(t==ripopolamento && maxPesci-curr_pesci>=10 && curr_persone[op]==0) return true;
        return false;
    }

    @Override
    protected void inizia(int t) throws InterruptedException {
        l.lock();
        try{
            while(!possoEntrare(t)){
                possoEntrare.await();
            }
            curr_persone[t]++;
            System.out.format("Ci sono %s pescatori e %s addetti nel lago e n_pesci:%s %n", curr_persone[pesca],curr_persone[ripopolamento],curr_pesci);

            if(t==pesca) curr_pesci--;
            else curr_pesci+=10;
        }finally {
            l.unlock();
        }
    }

    @Override
    protected void finisci(int t) throws InterruptedException {
        l.lock();
        try{
            System.out.format("E' uscito un %s%n", (t==pesca) ? "PESCATORE":"ADDETTO");
            curr_persone[t]--;
            System.out.format("Ci sono %s pescatori e %s addetti nel lago e n_pesci:%s %n", curr_persone[pesca],curr_persone[ripopolamento],curr_pesci);

            if(curr_persone[t]==0) possoEntrare.signalAll();
            //esco
        }finally {
            l.unlock();
        }

    }


    public static void main(String[] args){
        laghetto l=new laghettoLC(200, 50, 40, 5);
        pescatori[] pesc=new pescatori[l.P];
        addetto[] addetti=new addetto[l.N];
        for(int i=0;i<l.P;i++){
            pesc[i]=new pescatori(i+"", l);
            pesc[i].start();
        }
        for(int i=0;i<l.N;i++){
            addetti[i]=new addetto(i+l.P+"", l);
            addetti[i].start();
        }



    }

}
