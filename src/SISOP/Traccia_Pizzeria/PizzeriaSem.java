package SISOP.Traccia_Pizzeria;

import java.util.concurrent.Semaphore;

public class PizzeriaSem extends Pizzeria{

    private Semaphore mutex=new Semaphore(1);//ci permette di modificare dati protetti
    private Semaphore possoSedermi=new Semaphore(Max_Clienti);//ci consente di far occupare i Max posti del tavolo
    private Semaphore possoPreparare=new Semaphore(0);//per far preparare la pizza appena si siede l'ultimo cliente
    private Semaphore possoMangiare=new Semaphore(0);//per far mangiare i clienti appena la pizza è pronta


    public PizzeriaSem(){
        super();
    }


    protected void miSiedo() throws InterruptedException{
        possoSedermi.acquire();
        mutex.acquire();
        curr_clienti++;
        if(curr_clienti==Max_Clienti) possoPreparare.release();
        System.out.format("Il cliente[%s] si è seduto a tavola %n", ((Cliente)Thread.currentThread()).getId());

        mutex.release();
    }

    @Override
    protected void mangiaPizza() throws InterruptedException {
        possoMangiare.acquire();
    }

    @Override
    protected void pizzaMangiata() throws InterruptedException{
        //solo l'ultimo notifica che il tavolo è libero e può essere occupato
        mutex.acquire();
        curr_clienti--;
        if(curr_clienti==0) possoSedermi.release(5);
        mutex.release();
    }

    @Override
    protected void preparaPizza() throws InterruptedException {
        possoPreparare.acquire();
        mutex.acquire();
        System.out.format("Il pizzaiolo[%s] ha iniziato a preparare la maxi-pizza%n", Thread.currentThread().getId());
        mutex.release();

    }

    @Override
    protected void pizzaPronta() {
        possoMangiare.release(5);
        //i clienti possono mangiare

    }


    public static void main(String[] args) {
        Pizzeria pizzeria=new PizzeriaSem();
        Pizzaiolo pizzaiolo=new Pizzaiolo("pizzaiolo", pizzeria);
        pizzaiolo.setDaemon(true);
        pizzaiolo.start();
        Cliente[] clienti=new Cliente[15];
        for(int i=0;i<15;i++){
            clienti[i]=new Cliente(Integer.toString(i), pizzeria);
            clienti[i].start();
        }

        for(int i=0;i<15;i++) {
            try {
                clienti[i].join();
            }catch(InterruptedException e){}
        }



    }
}
