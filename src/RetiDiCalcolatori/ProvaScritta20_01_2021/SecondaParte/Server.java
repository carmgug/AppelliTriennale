package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte;

import ProvaScritta20_01_2021.SecondaParte.Thread.ThreadHandlerConnection;
import ProvaScritta20_01_2021.SecondaParte.Thread.ThreadSenderInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Server {

    Map<Integer, List<Misura>> db;
    private final static int tPort=3000;
    public Server(){
        db=new HashMap<Integer,List<Misura>>();
        StartServer();

    }

    private void StartServer(){
        Thread t1=new ThreadHandlerConnection(this,tPort);
        t1.start();
    }

    public synchronized boolean addSensori(int id_Sensore){
        if(!db.containsKey(id_Sensore)){
            db.put(id_Sensore, new LinkedList<Misura>());
            new ThreadSenderInfo(this,id_Sensore).start();
        }
        return false;
    }



    public synchronized Misura getMisura(int id_Sensore){
        if(db.containsKey(id_Sensore)){
            List<Misura> l=db.get(id_Sensore);
            if(l.size()!=0) return l.get(l.size()-1);
        }
        return new Misura(-1,-1,-1);
    }

    public synchronized void addMisura(int id_Sensore,Misura misura){
        if (db.containsKey(id_Sensore)){
            db.get(id_Sensore).add(misura);
        }
    }

    public synchronized boolean getStato(int id_Sensore){
        //esiste per costruzione
        List<Misura> l=db.get(id_Sensore);
        long timestamp=l.get(l.size()-1).getTime();
        if(System.currentTimeMillis()-timestamp<10000){
            return false;
        }
        return true;
    }






}
