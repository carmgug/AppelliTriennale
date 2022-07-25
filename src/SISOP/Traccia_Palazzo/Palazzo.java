package SISOP.Traccia_Palazzo;

public abstract class Palazzo {




    protected static int Max_Piani=50;
    protected static int COSTRUZIONE=0;
    protected static int PULIZIA=1;
    protected static int CONTROLLO=2;
    private static int Min_ver=1,Max_ver=100;


    protected abstract void start(int t) throws InterruptedException;
    protected abstract void end(int t) throws InterruptedException;


    protected abstract boolean LavoroFinito() throws InterruptedException;

    protected int verifica(){
        return (int)(Math.random()*(Max_ver-Min_ver+1))+Min_ver;
    }

}
