package SISOP.Traccia_Giugno_2021;

public class Turista extends Thread{




    private TourFirenze tour;

    public Turista(TourFirenze tour){
        this.tour=tour;
    }

    public void run(){
        try{
            tour.turistaInizia();
            tour.turistaFine();

        }catch (InterruptedException e){}
    }



}
