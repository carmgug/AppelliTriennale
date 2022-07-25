package RetiDiCalcolatori.SISOP.Traccia_Laghetto;

public abstract class laghetto {

    protected static int maxPesci,minPesci;
    protected static int pesca=0,ripopolamento=1;
    protected int P,N;

    public laghetto(int maxPesci,int minPesci,int P,int N){
        this.maxPesci=maxPesci;
        this.minPesci=minPesci;
        this.P=P;
        this.N=N;

    }





    protected abstract void inizia(int t) throws InterruptedException;
    protected abstract void finisci(int t) throws InterruptedException;



}
