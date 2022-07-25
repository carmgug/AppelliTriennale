package RetiDiCalcolatori.SISOP.Traccia_Piscina;

import java.util.concurrent.TimeUnit;

public class Persona extends Thread{

    private String ID;
    private Piscina piscina;

    private static int t_max_vasca=60,t_min_vasca=30;
    private static int t_doccia=30;

    public Persona(String ID,Piscina piscina){
        this.ID=ID;
        this.piscina=piscina;
    }


    private void svogliAzione(int tempo) throws InterruptedException{
        TimeUnit.MINUTES.sleep(tempo);
    }

    public void run(){
        try{

            int corsia=piscina.entra();
            System.out.format("Persona[%s] è entrato nella corsia %s%n", ID,corsia);
            int t_vasca=(int)(Math.random()*(t_max_vasca-t_min_vasca+1))+t_min_vasca;
            //perchè potrebbe svegliarsi e fare la doccia
            try {
                System.out.format("Persona[%s] nuota per %s%n", ID,t_vasca);

                svogliAzione(t_vasca);
            }catch(InterruptedException e){}
            System.out.format("Persona[%s] esce dalla corsia e fa la doccia %n", ID);
            piscina.esci(corsia);
            svogliAzione(t_doccia);
            System.out.format("Persona[%s] esce dalla piscina %n", ID);


        }catch(InterruptedException e){}
    }

}
