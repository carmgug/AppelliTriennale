package RetiDiCalcolatori.ProvaScritta2020Maggio.PrimaParte;

import java.io.Serializable;

public class Ospedale implements Serializable {

    private int code;
    private String city;
    private int pazienti;
    private int postiLetto;

    public Ospedale(String error, String error1, int i, int i1) {

    }


    public boolean addPosto(int numeroPosti){
        if(pazienti+numeroPosti>postiLetto) return false; //posti letto non aggiornati
        postiLetto=pazienti+numeroPosti;
        return true;
    }

    public int getPazienti(){
        return pazienti;
    }

    public int getPostiLiberi(){
        return postiLetto-pazienti;
    }


}
