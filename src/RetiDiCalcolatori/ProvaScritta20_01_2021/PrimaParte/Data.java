package RetiDiCalcolatori.ProvaScritta20_01_2021.PrimaParte;

import java.io.Serializable;

public class Data implements Serializable {
    private int Giorno;
    private int Mese;
    private int Anno;

    public Data(int Giorno,int Mese,int Anno){
        if(Giorno>31) throw new RuntimeException();
        if(Mese>12) throw new RuntimeException();
        this.Giorno=Giorno;
        this.Mese=Mese;
        this.Anno=Anno;

    }

    public int getAnno() {
        return Anno;

    }
    public int getMese() {
        return Mese;

    }
    public int getGiorno() {
        return Giorno;

    }


}
