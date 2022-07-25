package SISOP.Traccia_Cementificio;

import SISOP.Tracce_Esame.Traccia_AziendaAgricola.Adetto;

import java.util.concurrent.TimeUnit;

public class Addetto extends Thread{

    private String ID;
    private Cementificio negozio;
    private static int tempo_rif=5;


    public Addetto(String ID,Cementificio negozio){
        this.ID=ID;
        this.negozio=negozio;
    }


    public void lavora(int tempo) throws InterruptedException{
        TimeUnit.SECONDS.sleep(5);
    }


    public void run(){
        try{
            while(true){
                System.out.format("L'addetto %s e' pronto per il rifornimento%n",ID);
                negozio.iniziaRiferimento();
                System.out.format("RIFORNIMENTO IN CORSO DA PARTE DI %s%n", ID);
                lavora(tempo_rif);
                negozio.terminaRiferimento();
                System.out.format("RIFORNIMENTO FINITO DA PARTE DI %s%n", ID);
            }
        }catch(InterruptedException e){}

    }
}
