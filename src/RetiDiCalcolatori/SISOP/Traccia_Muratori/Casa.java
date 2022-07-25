package RetiDiCalcolatori.SISOP.Traccia_Muratori;

public abstract class Casa {

    protected int N;
    protected static int n_pareti=4;
    protected static int mattoni=0,cemento=1;


    protected abstract boolean inizia(int t) throws InterruptedException;

    protected abstract void termina(int t);




}
