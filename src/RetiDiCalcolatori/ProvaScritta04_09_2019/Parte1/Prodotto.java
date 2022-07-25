package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte1;

import java.io.Serializable;

public class Prodotto implements Serializable {
    private String codice;
    private String nome;
    private String produttore;
    private double prezzo;

    public Prodotto(String s, String s1, String s2, int i) {
        codice=s;
        nome=s1;
        produttore=s2;
        prezzo=i;
    }

    public String getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getProduttore() {
        return produttore;
    }

    public double getPrezzo() {
        return prezzo;
    }
}
