package RetiDiCalcolatori.SISOP.Traccia_Cioccolatini;

import java.util.LinkedList;

public abstract class Scatola {

    protected int N;
    protected int X;
    protected int Dolci_Per_Tipo;

    public Scatola(int N,int X){
        if(N%X!=0) throw new IllegalArgumentException();
        this.N=N;
        this.X=X;
        Dolci_Per_Tipo=N/X;
    }

    public abstract LinkedList<Integer> get() throws InterruptedException;
    public abstract void put(LinkedList<Integer> c) throws InterruptedException;






}
