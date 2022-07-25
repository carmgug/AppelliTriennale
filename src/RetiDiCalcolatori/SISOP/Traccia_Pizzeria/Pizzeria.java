package RetiDiCalcolatori.SISOP.Traccia_Pizzeria;

public abstract class Pizzeria {

    protected static int Max_Clienti=5;
    protected int curr_clienti;



    public Pizzeria(){
        curr_clienti=0;

    }


    protected abstract void miSiedo() throws InterruptedException;
    protected abstract void mangiaPizza() throws InterruptedException;
    protected abstract void pizzaMangiata() throws InterruptedException;
    protected abstract void preparaPizza() throws InterruptedException;
    protected abstract void pizzaPronta();


}
