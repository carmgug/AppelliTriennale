package RetiDiCalcolatori.SISOP.Traccia_Cementificio;

public abstract class Cementificio {

    protected int N;
    protected int P;


    public  abstract void entra() throws InterruptedException;
    public  abstract void esci();
    public abstract void preleva() throws InterruptedException;

    public abstract void iniziaRiferimento() throws InterruptedException;
    public abstract void terminaRiferimento() throws InterruptedException;



}
