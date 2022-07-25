package RetiDiCalcolatori.ProvaScritta2020gennaio.SecondaParte.Gestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//Richiesta è un stringa <data><NumeroPersone>
//Offerta è una stringa <HostnameCentroBenessere><Prezzo>
public class Gestore {

    private final static int M=20;
    private final static int N=5;
    private Map<Integer,String> ListaOfferte;
    private Registro registro;

    public Gestore(){
        ListaOfferte=new HashMap<>();
        registro=new Registro();
        startserver();
    }

    private void startserver() {
        new ThreadHandlerRichiesta(registro);
    }


}

