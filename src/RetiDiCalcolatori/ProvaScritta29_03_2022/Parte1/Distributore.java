package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte1;

import java.io.Serializable;

public class Distributore implements Serializable {

    private String PartitaIva; //piva
    private String Regione; //regione di appertenenza
    private String RagioneSociale; //ragione sociale
    private double PrezzoBenzina,PrezzoDisel; //prezzo benzina e disel



    public Distributore(String PartitaIva,String regione,String RagioneSociale,double PrezzoBenzina,double PrezzoDisel){
        this.PartitaIva=PartitaIva;
        this.Regione=regione;
        this.RagioneSociale=RagioneSociale;
        this.PrezzoBenzina=PrezzoBenzina;
        this.PrezzoDisel=PrezzoDisel;
    }

    public String getRegione() {
        return Regione;
    }


    public String getRagioneSociale() {
        return RagioneSociale;
    }


    public double getPrezzoBenzina() {
        return PrezzoBenzina;
    }


    public double getPrezzoDisel() {
        return PrezzoDisel;
    }


    public String getPartitaIva() {
        return PartitaIva;
    }


}
