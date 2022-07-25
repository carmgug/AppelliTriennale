package SISOP.Traccia_Pizzeria;

import java.util.concurrent.TimeUnit;

public class Pizzaiolo extends Thread{

    private String ID;
    private Pizzeria locale;

    private static int t_preparazione=5;

    public Pizzaiolo(String ID,Pizzeria locale){
        this.ID=ID;
        this.locale=locale;
    }

    private void prepara(int tempo) throws InterruptedException{
        TimeUnit.SECONDS.sleep(tempo);
    }

    public void run(){
        try{
            while (true) {
                locale.preparaPizza();
                //il pizzaiolo prepara la pizza
                prepara(t_preparazione);
                //ha finito di preparare e serve la pizza al tavolo
                System.out.format("Il pizzaiolo[%s] ha finito di preparare la maxi-pizza e la serve%n", Thread.currentThread().getId());

                locale.pizzaPronta();
            }
        }catch(InterruptedException e){}
    }


}
