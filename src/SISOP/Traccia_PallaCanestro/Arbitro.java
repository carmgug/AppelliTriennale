package SISOP.Traccia_PallaCanestro;

import java.util.concurrent.TimeUnit;

public class Arbitro extends Thread{

    private String ID="ARBITRO";
    private Partita campo;
    private static int t_PARTITA=40;


    public Arbitro(Partita campo){
        this.campo=campo;
    }

    public void run(){
        try{
            TimeUnit.SECONDS.sleep(t_PARTITA);
            int[] res=campo.termina();
            System.out.format("Squadra_0[%s] : Squadra_1[%s]",res[0],res[1]);
        }catch(InterruptedException e){}
    }


}
