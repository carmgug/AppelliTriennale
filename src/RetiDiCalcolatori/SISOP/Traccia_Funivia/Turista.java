package RetiDiCalcolatori.SISOP.Traccia_Funivia;

public class Turista extends Thread{
    private String ID;
    private int tipo;
    private Funivia funivia;

    public Turista(String ID,int tipo,Funivia funivia){
        this.funivia=funivia;
        this.ID=ID;
        this.tipo=tipo;
    }

    public String getID(){
        return ID;
    }

    public int getTipo(){
        return tipo;
    }

    public void run(){
        try{
            funivia.turistaSali(tipo);
            funivia.turistaScendi(tipo);
        }catch (InterruptedException e){}
    }


}
