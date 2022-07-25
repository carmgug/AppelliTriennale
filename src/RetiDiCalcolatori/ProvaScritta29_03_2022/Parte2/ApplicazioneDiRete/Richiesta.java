package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete;

import java.io.Serializable;

public class Richiesta implements Serializable {
    private String nomeLotteria;
    private int numeroBiglietti;

    public Richiesta(String nomeLotteria,int numeroBiglietti){
        if(numeroBiglietti<=0) throw new RuntimeException();
        this.nomeLotteria=nomeLotteria;
        this.numeroBiglietti=numeroBiglietti;
    }

    public String getNomeLotteria() {
        return nomeLotteria;
    }

    public void setNomeLotteria(String nomeLotteria) {
        this.nomeLotteria = nomeLotteria;
    }

    public int getNumeroBiglietti() {
        return numeroBiglietti;
    }

    public void setNumeroBiglietti(int numeroBiglietti) {
        this.numeroBiglietti = numeroBiglietti;
    }
}
