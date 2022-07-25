package SISOP.Traccia_Visualizzatore;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Applicazione {

    private Visualizzatore v;
    private Utente[] utenti;
    private int numero_u;
    private LinkedList<String> coda;
    private int cont=0,Max_cont=100;

    public Applicazione(int numero_u){
        v=new Visualizzatore();
        this.numero_u=numero_u;
        utenti=new Utente[numero_u];
        for(int i=0;i<numero_u;i++){
            utenti[i]=new Utente(Integer.toString(i));
        }
        coda=new LinkedList<>();
    }

    public void avvia(){
        v.start();
        for(int i=0;i<numero_u;i++){
            utenti[i].start();
        }

    }


    //semafori
    private static Semaphore mutex=new Semaphore(1);
    private static Semaphore CiSonoPostiVuoti=new Semaphore(100);
    private static Semaphore CiSonoElementi=new Semaphore(0);


    private void aggiungi(int c,String s) throws InterruptedException {
        CiSonoPostiVuoti.acquire(c);
        mutex.acquire();
        for(int i=0;i<c;i++){
            coda.addLast(s);
        }
        System.out.println("Aggiunto "+s+" "+c+" volta/e");
        cont+=c;
        TimeUnit.SECONDS.sleep(2);
        mutex.release();
        CiSonoElementi.release();
    }

    private void rimuovi() throws InterruptedException{
        CiSonoElementi.acquire();
        mutex.acquire();
        System.out.println(coda.removeFirst()+" Prelievo effettuato con successo!!");

        cont-=1;
        mutex.release();
        CiSonoPostiVuoti.release();
    }



    private class Utente extends Thread{
        private String myID;
        int min=1,max=5;

        public Utente(String myID){
            this.myID=myID;
        }


        private int genera(){
            return (int) Math.random()*(max-min+1)+min;
        }

        @Override
        public void run() {
            try{
                while(true){
                    aggiungi(genera(), myID);

                }
            }catch(InterruptedException e){
                System.out.println("Qualcosa è andato storto!!!");
            }
        }
    }

    private class Visualizzatore extends Thread{

        public void run() {
            try{
                while(true){
                    rimuovi();
                }
            }catch(InterruptedException e){
                System.out.println("Qualcosa è andato storto!!!");
            }
        }
    }

    public static void main(String[] args){
        Applicazione app=new Applicazione(10);
        app.avvia();
    }





}
