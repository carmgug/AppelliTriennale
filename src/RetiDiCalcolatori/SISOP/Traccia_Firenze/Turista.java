package RetiDiCalcolatori.SISOP.Traccia_Firenze;

public class Turista extends Thread{

    private String ID;
    private TourFirenze tourFirenze;


    public Turista(String ID,TourFirenze tourFirenze){
        this.ID=ID;
        this.tourFirenze=tourFirenze;
    }

    public void run() {
        try{
            tourFirenze.turistaInizia();
            tourFirenze.turistaFine();

        }catch(InterruptedException e){}


    }

}
