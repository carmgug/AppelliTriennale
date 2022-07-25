package RetiDiCalcolatori.SISOP.Traccia_Pizzeria;

import java.util.concurrent.locks.*;

public class PizzeriaLC extends Pizzeria{



    private Lock l=new ReentrantLock();
    private Condition possoMangiare=l.newCondition();
    private Condition possoSedermi=l.newCondition();
    private Condition possoCucinare=l.newCondition();


    private boolean PizzaServita =false;

    private boolean TavoloLibero(){
        return (curr_clienti<Max_Clienti);
    }
    private boolean TavoloPieno(){
        return curr_clienti==Max_Clienti;
    }


    @Override
    protected void miSiedo() throws InterruptedException {
        l.lock();
        try{
            while(!TavoloLibero()){
                possoSedermi.await();
            }
            curr_clienti++;
            System.out.format("Il cliente[%s] si è seduto al tavolo%n", ((Cliente)Thread.currentThread()).getID());
            if(curr_clienti==Max_Clienti) possoCucinare.signal();//il pizzaiolo è uno
        }finally {
            l.unlock();
        }
    }

    @Override
    protected void mangiaPizza() throws InterruptedException {
        l.lock();
        try{
            while(!PizzaServita){
                possoMangiare.await();
            }
        }finally {
            l.unlock();
        }

    }

    @Override
    protected void pizzaMangiata() throws InterruptedException {
        l.lock();
        try{
            curr_clienti--;
            if(curr_clienti==0) {possoSedermi.signalAll();
                PizzaServita =false;}
        }finally {
            l.unlock();
        }
    }

    @Override
    protected void preparaPizza() throws InterruptedException {
        l.lock();
        try{
            while(!TavoloPieno() || PizzaServita){
                possoCucinare.await();
            }
            System.out.format("Il pizzaiolo[%s] ha iniziato " +
                    "a preparare la maxi-pizza%n", Thread.currentThread().getId());
        }finally {
            l.unlock();
        }
    }

    @Override
    protected void pizzaPronta() {
        l.lock();
        try{
            PizzaServita=true;
            possoMangiare.signalAll();
        }finally {
            l.unlock();
        }
    }
    public static void main(String[] args) {
        Pizzeria pizzeria=new PizzeriaLC();
        Pizzaiolo pizzaiolo=new Pizzaiolo("pizzaiolo", pizzeria);
        pizzaiolo.setDaemon(true);
        pizzaiolo.start();
        Cliente[] clienti=new Cliente[100];
        for(int i=0;i<100;i++){
            clienti[i]=new Cliente(Integer.toString(i), pizzeria);
            clienti[i].start();
        }

        for(int i=0;i<100;i++) {
            try {
                clienti[i].join();
            }catch(InterruptedException e){}
        }
        System.out.println("FINE GIORNATA DI LAVORO");



    }

}
