package RetiDiCalcolatori.SISOP.Traccia_Laghetto;

import java.util.concurrent.TimeUnit;

public class addetto extends Thread{

    private String ID;
    private laghetto lago;

    private static int t_Max=1000,t_Min=600;//ms


    public addetto(String ID,laghetto lago){
        this.ID=ID;
        this.lago=lago;
    }

    private void svolgi() throws InterruptedException{
        int tempo=(int) (Math.random()*(t_Max-t_Min+1))+t_Min;
        TimeUnit.MILLISECONDS.sleep(tempo);
    }

    public void run(){
        try{
            while(true) {
                lago.inizia(lago.ripopolamento);
                svolgi();
                lago.finisci(lago.ripopolamento);
                TimeUnit.SECONDS.sleep(3); //allontanamento
            }
        }catch (InterruptedException e){

        }
    }


}
