package RetiDiCalcolatori.SISOP.Traccia_Piscina;



import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class PiscinaSEM extends Piscina{



    private Semaphore[] corsia;
    private Semaphore mutex=new Semaphore(1);
    private Semaphore Aperta=new Semaphore(0);


    private boolean aperta;

    private int[] posti_dis;
    private LinkedList<Persona> persone=new LinkedList<>(); //persone in piscina

    public PiscinaSEM(){
        corsia=new Semaphore[corsie];
        posti_dis=new int[corsie];
        for(int i=0;i<corsie;i++) {
            corsia[i] = new Semaphore(0, true);
            posti_dis[i]=0;
        }
        aperta=false;

    }


    private int scegli(){
        int max=posti_dis[0],j=0;
        for(int i=1;i<corsie;i++){
            if(posti_dis[i]>max){
                j=i;max=posti_dis[i];
            }
        }
        if(max==0)
            //allora non c'erano posti disponbili
            return (int)(Math.random()*(corsie-0))+0;
        else {posti_dis[j]--;return j;}



    }



    @Override
    public int entra() throws InterruptedException{

        Aperta.acquire();Aperta.release(); //se la piscina è aperta allora entra e lascia spazio per il prossimo cliente

        mutex.acquire();//scelgo la corsia e ci entro
        int c=scegli();

        mutex.release();
        corsia[c].acquire();

        //aggiungo all'elenco delle persone che stanno nuotando
        mutex.acquire();
        persone.add((Persona)Thread.currentThread());
        mutex.release();

        return c;

    }

    @Override
    public void esci(int c) throws InterruptedException{
        //se esco e la piscina non è chiusa allora lascio il posto se no non effettuo nessuna release
        mutex.acquire();
        if(aperta) {corsia[c].release();posti_dis[c]++;}
        persone.remove((Persona)Thread.currentThread());
        mutex.release();

    }

    @Override
    public void apri() throws InterruptedException{
        mutex.acquire();
        aperta=true;
        for(int i=0;i<corsie;i++) {
            int x=(4-posti_dis[i]);
            corsia[i].release((4-posti_dis[i]));
            posti_dis[i]=4;
        }
        mutex.release();
        Aperta.release();

    }

    @Override
    public void chiudi() throws InterruptedException{
        mutex.acquire();
        for(Persona curr:persone) curr.interrupt();
        aperta=false;
        Aperta.acquire();
        mutex.release();

    }


    public static void main(String[] args) {
        Piscina piscina=new PiscinaSEM();
        Persona[] persone=new Persona[100];
        for(int i=0;i<100;i++){
            persone[i]=new Persona(Integer.toString(i), piscina);
            persone[i].start();
        }
        Istruttore istruttore=new Istruttore("istruttore",piscina);
        istruttore.setDaemon(true);
        istruttore.start();
    }
}
