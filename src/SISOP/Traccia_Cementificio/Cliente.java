package SISOP.Traccia_Cementificio;

import java.util.concurrent.TimeUnit;

public class Cliente extends Thread{


    private String ID;
    private Cementificio negozio;

    private static int MAX_sacchi=30,MIN_sacchi=10;
    private static int trasporta=1;

    public Cliente(String ID,Cementificio negozio){
        this.ID=ID;
        this.negozio=negozio;

    }

    private int scegli(){
        return (int) (Math.random()*(MAX_sacchi-MIN_sacchi+1))+MIN_sacchi;
    }

    private void attendi(int tempo) throws InterruptedException {
        TimeUnit.SECONDS.sleep(tempo);
    }

    public void run(){
        try{
            int n=scegli();
            negozio.entra();
            System.out.format("il cliente %s ha scelto %s sacco/sacchi " +
                    "ed entra nel negozio%n",ID,n);
            int curr=0;
            while(curr<n){
                negozio.preleva();
                //System.out.format("il cliente %s ha PRELEVATO un sacco e lo " +
                        //"sta trasportando%n",ID);
                attendi(trasporta);
                curr++;
                //System.out.format("il cliente %s ha FINITO di trasportare un sacco%n", ID);
            }
            System.out.format("il cliente %s ha FINITO di trasportare tutti i sacchi%n", ID);
            negozio.esci();


        }catch(InterruptedException e){}

    }
}
