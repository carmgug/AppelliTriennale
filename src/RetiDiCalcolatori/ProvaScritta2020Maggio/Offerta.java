package RetiDiCalcolatori.ProvaScritta2020Maggio;

import java.io.Serializable;

public class Offerta implements Serializable {

    Prodotto prodotto;
    Cliente cliente;
    double value;

    public Offerta(Prodotto prodotto, Cliente cliente, double value){
        this.prodotto=prodotto;
        this.cliente=cliente;
        this.value=value;
    }



    public Cliente getCliente() {
        return null;
    }


    public Prodotto getProdotto() {
        return null;
    }

    public double getValue() {
        return 0;
    }
}
