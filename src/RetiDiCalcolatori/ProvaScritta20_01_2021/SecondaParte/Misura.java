package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte;

import java.io.Serializable;

public class Misura implements Serializable {

    private final int id_Sensore;
    private final double valore;
    private final long timestamp;

    public Misura(int id_Sensore,double valore, long timestamp){
        this.id_Sensore=id_Sensore;
        this.valore=valore;
        this.timestamp=timestamp;

    }


    public long getTime() {
        return timestamp;
    }
}
