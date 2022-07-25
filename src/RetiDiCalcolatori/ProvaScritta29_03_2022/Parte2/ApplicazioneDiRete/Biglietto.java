package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete;

import java.io.Serializable;
import java.util.Objects;

public class Biglietto implements Serializable {
    private final String nomeLotteria;
    private final int Numero;

    public Biglietto(String nomeLotteria, int Numero) {
        if (Numero < 0 || Numero > 10000)
            throw new RuntimeException();
        this.nomeLotteria = nomeLotteria;
        this.Numero = Numero;
    }

    public Biglietto(Biglietto b) {
        this.nomeLotteria = b.nomeLotteria;
        this.Numero = b.getNumero();
    }

    public String getNomeLotteria() {
        return nomeLotteria;
    }

    public int getNumero() {
        return Numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biglietto)) return false;
        Biglietto biglietto = (Biglietto) o;
        return this.Numero == biglietto.Numero &&
                Objects.equals(nomeLotteria, biglietto.nomeLotteria);
    }


}
