package RetiDiCalcolatori.SISOP.Traccia_Tesoro;

import java.util.concurrent.Semaphore;

public class MappaDelTesoroSem extends MappaDelTesoro{


    private Semaphore mutex=new Semaphore(1);
    private Semaphore[][] Accesso;

    private boolean InCorso;


    public MappaDelTesoroSem(int N,int M,int X,int Y){
        super(N,M);
        mappa[X][Y]=TESORO;
        Accesso=new Semaphore[N][M];
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++)
                Accesso[i][j]=new Semaphore(1,true);
        InCorso=true;
    }



    @Override
    protected boolean iniziaRicerca(int x, int y) throws InterruptedException {
        Accesso[x][y].acquire();
        mutex.acquire();
        boolean check_1=InCorso;
        mutex.release();
        return check_1;

    }

    @Override
    protected boolean terminaRicerca(int x, int y) throws InterruptedException {
        //accedo al dato della cella x y acceduta da un solo processo per volta
        //grazie al semaforo in iniziaRicerca
        int tesoro=mappa[x][y];
        if(tesoro!=TESORO) return false;
        else{
            mutex.acquire();
            InCorso=false;
            mutex.release();
            return true;//ho vinto
        }
    }
}
