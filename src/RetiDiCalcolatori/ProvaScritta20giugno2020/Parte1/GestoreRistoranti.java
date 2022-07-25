package RetiDiCalcolatori.ProvaScritta20giugno2020.Parte1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GestoreRistoranti {

   Map<String,Ristorante> db;

    public int maxAmmontare(String piva,int dataInzio,int Datafine){
        int ammontare=0;
        if(db.containsKey(piva)){
            List<Vendita> v=db.get(piva).getVendite();
            for(Vendita x: v){
                if(x.getData()>=dataInzio && x.getData()<=Datafine){
                    ammontare=x.getvalue();
                }
            }

        }
        return ammontare;
    }

    public String maxCitta(String citta){
        int maxguadagni=0;
        String piva="";
        for(Map.Entry<String,Ristorante> entry : db.entrySet()){
            if(citta.equals(entry.getValue().getCitta())){
                Ristorante currRistornate=entry.getValue();
                if(piva==""){
                    piva= entry.getKey();
                    maxguadagni=calcolaGuadagni(currRistornate.getVendite());
                }
                else{
                    int curr_guadagni=calcolaGuadagni(currRistornate.getVendite());
                    if(curr_guadagni>maxguadagni){
                        maxguadagni=curr_guadagni;
                        piva=Ristorante.getPiva();
                    }
                }
            }
        }
        return piva;
    }

    private int calcolaGuadagni(List<Vendita> vendite){
        int guadagno=0;
        for(Vendita v: vendite ){
            guadagno+=v.getValue();

        }
        return guadagno;
    }





}

class Ristorante {
    private String piva;
    private String città;

    private List<Vendita> vendite;

    public static String getPiva() {
        return null;
    }

    public List<Vendita> getVendite(){
        return vendite;
    }

    public String getCitta() {
        return null;
    }
}

class Vendita{
    private int value;
    private int data_Scontrino;

    public int getValue() {
        return 0;
    }

    public int getData() {
        return 0;
    }

    public int getvalue() {
        return 0;
    }
}

