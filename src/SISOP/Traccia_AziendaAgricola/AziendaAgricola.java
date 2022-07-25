package SISOP.Traccia_AziendaAgricola;

public abstract class AziendaAgricola {

    protected final static int num_sacchi=200;
    protected final static double costo=3;
    protected double guadagno=0;


    public abstract void acquista(int num) throws InterruptedException;

    public abstract void prelevaSacchi(int num) throws InterruptedException;

    public abstract void aggiungiSacchi() throws InterruptedException;

    public abstract boolean SonoPresentiClienti();





}
