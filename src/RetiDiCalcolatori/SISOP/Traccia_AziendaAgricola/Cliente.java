package RetiDiCalcolatori.SISOP.Traccia_AziendaAgricola;

import java.util.concurrent.TimeUnit;

public class Cliente extends Thread{
    private String myID;
    private AziendaAgricola Negozio;
    private int min=1,max=10;
    public Cliente(String ID,AziendaAgricola Negozio){
        myID=ID;
        this.Negozio=Negozio;
    }

    private int generaNumero(){
        return (int) (Math.random()*(max-min+1))+min;
    }

    public void run(){
        try{
            int x=generaNumero();
            Negozio.acquista(x);
            System.out.println("Il Cliente "+myID+" ha acquistato "+x+" sacchi"
                +" spendendo "+x*Negozio.costo);
            TimeUnit.MILLISECONDS.sleep(20);
            Negozio.prelevaSacchi(x);
            TimeUnit.MILLISECONDS.sleep(20);
            System.out.println("Il Cliente "+myID+" ha prelevato tutti i "
                    +x+" sacchi dal magazzino ed esce dal negozio");
        }catch(InterruptedException e){}

    }


}
