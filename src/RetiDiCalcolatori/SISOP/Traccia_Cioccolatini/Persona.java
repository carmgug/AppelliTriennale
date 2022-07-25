package RetiDiCalcolatori.SISOP.Traccia_Cioccolatini;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Persona extends Thread{

    private String ID;
    private Scatola scatola;

    private static int t_scelta=3;

    public Persona(String id,Scatola scatola){
        this.scatola=scatola;
        this.ID=id;

    }

    private void scegli() throws InterruptedException{
        TimeUnit.SECONDS.sleep(t_scelta);
    }
    private int scelta(LinkedList<Integer> c){
        return (int) (Math.random()*(c.size()-0))+0;
    }

    public void run(){
        try{
            LinkedList<Integer> manciata=scatola.get();
            scegli();
            int x=scelta(manciata);
            int rimosso=manciata.remove(x);
            System.out.format("La persona %s ha scelto il cioccolatino %s" +
                            " x%n",ID,rimosso);
            scatola.put(manciata);

        }catch(InterruptedException e){}


    }


}
