package SISOP.Traccia_Piscina;

import java.util.concurrent.TimeUnit;

public class Istruttore extends Thread{

    private Piscina piscina;
    private String id;
    public Istruttore(String id,Piscina piscina){
        this.id=id;
        this.piscina=piscina;
    }

    public void run(){
        try{
            while(true) {
                piscina.apri();
                TimeUnit.SECONDS.sleep(16);
                piscina.chiudi();
                TimeUnit.SECONDS.sleep(18);
            }

        }catch(InterruptedException e){}
    }
}
