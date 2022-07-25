package RetiDiCalcolatori.ProvaScritta18_03_2021.Parte2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class ThreadRichiestaHandler extends Thread{

    private final static int tPort=2222;
    private final Socket client;
    private Socket negozio;
    private final Registro registro;



    public ThreadRichiestaHandler(Socket client,Registro registro){
        this.client=client;
        this.registro=registro;
    }



    public void run(){
        try{
            BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()));
            String msg=bf.readLine();
            bf.close();

            String[] split=msg.split(",");
            double prezzoMassimo=Double.parseDouble(split[2]);

            //Inviamo tramite il socket negozio sulla porta TCP 2222
            //Le richieste ai vari negozi
            //Al più potremo parallelizzare questa parte crendo un thread e passandogli
            //hostname e porta tcp
            //Ma nel caso peggiore andremmo a creare una cinquantina di thread;
            //ma dovremmo anche implementare un sistema per come avvisare il thread che si è ricevuta risposta da parte di tutti i negozi;
            String[] ris=SendAndRiceviOfferte(msg,prezzoMassimo);

            msg=creaRisposta(ris);

            PrintWriter pw=new PrintWriter(client.getOutputStream(),true);
            pw.write(msg);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String[] SendAndRiceviOfferte(String msg,double prezzoMassimo){
        int OffertaCorrente = -1;
        String idNegozio = "";
        try {
            //Invio e Lettura risposte Server
            //Se voglio farlo in maniera parallela creo per ogni entry un Thread che gestice la richiesta.
            Map<String, java.lang.Record> db = registro.getMappa();
            for (Map.Entry<String, java.lang.Record> entry : db.entrySet()) {
                String hostname = entry.getValue().getHostname();
                negozio = new Socket(hostname, tPort);
                PrintWriter pw = new PrintWriter(negozio.getOutputStream(), true);
                pw.write(msg);
                pw.close();

                BufferedReader bf = new BufferedReader(new InputStreamReader(negozio.getInputStream()));
                msg = bf.readLine();
                bf.close();

                int Offertatemp = Integer.parseInt(msg);
                if (Offertatemp != 0 && Offertatemp<=prezzoMassimo && OffertaCorrente==-1 ) {
                    OffertaCorrente = Offertatemp;
                    idNegozio = entry.getKey();
                }
                else if(Offertatemp<=prezzoMassimo && Offertatemp<OffertaCorrente){
                    OffertaCorrente = Offertatemp;
                    idNegozio = entry.getKey();
                }
                negozio.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        if(OffertaCorrente!=-1){//almeno un offerta è stata trovata
            String[] ris={""+OffertaCorrente,idNegozio};
            return ris;
        }
        return new String[]{"-1", "-1"};

    }

    private String creaRisposta(String[] ris){
        if(ris[0]=="-1" && ris[1]=="-1") return "Nessuna offerta è arrivata";
        else
            registro.addVendita(ris[1],Double.parseDouble(ris[0]));
            return ris[1]+","+ris[0];

    }

}
