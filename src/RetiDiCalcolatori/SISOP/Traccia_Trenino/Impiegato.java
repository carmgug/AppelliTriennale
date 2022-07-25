package RetiDiCalcolatori.SISOP.Traccia_Trenino;

import java.util.concurrent.TimeUnit;

public class Impiegato extends Thread {


    private String ID;
    private Trenino treno;

    private static int t_scatto=30;

    public Impiegato(String ID,Trenino treno){
        this.ID=ID;
        this.treno=treno;
    }

    private void scatto(int tempo) throws InterruptedException{
        TimeUnit.SECONDS.sleep(tempo);
    }

    public void run(){
        try{
            while(true){
                treno.impFaiScendere();
                //fai salire solo se ci sono almeno 10 persone
                treno.impFaiSalire();
                scatto(t_scatto);
                treno.impMuovi();
            }

        }catch(InterruptedException e){}



    }
}
