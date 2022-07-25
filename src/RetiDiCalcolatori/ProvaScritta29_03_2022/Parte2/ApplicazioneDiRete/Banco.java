package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete;




import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.util.Map;
import java.util.Set;

//Cordinatore Delle lotterie
public class Banco {


    private final static int Tport=3000;
    private final static String bAddress="230.0.0.1";
    private final static int bPort=4000;
    private final static int nPartecipanti=10;
    private final Registro registro;

    public Banco(Registro registro, Map<String,Set<Biglietto>> lotterieGestite){
        this.registro=new Registro();
        System.out.println("Avvio banco in corso");
        startServer(lotterieGestite);
    }


    private void startServer(Map<String, Set<Biglietto>> lotterieGestite) {
        for( Map.Entry<String,Set<Biglietto>> entry : lotterieGestite.entrySet()){
            String nomeLotteria=entry.getKey();

            try {
                registro.addLotteria(nomeLotteria, entry.getValue());
                new ThreadAcquistaBiglietto(registro);
                new ThreadTimeoutHandler(nomeLotteria,registro);

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


}
