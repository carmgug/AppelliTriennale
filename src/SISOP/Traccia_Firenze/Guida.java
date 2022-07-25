package SISOP.Traccia_Firenze;

import java.util.concurrent.TimeUnit;

public class Guida extends Thread {

    private String ID;
    private TourFirenze tourFirenze;

    private static int Max_t = 50, Min_t = 40;
    private static int Max_pausa = 10, Min_pausa = 20;
    private static int t_spiegazione=15;


    public Guida(String ID, TourFirenze tuorFirenze) {
        this.ID = ID;
        this.tourFirenze = tuorFirenze;
    }

    private void Svolgi(int tempo) throws InterruptedException{
        TimeUnit.MINUTES.sleep(tempo);
    }

    private int getTempo(int Max_Value,int Min_Value){
        int tempo=(int)(Math.random()*(Max_Value-Min_Value+1))+Min_Value;
        return tempo;
    }

    public void run(){
        try{
            while(true){
                tourFirenze.attendiFormazioneGruppo();
                Svolgi(t_spiegazione); //
                tourFirenze.visitaInizia();
                //prima-parte
                Svolgi(getTempo(Max_t, Min_t));
                //pausa
                Svolgi(getTempo(Max_pausa, Min_pausa));
                //seconda-parte
                Svolgi(getTempo(Max_t, Max_t));
                tourFirenze.turistaFine();
            }

        }catch(InterruptedException e){

        }
    }

}
