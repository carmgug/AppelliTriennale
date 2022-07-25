package SISOP.Traccia_Funivia;

public abstract class Funivia {

    protected static int piedi=0,bici=1;



    protected abstract void pilotaStart() throws InterruptedException;
    protected abstract void pilotaEnd() throws InterruptedException;
    protected abstract void turistaSali(int t) throws InterruptedException;
    protected abstract void turistaScendi(int t) throws InterruptedException;
}
