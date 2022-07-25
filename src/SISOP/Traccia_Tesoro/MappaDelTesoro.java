package SISOP.Traccia_Tesoro;

public abstract class MappaDelTesoro {

    protected static int TESORO=69;
    protected int[][] mappa;
    protected int N,M;


    public MappaDelTesoro(int N,int M){
        mappa=new int[N][M];
        this.N=N;
        this.M=M;
    }

    public int getN(){return N;}
    public int getM(){return M;}

    protected abstract boolean iniziaRicerca(int x,int y) throws InterruptedException;
    protected abstract boolean terminaRicerca(int x,int y) throws InterruptedException;



}
