package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DistributorBenzineService {

    private Map<String, List<Distributore>> db;


    public DistributorBenzineService(){
        db=new HashMap<String,List<Distributore>>();
    }

    public Distributore MinPrezzoBenzina(String regione){
        if(db.containsKey(regione)){
            List<Distributore> Dist_regione=db.get(regione);
            if(Dist_regione.size()!=0){
                Iterator<Distributore> it= Dist_regione.listIterator();
                Distributore miglioreDist= it.next();
                while(it.hasNext()){
                    Distributore tmp=it.next();
                    if(tmp.getPrezzoBenzina()<miglioreDist.getPrezzoBenzina())
                        miglioreDist=tmp;
                }
                return miglioreDist;

            }
        }
        return new Distributore("error","error","error",-1,-1);

    }

    public String RegioneMinMediaDiselResponse(){
        String regione_curr=null;
        double media_curr = 0;

        for(Map.Entry<String,List<Distributore>> entry: db.entrySet()) {
            if (regione_curr == null) { //Prima regione incontrata
                regione_curr= entry.getKey();
                media_curr=getMediaDiselRegione(entry.getValue());
            }
            else{
                double tmp_med=getMediaDiselRegione(entry.getValue());
                if(tmp_med < media_curr) {
                    media_curr=tmp_med;
                    regione_curr= entry.getKey();
                }
            }
        }
        return regione_curr;
    }


    private double getMediaDiselRegione( List<Distributore> d){
        double med=0;
        if(d.size()==0) return med;
        for(Distributore curr:d){
            med+=curr.getPrezzoDisel();
        }
        med=med/d.size();
        return med;
    }






}
