package RetiDiCalcolatori.SISOP.Traccia_TreAule;

import java.util.concurrent.Semaphore;

public class TreAuleSem extends TreAule{


    private Semaphore mutex=new Semaphore(1);
    private Semaphore PostiDisponibili=new Semaphore(Cap_Max[0]+Cap_Max[1]+Cap_Max[2]);
    private Semaphore AspettaTurno=new Semaphore(0,true);
    private Semaphore DevoAggiungere=new Semaphore(0);
    private Semaphore Aula=new Semaphore(0);


    private int[] stud_curr=new int[3];
    private int aula_curr=0;//la prima aula dove far entrare è la prima

    public TreAuleSem(){
        super();

    }

    @Override
    protected int entra() throws InterruptedException {
        DevoAggiungere.release();
        AspettaTurno.acquire();
        int Mia_Aula=aula_curr; //prendo l'scelta dal docente
        Aula.release();
        return Mia_Aula;

    }

    @Override
    protected void lascia(int c) throws InterruptedException {
        mutex.acquire();
        stud_curr[c]--;
        mutex.release();
        PostiDisponibili.release();
    }

    @Override
    protected void faiEntrare() throws InterruptedException {
        DevoAggiungere.acquire();
        PostiDisponibili.acquire();
        mutex.acquire();
        boolean inserito=false;
        while(!inserito){
            if (stud_curr[aula_curr]<Cap_Max[aula_curr]){
                stud_curr[aula_curr]+=1;
                AspettaTurno.release(); //è stato aggiunto uno studente
                inserito=true;
            }
            else aula_curr=(aula_curr+1)%Cap_Max.length;
        }
        mutex.release();
        //per politica RR
        Aula.acquire();//non posso aggiornare l'aula prima che lo studente che ho appena fatto entrare non l'ha acquisita
        aula_curr=(aula_curr+1)%Cap_Max.length;



    }


}
