package RetiDiCalcolatori.SISOP.Traccia_Dischi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ControlloreLC extends Controllore{

    private Lock lc=new ReentrantLock();
    private Condition Occupato=lc.newCondition();


    private int[] Memoria_Occupata;

    public ControlloreLC(int n_sharedDisk,int Max_M){
        super(n_sharedDisk,Max_M);
        Memoria_Occupata=new int[n_sharedDisk];

    }

    private boolean Occupati(int a,int b){
        return (Memoria_Occupata[a] <Max_Memory[a] && Memoria_Occupata[b]<Max_Memory[b]);
    }

    @Override
    protected void allocaDischi(int a, int b) throws InterruptedException {
        lc.lock();
        try{
            while(Occupati(a,b)){
                Occupato.await();
            }
            Memoria_Occupata[a]++;
            Memoria_Occupata[b]++;
        }finally {
            lc.unlock();
        }
    }

    @Override
    protected void rilasciaDischi(int a, int b) {
        lc.lock();
        try{

            Memoria_Occupata[a]--;
            Memoria_Occupata[b]--;
            Occupato.signalAll();

        }finally {
            lc.unlock();
        }
    }
}
