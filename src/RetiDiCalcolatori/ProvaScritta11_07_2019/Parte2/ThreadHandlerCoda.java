package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadHandlerCoda extends Thread{

    private Porto porto;
    private Random random=new Random();
    private final static int time=60;

    public void run(){
        while(true){
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int id_banchina=porto.gestisciNaveCoda();
            if(id_banchina!=-1){
                int id_Nave=porto.getIdNave(id_banchina);
                new ThreadHandlerScarico(porto,id_banchina,id_Nave);
            }
        }

    }



}
