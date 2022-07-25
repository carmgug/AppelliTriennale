package RetiDiCalcolatori.SISOP.Traccia_Palazzo;

import javax.xml.crypto.dsig.SignatureMethod;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class PalazzoSem extends Palazzo{

    private Semaphore mutex=new Semaphore(1);
    private Semaphore[] possoLavorare=new Semaphore[3];


    private static int curr_piano;
    private boolean palazzoFinito;
    private LinkedList<Operai> lavoratori;

    public PalazzoSem(){
        super();
        curr_piano=0;
        palazzoFinito=false;
        lavoratori=new LinkedList<>();
        possoLavorare[0]=new Semaphore(1);
        possoLavorare[1]=new Semaphore(0);
        possoLavorare[2]=new Semaphore(0);
    }


    protected void addLavoratore() throws InterruptedException{
        mutex.acquire();
        if(!lavoratori.contains((Operai)Thread.currentThread())) lavoratori.add((Operai) Thread.currentThread());
        mutex.release();
    }

    protected boolean LavoroFinito() throws InterruptedException{
        mutex.acquire();
        boolean res=palazzoFinito;
        mutex.release();
        return res;
    }


    @Override
    protected void start(int t) throws InterruptedException{

        possoLavorare[t].acquire();

    }


    @Override
    protected void end(int t) throws InterruptedException {
        if(t==CONTROLLO){
            int val=verifica();
            if(val<=80){
                mutex.acquire();
                System.out.println("Si passa al prossimo piano");
                //devo costruire il prossimo piano?
                if(curr_piano==Max_Piani) {
                    palazzoFinito=true;
                    System.out.println("PALAZZO FINITO!!");
                }
                else {curr_piano++;possoLavorare[COSTRUZIONE].release();}

                mutex.release();
            }
            else if(val>=81 && val<=89){
                System.out.println("Dobbiamo ripulire il piano");

                possoLavorare[PULIZIA].release();
                //devo ripulire per bene il piano
            }
            else if(val>89){
                System.out.println("Il piano va rifatto!!");

                possoLavorare[COSTRUZIONE].release();
                //richiamo la squadra uno sullo stesso piano
                //che appena finisce si occuperà di chiamare la squadra 2;
            }
        }
        else{
            //se e' 0 allora chiamo 1;
            //se e' 1 allora chiamo 2;
            possoLavorare[t+1].release();
        }
    }


    public static void main(String[] args){
        Palazzo palazzo=new PalazzoSem();
        Operai[] operai=new Operai[3];
        for(int i=0;i<3;i++){
            operai[i]=new Operai(""+i, i, palazzo);
            operai[i].setDaemon(true);
            operai[i].start();
        }
        try {
            operai[2].join();
        }catch (InterruptedException e){}

        System.out.println("GIORNO DI LAVORO FINITO");




    }
}
