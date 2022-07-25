package RetiDiCalcolatori.SISOP.Traccia_Boccaccio;

public class Addetto extends Thread {


    private String ID;
    private Boccaccio boccaccio;

    public Addetto(String ID,Boccaccio boccaccio){
        this.ID=ID;
        this.boccaccio=boccaccio;
    }


    public void run(){
        try{
            while(true){
                boccaccio.riempi();
            }
        }catch(InterruptedException e){}
    }


}
