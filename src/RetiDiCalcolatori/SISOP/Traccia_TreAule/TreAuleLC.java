package RetiDiCalcolatori.SISOP.Traccia_TreAule;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TreAuleLC extends TreAule{


    private Lock lc=new ReentrantLock();
    private Condition Studenti=lc.newCondition();
    private Condition Docente=lc.newCondition();
    private Condition ProssimoStudente=lc.newCondition();
    private Condition AulaPiena=lc.newCondition();

    private boolean aulaScelta=false;
    private int[] posti_occ=new int[3];
    private int aula_curr=0;
    private LinkedList<Studente> coda=new LinkedList<>();



    private boolean MioTurno(){
        Studente stud=(Studente) Thread.currentThread();
        return coda.getFirst()==stud;
    }

    @Override
    protected int entra() throws InterruptedException {
        int aula=-1;
        lc.lock();
        try{
            coda.addLast((Studente)Thread.currentThread());
            if(coda.size()==1) Docente.signal(); //se sono il primo avviso il docente
            while(!MioTurno() || !aulaScelta){
                Studenti.await();
            }
            aula=aula_curr;
            aulaScelta=false;
            ProssimoStudente.signal(); //ho preso l'aula assegnata e avviso il docente

        }finally {
            lc.unlock();
        }
        return aula;
    }

    @Override
    protected void lascia(int c) throws InterruptedException {
        lc.lock();
        try{
            posti_occ[c]--;
            AulaPiena.signal();
        }finally {
            lc.unlock();
        }
    }


    private boolean AulaPiena(){
        return posti_occ[0]==Cap_Max[0] && posti_occ[1]==Cap_Max[1] && posti_occ[2]==Cap_Max[2];
    }
    private void ScegliAula(){
        boolean inserito=false;
        while(!inserito){
            if (posti_occ[aula_curr]<Cap_Max[aula_curr]){
                inserito=true;
            }
            else aula_curr=(aula_curr+1)%Cap_Max.length;
        }
    }

    @Override
    protected void faiEntrare() throws InterruptedException {
        lc.lock();
        try{
            while(coda.size()==0){
                Docente.await();
            }
            while(AulaPiena()){
                AulaPiena.await();
            }
            ScegliAula();//Scelgo la prima aula disponibile controllando la corrente
            posti_occ[aula_curr]++;
            aulaScelta=true;
            while(aulaScelta){
                ProssimoStudente.await(); //Devo aspettare che lo studente corrente capisca in quale aula entrare
            }
            //Politica RR
            aula_curr=(aula_curr+1)%Cap_Max.length;
        }
        finally {
            lc.unlock();
        }

    }
}
