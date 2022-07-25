package SISOP.Traccia_TreAule;

import java.util.concurrent.TimeUnit;

public class Studente extends Thread {

    private String ID;
    private TreAule aula;
    private final static int t_max=60,t_min=45;


    public Studente(String ID,TreAule aula){
        this.ID=ID;
        this.aula=aula;
    }

    private void SvolgiCompito() throws InterruptedException {
        int tempo=(int) (Math.random()*(t_max-t_min+1))+t_min;
        TimeUnit.MINUTES.sleep(tempo);
    }



    public void run(){
        try{
            int c=aula.entra();
            SvolgiCompito();
            aula.lascia(c);
        }catch(InterruptedException e){}
    }


}
