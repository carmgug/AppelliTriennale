package SISOP.Traccia_Giugno_2021;

import java.util.concurrent.TimeUnit;

public class Guida extends Thread{


    private TourFirenze tour;
    private final static int t_descrivi=15;//minuti
    private final static int t_Max1=50,t_Min1=40;
    private final static int t_Maxp=20,t_Minp=10;
    private final static int Spiega=0,Pausa=1,Descrivi=2;


    public Guida(TourFirenze tour){
        this.tour=tour;
    }

    private void SvolgiAzione(int Azione) throws InterruptedException {
        if(Azione==Spiega){
            int tempo=(int) (Math.random()*(t_Max1-t_Min1+1))+t_Min1;
            TimeUnit.MINUTES.sleep(tempo);
        }
        else if(Azione==Pausa){
            int tempo=(int) (Math.random()*(t_Maxp-t_Minp+1))+t_Minp;
            TimeUnit.MINUTES.sleep(tempo);
        }
        else if(Azione==Descrivi){
            TimeUnit.MINUTES.sleep(t_descrivi);
        }
    }


    public void run(){
        try {
            while(true){
                tour.attendiFormazioneGruppo();
                SvolgiAzione(Descrivi);
                tour.visitaInizia();
                SvolgiAzione(Spiega);
                SvolgiAzione(Pausa);
                SvolgiAzione(Spiega);
                tour.turistaFine();
                TimeUnit.MINUTES.sleep(60); //RIPOSA
            }
        }catch (InterruptedException e){}

    }





}
