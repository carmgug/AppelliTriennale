package SISOP.Traccia_PallaCanestro;

import java.util.concurrent.TimeUnit;

public class Giocatore extends Thread{


    private String ID;
    private Partita campo;
    private int squadra;
    private int p;

    private static int Max_t_palla=5,Min_t_palla=1;
    private static int Max_P=60,Min_P=30;

    public Giocatore(String ID,Partita campo,int squadra){
        this.campo=campo;
        this.ID=ID;
        this.squadra=squadra;
        p=((int) Math.random()*(Max_P-Min_P+1))+Min_P;
    }

    private void svolgiAzione() throws InterruptedException{
        int tempo=(int) (Math.random()*(Max_t_palla-Min_t_palla+1))+Min_t_palla;
        TimeUnit.SECONDS.sleep(tempo);
    }

    public void run(){
        try{
            boolean partita=true;
            while(partita){
                partita= campo.riceviPalla(squadra);
                System.out.format("il GIOCATORE[%s] ha RICEVUTO la palla SQUADRA:%s%n",ID,squadra);
                svolgiAzione();
                System.out.format("il GIOCATORE[%s] ha LANCIATO la palla%n",ID);
                partita=campo.lanciaPalla(squadra,p);
                svolgiAzione();

            }
        }catch(InterruptedException e){}
    }

}
