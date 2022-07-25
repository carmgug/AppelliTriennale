package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte2;


import ProvaScritta20_01_2021.SecondaParte.Thread.ThreadHandlerConnection;

import java.net.Socket;
import java.util.*;

public class Porto {

    private final String hostname;
    private final static int primoTipo=40;
    private int curr_banchine40m=0;
    private int curr_banchineOver40m=0;
    private final List<Nave> db;

    private final LinkedList<Nave> coda;


    public Porto(String hostname){
        this.hostname=hostname;
        db=Collections.synchronizedList(new LinkedList<>());
        coda=new LinkedList<Nave>();
        this.startServer();
    }

    private void startServer(){
        for(int i=0;i<5;i++){
            new ThreadOperatore(this,i);
        }
        new ThreadHandleConnection(this).start();

    }

    public synchronized int addNave(int id_nave,double lunghezza,int n_container){
        Nave nave=new Nave(id_nave,lunghezza,n_container);
        if(lunghezza<=primoTipo && curr_banchine40m<5){
            db.add(nave);
            curr_banchine40m++;
            return db.size()-1;
        }
        else if(curr_banchineOver40m<5){
            db.add(nave);
            curr_banchineOver40m++;
            return db.size()-1;
        }

        coda.add(nave);
        return -1;
    }


    public String getAddressOperatore(int n_banchina) {
        return "hostname";
    }

    public synchronized void removeNave(int id_nave) {
        Iterator<Nave> it=db.listIterator();
        while(it.hasNext()){
            Nave n=it.next();
            if(n.getId_Nave()==id_nave){

                if(n.getLunghezza()<=primoTipo) curr_banchineOver40m++;
                else curr_banchineOver40m--;
                it.remove();
                break;
            }
        }


    }

    public synchronized int getNumeroContainer(int id_nave) {
        Iterator<Nave> it=db.listIterator();
        while(it.hasNext()){
            Nave n=it.next();
            if(n.getId_Nave()==id_nave){
                return n.getN_container();
            }
        }
        return -1;
    }

    public synchronized int gestisciNaveCoda() {
        Nave n=coda.getFirst();
        return addNave(n.getId_Nave(),n.getLunghezza(),n.getN_container());
    }

    public int getIdNave(int id_banchina) {
        return  db.get(id_banchina).getId_Nave();
    }
}

class Nave{
    private int id_nave;
    private double lunghezza;
    private int n_Container;

    public Nave(int id_nave, double lunghezza, int n_container) {

    }

    public int getId_Nave() {
        return id_nave;
    }

    public double getLunghezza() {
        return lunghezza;
    }

    public int getN_container() {
        return n_Container;
    }
}
