package RetiDiCalcolatori.SISOP.Traccia_Laghetto;

import java.util.concurrent.TimeUnit;

public class pescatori extends Thread{


    private String ID;
    private laghetto lago;

    private static int t_Max=3000,t_Min=2000;//ms


    public pescatori(String ID,laghetto lago){
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
                lago.inizia(lago.pesca);
                svolgi();
                lago.finisci(lago.pesca);
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e){

        }
    }

}
