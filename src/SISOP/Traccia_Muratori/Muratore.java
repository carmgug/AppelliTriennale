package SISOP.Traccia_Muratori;

import java.util.concurrent.TimeUnit;

public class Muratore extends Thread{


    private static int t_mattoni=500;//ms
    private static int t_cemento=700;//msd

    private static int t_parete=1000;//ms
    private static int t_riposo=5;//secondi

    private int tipo;
    private Casa casa;
    private String myID;

    public Muratore(int t,String myID,Casa casa){
        tipo=t;
        this.casa=casa;
        this.myID=myID;
    }

    private void lavora(int tempo) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(tempo);
    }

    private void prepara_materiale(int t) throws InterruptedException {
        if(tipo==casa.mattoni) lavora(t_mattoni);
        else lavora(t_cemento);
    }

    private void riposa(int t) throws InterruptedException{
        TimeUnit.SECONDS.sleep(t);
    }

    public void run(){
        try{
            while(true){
                System.out.format("Il muratore[%s] prepara %s%n",myID,
                        (tipo==casa.mattoni ? "i MATTONI":"il CEMENTO"));

                prepara_materiale(tipo);
                if(!casa.inizia(tipo)) {
                    System.out.format("Il muratore[%s] torna a casa"
                            +" CASAFINITA%n",
                            myID);
                    break;
                }
                lavora(t_parete);
                casa.termina(tipo);
                System.out.format("Il muratore[%s] ha terminato di %s e si riposa per %s secondi%n",myID,
                        (tipo==casa.mattoni ? "di mettere MATTONI":"di mettere il CEMENTO"),t_riposo);
                riposa(t_riposo);
            }

        }catch(InterruptedException e){}

    }

}
