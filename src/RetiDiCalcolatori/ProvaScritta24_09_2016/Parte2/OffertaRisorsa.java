package RetiDiCalcolatori.ProvaScritta24_09_2016.Parte2;

import java.io.Serializable;

public class OffertaRisorsa implements Serializable {

    private final String nome;
    private final RichiestaRisorsa.Tipo tipo;
    private final String descrizione;

    public OffertaRisorsa(String nome, RichiestaRisorsa.Tipo tipo,String descrizione){
        this.nome=nome;
        this.tipo=tipo;
        this.descrizione=descrizione;
    }

    public String getNome() {
        return nome;
    }

    public RichiestaRisorsa.Tipo getTipo() {
        return tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
