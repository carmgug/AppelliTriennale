package SISOP.Traccia_Tesoro;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Cercatore extends Thread{


    private String ID;
    private MappaDelTesoro mappa;
    private static int t_Max=5,t_Min=2;

    //due soluzioni
    //complessità temporale minore ma molto spazio
    //complessità temporale maggiore ma complessità spaziale minore

    //scegliamo la seconda
    LinkedList<int[]> celleVisitate;


    public Cercatore(String ID,MappaDelTesoro mappa){
        this.mappa=mappa;
        this.ID=ID;
        celleVisitate=new LinkedList<>();
    }

    private void SvolgiAzione() throws InterruptedException{
        int tempo=(int) (Math.random()*(t_Max-t_Min+1))+t_Min;
        TimeUnit.SECONDS.sleep(tempo);
    }


    private int[] scegliCella(){
        boolean trovato=false;
        int[] Cella=new int[2];
        while(!trovato) {
            Cella[0] = (int) (Math.random() * (mappa.getN() - 0 + 1)) + 0;
            Cella[1] = (int) (Math.random() * (mappa.getM() - 0 + 1)) + 0;
            boolean presente=false;
            for(int[] x:celleVisitate){
                if(x[0]==Cella[0] && x[1]==Cella[1]) presente=true;
            }
            if(!presente) trovato=true;
        }
        celleVisitate.add(Cella);
        return Cella;
    }

    public void run(){
        try{
            while(true){
                int[] scelta=scegliCella();
                boolean posso=mappa.iniziaRicerca(scelta[0],scelta[1]);
                if(!posso) break; //GIOCO TERMINATO;
                SvolgiAzione();
                boolean trovato=mappa.terminaRicerca(scelta[0],scelta[1]);
                if(trovato){
                    System.out.format("il cercatore[%s] grida 'HO VINTO' CELLA[%s][%s]%n ",ID,scelta[0],scelta[1] );
                    break;//gioco terminato;
                }
            }

        }catch (InterruptedException e){}

    }








}
