package RetiDiCalcolatori.SISOP.Traccia_BarMod;

public abstract class Bar {


    protected final static int PAGARE=0;
    protected final static int BERE=1;
    protected int guadagno=0;
    protected final static int COSTO=3;

    protected abstract int scegli() throws InterruptedException;

    protected abstract void inizia(int i) throws InterruptedException;
    protected abstract void finisci(int i) throws InterruptedException;



}
