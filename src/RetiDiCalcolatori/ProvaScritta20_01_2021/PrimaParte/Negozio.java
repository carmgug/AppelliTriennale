package RetiDiCalcolatori.ProvaScritta20_01_2021.PrimaParte;

import java.util.List;

public class Negozio {
    private String piva;
    private String provincia;
    private List<Incasso> incassi;


    public List<Incasso> getIncassi() {
        return incassi;
    }
}
class Incasso{
    private int Valore;
    private Data data_incasso;

    public Data getData() {
        return data_incasso;
    }

    public int getValore() {
        return Valore;
    }
}
