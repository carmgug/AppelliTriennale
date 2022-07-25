package RetiDiCalcolatori.SISOP.Traccia_Pizzeria;

import java.util.concurrent.TimeUnit;

public class Cliente extends Thread{

    private String ID;
    private Pizzeria locale;



    //tempo massimo/minimo per mangiare la pizza

    private static int Max_t=10,Min_t=5;

    public Cliente(String ID,Pizzeria locale){
        this.ID=ID;
        this.locale=locale;
    }

    public String getID(){
        return ID;
    }

    private static void mangia(int tempo) throws InterruptedException{
        TimeUnit.SECONDS.sleep(tempo);
    }



    public void run(){
        try{
            locale.miSiedo();
            locale.mangiaPizza();
            int tempo=(int) (Math.random()*(Max_t-Min_t+1))+Min_t;
            System.out.format("Il cliente[%s] ha iniziato a mangiare e impiegherà %s%n", ID,tempo);
            mangia(tempo);
            locale.pizzaMangiata();

        }catch(InterruptedException e){}
    }
}
