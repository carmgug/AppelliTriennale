package RetiDiCalcolatori.SISOP.Traccia_Trenino;

public class Turista extends Thread{

    private String ID;
    private Trenino treno;


    public Turista(String ID,Trenino treno){
        this.ID=ID;
        this.treno=treno;
    }

    public void run(){
        try{
            treno.turSali();
            treno.turScendi();

        }catch (InterruptedException e){}
    }

}
