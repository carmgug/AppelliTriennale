package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte;

import java.io.Serializable;
import java.util.List;


public class Dato implements Serializable {
    private String grandezza;
    private double valore;
    private long timestamp;


    public Dato(String grandezza, double valore, long timestamp) {
        this.grandezza=grandezza;
        this.valore=valore;
        this.timestamp=timestamp;
    }


    public long getTime(){
        return timestamp;
    }

    public double getValore(){
        return valore;
    }

    public String getGrandezza() {
        return grandezza;
    }
}


