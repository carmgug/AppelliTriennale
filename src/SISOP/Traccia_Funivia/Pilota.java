package SISOP.Traccia_Funivia;

import java.util.concurrent.TimeUnit;

public class Pilota extends Thread{

    //costanti
    private static int t_salire=1;//min
    private static int t_scendere=1;//min
    //dati
    private String ID;
    private Funivia funivia; //luogo di lavoro


    public Pilota(String ID,Funivia funivia){
        this.funivia=funivia;
        this.ID=ID;
    }

    private void viaggia(int tempo) throws InterruptedException{
        TimeUnit.SECONDS.sleep(tempo);
    }

    public void run(){
        try{
            while(true){
                funivia.pilotaStart();
                viaggia(t_salire);
                funivia.pilotaEnd();
                viaggia(t_scendere);
            }
        }catch(InterruptedException e){}
    }
}
