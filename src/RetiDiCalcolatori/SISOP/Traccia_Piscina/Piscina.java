package RetiDiCalcolatori.SISOP.Traccia_Piscina;

public abstract class Piscina {


    protected static int corsie=5;


    public abstract int entra() throws InterruptedException;
    public abstract void esci(int c) throws InterruptedException;

    public abstract void apri() throws InterruptedException;
    public abstract void chiudi() throws InterruptedException;

}
