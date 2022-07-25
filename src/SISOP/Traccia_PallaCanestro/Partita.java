package SISOP.Traccia_PallaCanestro;

public abstract class Partita {

    protected static int Max_Giocatori=10;
    protected static int tiro=3;//passaggi da effettuare per tirare

    protected abstract boolean riceviPalla(int s) throws InterruptedException;
    protected abstract boolean lanciaPalla(int s, int p) throws InterruptedException;
    protected abstract int[] termina() throws InterruptedException;


}
