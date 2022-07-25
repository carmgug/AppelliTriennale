package RetiDiCalcolatori.SISOP.Traccia_TreAule;

import java.util.concurrent.TimeUnit;

public class Insegnante extends Thread{

    private String ID="Insegnante";
    private TreAule aula;


    public Insegnante(TreAule aula){
        this.aula=aula;
    }

    public void run(){
        try{
            while(true){
                aula.faiEntrare();
                TimeUnit.SECONDS.sleep(5);
            }
        }catch(InterruptedException e){}
    }



}
