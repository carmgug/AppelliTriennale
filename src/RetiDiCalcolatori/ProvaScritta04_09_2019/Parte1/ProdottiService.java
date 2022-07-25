package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte1;

import java.util.*;

public class ProdottiService {

    Map<Integer, Set<Prodotto>> magazzini;

    //Non si capisce dalla traccia, ma senza questa lista sarebbe una vera tragedia
    Set<Prodotto> listaProdotti; //lista contenente tutti i prodotti

    public ProdottiService(){
        magazzini=new HashMap<Integer,Set<Prodotto>>();
    }

    public Prodotto getProdottopiuVenduto(String produttore){
        Prodotto prod_curr=new Prodotto("-1","-1","-1",-1);
        int max_vendite=-1;
        for(Prodotto p : listaProdotti){
            if( p.getProduttore().equals(produttore)){
                int vendite_tmp=contaVendite(p);
                if(vendite_tmp>max_vendite){
                    prod_curr=p;
                    max_vendite=vendite_tmp;
                }
            }
        }
        return prod_curr;
    }

    private int contaVendite(Prodotto p){
        int vendite=0;
        for(Map.Entry<Integer,Set<Prodotto>> entry: magazzini.entrySet()){
            if(entry.getValue().contains(p)) vendite++;
        }
        return vendite;
    }


    public List<Prodotto> getProdottiMaxIncasso(int id){
        List<Prodotto> res=new LinkedList<Prodotto>();
        if(magazzini.containsKey(id)){
            Set<Prodotto> prodotti=magazzini.get(id);
            Prodotto max1=null,max2=null,max3=null;
            double prezzo1=-1,prezzo2=-1,prezzo3=-1;
            for(Prodotto p: prodotti){
                double prezzo_Curr=p.getPrezzo();
                if(prezzo1<prezzo_Curr){
                    max3=max2; prezzo3=prezzo2;
                    max2=max1; prezzo2=prezzo1;
                    max1=p; prezzo1=prezzo_Curr;
                    //Per definizione max1 sicuramente è più costoso di max2 e
                    //max2 è più costoso di max3 quindi effettuo un vero prorpio shift
                }
                else if(prezzo2<prezzo_Curr){
                    max3=max2; prezzo3=prezzo2;
                    max2=p; prezzo2=prezzo_Curr;

                }
                else if(prezzo3<prezzo_Curr) {
                    max3 = p;
                    prezzo3 = prezzo_Curr;
                }
            }
            res.add(max1);
            res.add(max2);
            res.add(max3);
        }

        return res; //in caso il magazzino non esista viene restituita una lista vuota.

    }


}
