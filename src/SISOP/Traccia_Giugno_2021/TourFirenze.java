package SISOP.Traccia_Giugno_2021;

public abstract class TourFirenze {


    protected final static int Turisti_suff=20;





    protected abstract void attendiFormazioneGruppo() throws InterruptedException;
    protected abstract void visitaInizia();
    protected abstract void visitaFine();
    protected abstract void turistaInizia() throws InterruptedException;
    protected abstract void turistaFine() throws InterruptedException;



}
