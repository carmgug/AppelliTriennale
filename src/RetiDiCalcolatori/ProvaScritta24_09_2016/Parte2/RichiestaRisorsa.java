package RetiDiCalcolatori.ProvaScritta24_09_2016.Parte2;

import java.io.Serializable;

public class RichiestaRisorsa implements Serializable {
    public enum Tipo{
        Hardware,Software
    }

    private final Tipo tipo;
    private final String descrizione;

    public RichiestaRisorsa(Tipo tipo,String descrizione){
        this.tipo=tipo;
        this.descrizione=descrizione;

    }

    public Tipo getTipo(){
        return tipo;
    }

    public String getDescrizione(){
        return descrizione;
    }
}
