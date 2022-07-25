package RetiDiCalcolatori.ProvaScritta20_01_2021.PrimaParte;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WebService {

    Map<String, Map<String,Negozio>> db;
    //Provincia----> coppie(piva,Negozio);


    public String GuadagnoDaA(String provincia, Data dataF){
        String piva_max="";
        int GuadagnoMax=-1;

        if(db.containsKey(provincia)){
            Map<String,Negozio> mappa=db.get(provincia);
            for(Map.Entry<String,Negozio> entry: mappa.entrySet()){
                String piva_curr=entry.getKey();
                int guadagno_curr=calcolaIncasso(entry.getValue().getIncassi(),dataF);
                if(guadagno_curr>GuadagnoMax){
                    piva_max=piva_curr;
                    GuadagnoMax=guadagno_curr;
                }
            }
        }
        return piva_max;
    }

    private int calcolaIncasso(List<Incasso> incassi,Data dataF){
        int incasso=0;
        for(Incasso i:incassi){
            if(isBefore(dataF,i.getData())){
                incasso+=i.getValore();
            }
        }
        return incasso;
    }


    private boolean isBefore(Data d1,Data d2){//d1,dataFine d2,data
        if(d2.getAnno()<d1.getAnno()) return true;
        if(d2.getAnno()==d1.getAnno()){
            if(d2.getMese()<d1.getMese()) return true;
            if(d2.getMese()==d1.getMese()){
                if(d2.getGiorno()<=d1.getGiorno()) return true;
            }
        }
        return false;
    }

    public List<Data> DateGuadagno(String piva,int cifra){
        Negozio curr=trovaNegozio(piva);
        if(curr!=null){
            ArrayList<Data>  date=new ArrayList<Data>();
            List<Incasso> incassi=curr.getIncassi();
            for(Incasso i: incassi){
                if(i.getValore()>=cifra) date.add(i.getData());
            }
            return date;
        }
        return new ArrayList<Data>();
    }

    private Negozio trovaNegozio(String piva){
        for(Map.Entry<String,Map<String,Negozio>> entry: db.entrySet()){
            if(entry.getValue().containsKey(piva)){
                return entry.getValue().get(piva);
            }
        }
        return null;
    }


}

