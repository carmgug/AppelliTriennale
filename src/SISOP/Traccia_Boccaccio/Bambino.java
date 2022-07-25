package SISOP.Traccia_Boccaccio;

import java.util.concurrent.TimeUnit;

public class Bambino extends Thread{

    private String ID;
    private Boccaccio boccaccio;
    private int COLORE_PREFERITO;

    private static int Max_tempo=80,Min_tempo=40;

    public Bambino(String ID,Boccaccio boccaccio,int c){
        this.ID=ID;
        this.boccaccio=boccaccio;
        COLORE_PREFERITO=c;

    }

    private void SvolgiAzione() throws InterruptedException {
        int tempo=(int) (Math.random()*(Max_tempo-Min_tempo+1))+Min_tempo;
        TimeUnit.SECONDS.sleep(tempo);
    }


    public void run(){
        try{
            while(true){
                boolean Hopreso=boccaccio.prendi(COLORE_PREFERITO);
                if(!Hopreso) boccaccio.piangi();

            }

        }catch(InterruptedException e){}


    }


}
