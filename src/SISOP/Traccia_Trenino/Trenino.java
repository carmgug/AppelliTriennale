package SISOP.Traccia_Trenino;

public abstract class Trenino {

    protected static int Accesso=0;
    protected int[] cabine=new int[10];

    public Trenino(){
        for(int i=0;i<cabine.length;i++){
            cabine[i]=10;//posti_disponibili
        }
    }

    protected abstract void turSali() throws InterruptedException;
    protected abstract void turScendi() throws InterruptedException;
    protected abstract void impFaiScendere() throws InterruptedException;
    protected abstract void impFaiSalire() throws InterruptedException;
    protected abstract void impMuovi() throws InterruptedException;



}
