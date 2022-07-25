package RetiDiCalcolatori.SISOP.Traccia_Boccaccio;

public abstract class Boccaccio {


    protected static int Max_Caramelle=100;
    protected int Colori_Diversi;

    public Boccaccio(int Colori_Diversi){
        this.Colori_Diversi=Colori_Diversi;
    }

    protected abstract boolean prendi(int c) throws InterruptedException;
    protected abstract void piangi() throws InterruptedException;
    protected abstract void riempi() throws InterruptedException;





    


}
