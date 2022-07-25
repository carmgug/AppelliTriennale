package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte1;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WebService {

    Map<Cordinate, List<Informazione>> db;
    Map<String,List<Citta>> localita;

    public Informazione MeteoCordinata(Cordinate c){
        if(db.containsKey(c)){
            List<Informazione> info=db.get(c);
            return info.get(info.size()-1);
        }
        return new Informazione("error",0,0);
    }

    public String MaxDifferenzaTemperatura(String stato){
        if(localita.containsKey(stato)){
            List<Citta> citta=localita.get(stato);
            String citta_cur="";
            double max_Differenza=-1;
            for(Citta c: citta){
                Double curr=calcolaMaxDiff(c.cordinate);
                if(curr>max_Differenza) {
                    citta_cur=c.nome;
                    max_Differenza=curr;
                }
            }
            return citta_cur;
        }
        return "-1";
    }

    private double calcolaMaxDiff(Cordinate c){
        double max_Differenza=-1;
        List<Informazione> info=db.get(c);
        for(Informazione i:info){
            double curr=Math.abs((i.temperaturaMax-i.temperaturaMin));
            if(curr>max_Differenza) max_Differenza=curr;
        }
        return max_Differenza;
    }


}
class Informazione implements Serializable{
    String condizione;
    Double temperaturaMin;
    Double temperaturaMax;

    public Informazione(String error, int i, int i1) {

    }
}
class Cordinate implements Serializable{
    Double latitudine;
    Double longitudine;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cordinate)) return false;
        Cordinate cordinate = (Cordinate) o;
        return Objects.equals(latitudine, cordinate.latitudine) && Objects.equals(longitudine, cordinate.longitudine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitudine, longitudine);
    }
}
class Citta implements Serializable{
    String nome;
    Cordinate cordinate;

    public boolean equals(Object o){
        if(o==null || !(o instanceof Citta)) return false;
        if(this==o) return true;
        Citta c=(Citta) o;
        return ((Citta) o).cordinate.equals(this.cordinate);
    }

    public int hashCode() {
        return cordinate.hashCode();
    }

}


