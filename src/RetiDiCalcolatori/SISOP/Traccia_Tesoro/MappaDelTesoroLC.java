package RetiDiCalcolatori.SISOP.Traccia_Tesoro;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class MappaDelTesoroLC extends MappaDelTesoro{


    private Lock l=new ReentrantLock(true);
    private Condition[][] CellaOccupata;


    private boolean[][] Celle;//false significa che nessuno è dentro
    //true significa che c'è una persona
    private LinkedList[][] coda;
    private boolean InCorso;

    public MappaDelTesoroLC(int N, int M,int X,int Y) {
        super(N, M);
        Celle=new boolean[N][M]; //matrice inizializzata a false;
        mappa[X][Y]=TESORO;
        coda=new LinkedList[N][M];
        InCorso=true;

        CellaOccupata =new Condition[N][M];
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++) {
                CellaOccupata[i][j] = l.newCondition();
                coda[i][j]=new LinkedList<Cercatore>();
            }
    }


    private boolean cellaOccupata(int X,int Y){
        return Celle[X][Y]==true;
    }
    private boolean MioTurno(int X,int Y){
        Cercatore io=(Cercatore) Thread.currentThread();
        return io==coda[X][Y].getFirst();
    }


    @Override
    protected boolean iniziaRicerca(int x, int y) throws InterruptedException {
        l.lock();
        boolean res=false;
        try{
            coda[x][y].add((Cercatore)Thread.currentThread());
            while(cellaOccupata(x, y) || !MioTurno(x, y)){
                CellaOccupata[x][y].await();
            }
            coda[x][y].removeFirst();
            res=InCorso;
        }finally {
            l.unlock();
        }

        return res;
    }

    @Override
    protected boolean terminaRicerca(int x, int y) throws InterruptedException {
        l.lock();
        boolean res=false;
        try{
            int elem=mappa[x][y];
            if(elem==TESORO){
                InCorso=false;
                res=true;
            }
            else CellaOccupata[x][y].signalAll();
        }finally {
            l.unlock();
        }
        return res;
    }
}
