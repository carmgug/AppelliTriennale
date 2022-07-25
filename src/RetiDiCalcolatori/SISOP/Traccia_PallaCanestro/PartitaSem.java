package RetiDiCalcolatori.SISOP.Traccia_PallaCanestro;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PartitaSem extends Partita{

    private Semaphore mutex=new Semaphore(1);
    private Semaphore palla[] =new Semaphore[2];
    //la palla la ha

    private Random random=new Random();

    private int curr_passaggi=0;
    private int[] curr_punteggio;
    private boolean partitaInCorso;

    public PartitaSem(){
        int squadra= random.nextInt(2);
        palla[0]=new Semaphore(0);
        palla[1]=new Semaphore(0);
        palla[squadra].release();
        partitaInCorso=true;
        curr_punteggio=new int[2];//punteggio
    }


    @Override
    protected boolean riceviPalla(int s) throws InterruptedException {
        //tento di prendere la palla
        boolean res=false;
        palla[s].acquire();
        mutex.acquire();
        res=partitaInCorso;
        mutex.release();
        return res;

    }

    @Override
    protected boolean lanciaPalla(int s, int p) throws InterruptedException {
        int chance= random.nextInt(100);
        mutex.acquire();
        if(chance<p){
            if(curr_passaggi>=tiro){
                curr_passaggi=0;
                curr_punteggio[s]++;
            }
            else curr_passaggi++;
            palla[s].release();//la prende un giocatore casuale
        }
        else{
            //in caso contrario la palla la prende la squadra avversaria
            int cambioSquadra=1-s;
            curr_passaggi=0;
            palla[cambioSquadra].release();
        }
        boolean res=partitaInCorso;
        mutex.release();
        return res;

    }

    @Override
    protected int[] termina() throws InterruptedException {
        int[] res=new int[2];
        mutex.acquire();
        res[0]=curr_punteggio[0];
        res[1]=curr_punteggio[1];
        //ho salvato i risultati e termino la partita;
        partitaInCorso=false;
        mutex.release();
        return res;
    }



    public static void main(String[] args){
        System.out.println("PARTITA INIZIATA");
        Partita campo=new PartitaSem();
        Giocatore[] giocatori=new Giocatore[campo.Max_Giocatori];
        Arbitro arbitro=new Arbitro(campo);
        arbitro.start();
        for(int i=0;i<campo.Max_Giocatori;i++){
            giocatori[i]=new Giocatore(i+"", campo, i%2);
            giocatori[i].setDaemon(true);
            giocatori[i].start();
        }
        try{
            arbitro.join();
        }catch(InterruptedException e){}
        System.out.println("PARTITA FINITA");



    }



}
