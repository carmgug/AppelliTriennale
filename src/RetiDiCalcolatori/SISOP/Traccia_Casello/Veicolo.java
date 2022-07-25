package RetiDiCalcolatori.SISOP.Traccia_Casello;

import java.util.concurrent.TimeUnit;

public class Veicolo extends Thread {

    private final int minKM=50; private final int maxKM=100;
    private final int tempo=40,maxAttesa=3,minAttesa=6;

    private Casello casello;

    public Veicolo(Casello c){
        this.casello=c;
    }

    public void run(){
        try{
            int Km=Percorri();
            Aspetta(Km*tempo);

            //Scegli il Casello;
            int p=casello.getPorta();
            casello.Inserisci_V(this, p);

            Aspetta((int) Math.random()*(maxAttesa-minAttesa+1)+minAttesa);

            casello.Paga(this, p, Km);
            System.out.print("il veicolo"+this.getId()+" ha abbandonato la porta "+p+" ed e' uscito dall'autostrada");
        } catch (Exception e) {}

    }


    private int Percorri(){
        return (int) Math.random()*(maxKM-minKM+1)+minKM;
    }

    private void Aspetta(long secondi){
        try{
            TimeUnit.SECONDS.sleep(secondi);
        }catch (InterruptedException e){}
    }




}
