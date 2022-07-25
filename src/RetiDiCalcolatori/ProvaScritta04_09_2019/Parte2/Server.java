package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte2;

import ProvaScritta04_09_2019.Parte2.ThreadServer.HandConnection;
import ProvaScritta04_09_2019.Parte2.ThreadServer.HandlerQueue;
import ProvaScritta04_09_2019.Parte2.ThreadServer.SenderDirezione;

import java.net.Socket;
import java.util.*;

public class Server {

    Map<String,Esame> db;


    public Server(){
        db=new HashMap<String,Esame>();
        startserver();
    }

    private void startserver(){
        new HandConnection(this).start();
        new SenderDirezione().start();
    }

    public boolean addEsame(String codEsame,Esame e){
        if(!db.containsKey(codEsame)){
            db.put(codEsame,e);
            new HandlerQueue(this,codEsame).start();
            return true; //tutto andato a bene
        }
        return false;
    }


    public synchronized String addPrenotazione(String codEsame,Socket paziente) {
        if(db.containsKey(codEsame)){
            Esame e=db.get(codEsame);
            String prenotazione=e.addPaziente(paziente);
            if(!prenotazione.equals("pieno")){
                return codEsame+"#"+prenotazione;
            }
        }
        return "non effettuata";
    }

    public synchronized void removePrenotazione(String codEsame, int codPrenotazione) {
        if(db.containsKey(codEsame)){
            db.get(codEsame).removePrenotazione(codPrenotazione);
        }

    }

    public synchronized List<String> getStatistica() {
        List<String> res=new LinkedList<String>();
        for(Map.Entry<String,Esame> entry:db.entrySet()){
            String msg=""+entry.getKey()+
                    "#"+entry.getValue().numeropazientiTotali+
                    "#"+entry.getValue().getSizeQueue();
            res.add(msg);
        }
        return res;
    }

    public synchronized  Object[] addPazienteQueue(String codEsame) {
        Esame e=db.get(codEsame); //esiste per costruzione
        if(e.PostoDisponibile() && e.getSizeQueue()>0){
            Object[] o=new Object[2];
            o[0]=e.addPaziente(null); //so già che è presente un posto;
            o[1]=e.getFirstPaziente();
        }
        Object[] o={"non effettuata"};
        return o;
    }
}
class Esame{
    private String cod;
    private final static int max_Pazienti=20;
    private int curr_pazienti=0;
    private Medico m1;
    private Medico m2;
    private LinkedList<Socket> queuePaz;

     int numeropazientiTotali=0;

    public String addPaziente(Socket paziente){
        if(curr_pazienti<max_Pazienti){
            curr_pazienti++;
            numeropazientiTotali++;
            if(curr_pazienti%2==0) return m1.matricola+"#"+curr_pazienti;
            else return m2.matricola+"#"+curr_pazienti;
        }
        queuePaz.add(paziente);
        return "pieno";
    }
    public void removePrenotazione(int CodPrenotazione){
        curr_pazienti--;
    }

    public int getSizeQueue(){
        return queuePaz.size();
    }

    public boolean PostoDisponibile(){
        return curr_pazienti<max_Pazienti;
    }

    public Socket getFirstPaziente(){
        Socket paziente=queuePaz.pollFirst();
        return paziente;
    }




}
class Medico{
    String matricola;
}



