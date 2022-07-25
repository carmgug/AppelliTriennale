package SISOP.Traccia_Firenze;

public abstract class TourFirenze {


    protected int val_tur=20;


    protected abstract void attendiFormazioneGruppo() throws InterruptedException;
    protected abstract void visitaInizia();
    protected abstract void visitaFine();
    protected abstract void turistaInizia() throws InterruptedException;
    protected abstract void turistaFine() throws InterruptedException;




}
