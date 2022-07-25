package RetiDiCalcolatori.SISOP.Traccia_Cioccolatini;

import java.util.LinkedList;
import java.util.concurrent.*;

public class ScatolaSp extends Scatola{


    private Semaphore mutex=new Semaphore(1);
    private Semaphore scatola=new Semaphore(1);

    private Semaphore PossoPrendere=new Semaphore(100);


    private int[] Cioc_curr;
    private int Tot_cioc;


    public ScatolaSp(int N,int X){
        super(N,X);
        Cioc_curr=new int[X];
        for(int i=0;i<X;i++) Cioc_curr[i]=N/X;
        Tot_cioc=N;

    }

    private int scegli(){
        boolean trovato=false;
        int tipo_scelto=-1;
        while(!trovato){
            tipo_scelto=(int)(Math.random()*(X-0))+0;
            if(Cioc_curr[tipo_scelto]>0) {trovato=true;Cioc_curr[tipo_scelto]--;}
        }
        return tipo_scelto;
    }
    private int CioccolatiniRimasti(){
        if(Tot_cioc>=5) {Tot_cioc-=5;return 5;}
        if(Tot_cioc<5 && Tot_cioc!=0) {Tot_cioc=0;return Tot_cioc;};
        return 5;
    }
    @Override
    public LinkedList<Integer> get() throws InterruptedException{
        LinkedList<Integer> manciata=new LinkedList<>();
        //scelgo quanti prelevarne quindi devo guardare la scatola
        mutex.acquire();
        int daPrelevare=CioccolatiniRimasti();
        mutex.release();
        //in caso devo aspettare che mettino qualche cioccolatino dentro la scatola
        //perchè può essere che qualcuno ne abbia presi 5 e ne siano rimasti 0 e devo aspettare che
        // rimetta gli altri quattro.
        PossoPrendere.acquire(daPrelevare);
        //accedo alla scatola e metto il pugno.
        scatola.acquire();
        for(int i=0;i<daPrelevare;i++){
            manciata.add(scegli());
        }
        scatola.release();
        return manciata;
    }

    @Override
    public void put(LinkedList<Integer> c) throws InterruptedException {
        scatola.acquire();
        mutex.acquire();
        //accedo alla scatola e rimetto i cioccolatini
        while(c.size()!=0){
            Tot_cioc++;
            Cioc_curr[c.remove()]++;
            PossoPrendere.release();
        }


        mutex.release();
        scatola.release();

    }

    public static void main(String[] args){
        Scatola scatola=new ScatolaSp(100, 5);
        Persona[] persone=new Persona[100];

        for(int i=0;i<100;i++){
            persone[i]=new Persona(Integer.toString(i), scatola);
            persone[i].start();
        }
    }

}
