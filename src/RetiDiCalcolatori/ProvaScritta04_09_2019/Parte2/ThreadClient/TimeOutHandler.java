package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte2.ThreadClient;

import java.util.concurrent.TimeUnit;

public class TimeOutHandler extends Thread{


    Client client;

    public TimeOutHandler(Client client){
        this.client=client;
    }

    public void run(){
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(client.isAlive()){
            client.interrupt();
        }
    }



}
