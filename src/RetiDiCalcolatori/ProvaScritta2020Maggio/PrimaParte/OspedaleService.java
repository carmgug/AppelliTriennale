package RetiDiCalcolatori.ProvaScritta2020Maggio.PrimaParte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OspedaleService {

    Map<String, List<Ospedale>> db=new HashMap<String,List<Ospedale>>();

    public Ospedale MaxOspedaleRequest(String citta){
        if(db.containsKey(citta)){
            Ospedale curr=null;
            int max_posti=-1;
            List<Ospedale> l=db.get(citta);
            for(Ospedale o:l){
                int curr_posti=curr.getPostiLiberi();
                if(curr_posti>max_posti){
                    curr=o;
                    max_posti=curr_posti;
                }
            }
            return curr;
        }
        return new Ospedale("error","error",-1,-1);
    }


    public String CittaRatio(){
        String citta_curr="";
        double tasso_max=-1;
        for(Map.Entry<String,List<Ospedale>> entry: db.entrySet()){
            double tasso_curr=getTassoOccupazione(entry.getValue());
            if(tasso_curr>tasso_max){
                citta_curr=entry.getKey();
                tasso_max=tasso_curr;
            }
        }



        return citta_curr;
    }


    private double getTassoOccupazione(List<Ospedale> ospedali){
        int postiLettoDisponibili_tot=0;
        int pazienti_tot=0;
        for(Ospedale o: ospedali){
            pazienti_tot+=o.getPazienti();
            postiLettoDisponibili_tot+=o.getPostiLiberi();
        }
        return pazienti_tot/postiLettoDisponibili_tot;
    }


}
