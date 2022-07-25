package RetiDiCalcolatori.SISOP.Traccia_AziendaAgricola;

import java.util.concurrent.TimeUnit;

public class Adetto extends Thread{

    private AziendaAgricola Negozio;

    public Adetto(AziendaAgricola Negozio){
        this.Negozio=Negozio;
    }

    public void run(){
        try{
            while(Negozio.SonoPresentiClienti()) {
                Negozio.aggiungiSacchi();
                System.out.format("BLBLBLBLBLBLBLBLB%s",4);

                System.out.println("Un Adetto ha caricato" + Negozio.num_sacchi +
                        " nel magazzino");
                TimeUnit.SECONDS.sleep(10);
            }
        }catch (InterruptedException e){}
    }

}
